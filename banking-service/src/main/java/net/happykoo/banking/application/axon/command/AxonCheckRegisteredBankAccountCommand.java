package net.happykoo.banking.application.axon.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AxonCheckRegisteredBankAccountCommand(
    @TargetAggregateIdentifier
    String aggregateId,
    String membershipId
) {

}
