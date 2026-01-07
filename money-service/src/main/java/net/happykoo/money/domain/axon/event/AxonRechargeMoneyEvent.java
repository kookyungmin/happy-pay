package net.happykoo.money.domain.axon.event;

public record AxonRechargeMoneyEvent(
    String rechargingRequestId,
    Long membershipId,
    int moneyAmount
) {

}
