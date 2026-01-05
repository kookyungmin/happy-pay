package net.happykoo.money.domain.axon.event;


public record AxonCreateMemberMoneyEvent(
    String aggregateId,
    Long membershipId
) {

}
