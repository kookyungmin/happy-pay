package net.happykoo.banking.application.axon.command;

public record AxonCreateFirmBankingRequestCommand(
    String aggregateId,
    String fromBankName,
    String fromBankAccountNumber,
    String toBankName,
    String toBankAccountNumber,
    int moneyAmount,
    String firmBankingRequestId
) {

}
