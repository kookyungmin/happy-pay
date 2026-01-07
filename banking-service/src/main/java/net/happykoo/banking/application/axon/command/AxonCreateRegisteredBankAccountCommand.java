package net.happykoo.banking.application.axon.command;

public record AxonCreateRegisteredBankAccountCommand(
    String aggregateId,
    String membershipId,
    String bankName,
    String bankAccountNumber
) {

}
