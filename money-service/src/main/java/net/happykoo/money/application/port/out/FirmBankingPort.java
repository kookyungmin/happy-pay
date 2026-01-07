package net.happykoo.money.application.port.out;

import net.happykoo.money.application.port.out.payload.FirmBankingPayload;

public interface FirmBankingPort {

  void firmBanking(FirmBankingPayload payload);

}
