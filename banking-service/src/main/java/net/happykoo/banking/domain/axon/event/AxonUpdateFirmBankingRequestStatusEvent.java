package net.happykoo.banking.domain.axon.event;

import net.happykoo.banking.domain.FirmBankingRequestStatus;

public record AxonUpdateFirmBankingRequestStatusEvent(
    String aggregateId,
    FirmBankingRequestStatus status,
    String errorMessage
) {

}
