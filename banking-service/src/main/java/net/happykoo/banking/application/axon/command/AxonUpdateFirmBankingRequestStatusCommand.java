package net.happykoo.banking.application.axon.command;

import net.happykoo.banking.domain.FirmBankingRequestStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AxonUpdateFirmBankingRequestStatusCommand(
    @TargetAggregateIdentifier
    String aggregateId,
    FirmBankingRequestStatus status,
    String errorMessage
) {

}
