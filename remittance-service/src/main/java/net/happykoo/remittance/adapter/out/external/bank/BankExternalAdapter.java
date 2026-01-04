package net.happykoo.remittance.adapter.out.external.bank;

import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.ExternalSystemAdapter;
import net.happykoo.common.http.CommonHttpClient;
import net.happykoo.remittance.application.port.out.RequestFirmBankingPort;
import net.happykoo.remittance.application.port.out.RequestMembershipInfoPort;
import net.happykoo.remittance.application.port.out.data.MembershipData;
import net.happykoo.remittance.application.port.out.payload.FirmBankingPayload;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@Slf4j
public class BankExternalAdapter implements RequestFirmBankingPort {

  private final CommonHttpClient commonHttpClient;
  private final String bankServiceUrl;

  public BankExternalAdapter(
      CommonHttpClient commonHttpClient,
      @Value("${service.banking.url}") String bankServiceUrl
  ) {
    this.commonHttpClient = commonHttpClient;
    this.bankServiceUrl = bankServiceUrl;
  }

  @Override
  public boolean requestFirmBanking(FirmBankingPayload payload) {
    //TODO: Bank service 호출
    return true;
  }
}
