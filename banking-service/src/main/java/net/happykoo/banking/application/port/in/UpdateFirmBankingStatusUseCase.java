package net.happykoo.banking.application.port.in;

import net.happykoo.banking.application.port.in.command.UpdateFirmBankingStatusCommand;

public interface UpdateFirmBankingStatusUseCase {

  void updateFirmBankingStatus(UpdateFirmBankingStatusCommand command);

}
