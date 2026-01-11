package net.happykoo.moneyagg.adapter.out.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.ExternalSystemAdapter;
import net.happykoo.common.http.CommonHttpClient;
import net.happykoo.moneyagg.adapter.out.external.body.MembershipIdsBody;
import net.happykoo.moneyagg.adapter.out.external.response.MemberMoneyResponse;
import net.happykoo.moneyagg.application.port.out.FindMemberMoneyPort;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@Slf4j
public class MoneyExternalAdapter implements FindMemberMoneyPort {

  private final CommonHttpClient commonHttpClient;
  private final String moneyServiceUrl;
  private final ObjectMapper objectMapper;

  public MoneyExternalAdapter(
      CommonHttpClient commonHttpClient,
      @Value("${service.money.url}") String moneyServiceUrl
  ) {
    this.commonHttpClient = commonHttpClient;
    this.moneyServiceUrl = moneyServiceUrl;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public long findMoneySumByMembershipIds(List<String> membershipIds) {
    String url = String.join("/",
        moneyServiceUrl,
        "money/member-money/by-membership-id");
    try {
      var response = commonHttpClient.sendPostRequest(url,
          objectMapper.writeValueAsString(new MembershipIdsBody(
              membershipIds
          )),
          new TypeReference<List<MemberMoneyResponse>>() {
          });
      return response.get()
          .stream()
          .mapToInt(MemberMoneyResponse::getBalance)
          .sum();
    } catch (Exception e) {
      log.error("error occurred when requesting money: {}", e.getMessage());
      return 0;
    }
  }
}
