package net.happykoo.moneyagg.adapter.out.external;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.ExternalSystemAdapter;
import net.happykoo.common.http.CommonHttpClient;
import net.happykoo.moneyagg.adapter.out.external.response.MembershipResponse;
import net.happykoo.moneyagg.application.port.out.FindMembershipPort;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@Slf4j
public class MembershipExternalAdapter implements FindMembershipPort {

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
  public List<String> findMembershipIdsByAddress(String address) {
    String url = String.join("/",
        membershipServiceUrl,
        "membership/by-address",
        address);
    try {
      var response = commonHttpClient.sendGetRequest(url,
          new TypeReference<List<MembershipResponse>>() {
          });
      return response
          .stream()
          .map(MembershipResponse::getMembershipId)
          .toList();
    } catch (Exception e) {
      log.error("error occurred when requesting membership : {}", e.getMessage());
      return List.of();
    }
  }
}
