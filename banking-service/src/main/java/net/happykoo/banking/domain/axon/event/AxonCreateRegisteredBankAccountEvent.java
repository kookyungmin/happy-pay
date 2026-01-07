package net.happykoo.banking.domain.axon.event;

public record AxonCreateRegisteredBankAccountEvent(
    String aggregateId,
    String membershipId,
    String bankName,
    String bankAccountNumber
) {

}
