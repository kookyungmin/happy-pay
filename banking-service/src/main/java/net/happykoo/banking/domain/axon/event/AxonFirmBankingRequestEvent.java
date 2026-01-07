package net.happykoo.banking.domain.axon.event;

public record AxonFirmBankingRequestEvent(
    String firmBankingRequestId,
    String fromBankName,
    String fromBankAccount,
    String toBankName,
    String toBankAccount,
    int moneyAmount,
    String externalRequestId

) {

}
