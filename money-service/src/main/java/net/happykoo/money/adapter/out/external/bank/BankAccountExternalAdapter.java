package net.happykoo.money.adapter.out.external.bank;

import net.happykoo.common.annotation.ExternalSystemAdapter;
import net.happykoo.money.application.port.out.FindRegisteredBankAccountPort;
import net.happykoo.money.application.port.out.data.RegisteredBankAccountInfo;
import net.happykoo.money.application.port.out.payload.FindRegisteredBankAccountPayload;

@ExternalSystemAdapter
public class BankAccountExternalAdapter implements FindRegisteredBankAccountPort {

  @Override
  public RegisteredBankAccountInfo findRegisteredBankAccountInfo(
      FindRegisteredBankAccountPayload payload
  ) {
    return new RegisteredBankAccountInfo(
        payload.membershipId(),
        "해피은행",
        "1234",
        true
    );
  }
}
