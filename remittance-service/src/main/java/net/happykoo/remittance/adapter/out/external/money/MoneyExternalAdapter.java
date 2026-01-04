package net.happykoo.remittance.adapter.out.external.money;

import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.ExternalSystemAdapter;
import net.happykoo.common.http.CommonHttpClient;
import net.happykoo.remittance.application.port.out.RequestFirmBankingPort;
import net.happykoo.remittance.application.port.out.RequestMoneyChangingPort;
import net.happykoo.remittance.application.port.out.RequestMoneyInfoPort;
import net.happykoo.remittance.application.port.out.data.MoneyInfoData;
import net.happykoo.remittance.application.port.out.payload.FirmBankingPayload;
import net.happykoo.remittance.application.port.out.payload.MoneyChangingPayload;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@Slf4j
public class MoneyExternalAdapter implements RequestMoneyChangingPort, RequestMoneyInfoPort {

  private final CommonHttpClient commonHttpClient;
  private final String moneyServiceUrl;

  public MoneyExternalAdapter(
      CommonHttpClient commonHttpClient,
      @Value("${service.money.url}") String moneyServiceUrl
  ) {
    this.commonHttpClient = commonHttpClient;
    this.moneyServiceUrl = moneyServiceUrl;
  }

  //TODO: Money service 호출
  @Override
  public boolean requestMoneyRecharging(MoneyChangingPayload payload) {
    return true;
  }

  @Override
  public boolean requestMoneyIncrease(MoneyChangingPayload payload) {
    return true;
  }

  @Override
  public boolean requestMoneyDecrease(MoneyChangingPayload payload) {
    return true;
  }

  @Override
  public MoneyInfoData requestMoneyInfo(String membershipId) {
    return null;
  }
}
