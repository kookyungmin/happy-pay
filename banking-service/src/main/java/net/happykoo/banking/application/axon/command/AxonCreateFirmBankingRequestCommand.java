package net.happykoo.banking.application.axon.command;

import net.happykoo.banking.domain.FirmBankingRequestStatus;

public record AxonCreateFirmBankingRequestCommand(
    String aggregateId,
    String fromBankName,
    String fromBankAccountNumber,
    String toBankName,
    String toBankAccountNumber,
    int moneyAmount
) {

}
