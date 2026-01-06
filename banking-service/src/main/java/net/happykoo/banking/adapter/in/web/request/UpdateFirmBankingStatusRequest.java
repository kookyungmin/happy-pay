package net.happykoo.banking.adapter.in.web.request;

import net.happykoo.banking.domain.FirmBankingRequestStatus;

public record UpdateFirmBankingStatusRequest(
    String eventStreamId,
    FirmBankingRequestStatus status,
    String errorMessage
) {

}
