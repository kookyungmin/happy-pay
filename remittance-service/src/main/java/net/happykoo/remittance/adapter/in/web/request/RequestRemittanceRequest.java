package net.happykoo.remittance.adapter.in.web.request;

import net.happykoo.remittance.domain.RemittanceRequestType;

public record RequestRemittanceRequest(
    String fromMembershipId,
    String toMembershipId,
    String toBankName,
    String toBankAccountNumber,
    RemittanceRequestType remittanceType,
    int amount
) {

}
