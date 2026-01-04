package net.happykoo.remittance.application.port.out;

import net.happykoo.remittance.application.port.out.payload.FirmBankingPayload;

public interface RequestFirmBankingPort {

  boolean requestFirmBanking(FirmBankingPayload payload);
}
