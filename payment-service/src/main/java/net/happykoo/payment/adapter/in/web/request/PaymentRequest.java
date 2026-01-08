package net.happykoo.payment.adapter.in.web.request;

public record PaymentRequest(
    String requestMembershipId,
    String requestPrice,
    String franchiseId,
    String franchiseFeeRate
) {

}
