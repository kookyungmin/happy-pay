package net.happykoo.remittance.adapter.out.external.membership;

import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.ExternalSystemAdapter;
import net.happykoo.common.http.CommonHttpClient;
import net.happykoo.remittance.application.port.out.RequestMembershipInfoPort;
import net.happykoo.remittance.application.port.out.data.MembershipData;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@Slf4j
public class MembershipExternalAdapter implements RequestMembershipInfoPort {

  private final CommonHttpClient commonHttpClient;
  private final String membershipServiceUrl;

  public MembershipExternalAdapter(
      CommonHttpClient commonHttpClient,
      @Value("${service.membership.url}") String membershipServiceUrl
  ) {
    this.commonHttpClient = commonHttpClient;
    this.membershipServiceUrl = membershipServiceUrl;
  }

  @Override
  public boolean existsMembership(String membershipId) {
    String url = String.join("/",
        membershipServiceUrl,
        "membership",
        membershipId);

    try {
      MembershipData response = commonHttpClient.sendGetRequest(url, MembershipData.class);
      log.info("membership response : {}", response);
      return response.isValid();
    } catch (Exception e) {
      log.error("error occurred when requesting membership : {}", e.getMessage());
      return false;
    }
  }
}
