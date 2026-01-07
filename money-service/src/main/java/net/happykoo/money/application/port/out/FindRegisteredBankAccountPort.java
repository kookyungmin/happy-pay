package net.happykoo.money.application.port.out;

import net.happykoo.money.application.port.out.data.RegisteredBankAccountInfo;
import net.happykoo.money.application.port.out.payload.FindRegisteredBankAccountPayload;

public interface FindRegisteredBankAccountPort {

  RegisteredBankAccountInfo findRegisteredBankAccountInfo(FindRegisteredBankAccountPayload payload);
}
