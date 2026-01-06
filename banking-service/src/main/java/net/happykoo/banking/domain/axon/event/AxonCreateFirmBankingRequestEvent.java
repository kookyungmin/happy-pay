package net.happykoo.banking.domain.axon.event;

public record AxonCreateFirmBankingRequestEvent(
    String aggregateId,
    String fromBankName,
    String fromBankAccountNumber,
    String toBankName,
    String toBankAccountNumber,
    int moneyAmount

) {

}
