package net.happykoo.money.application.axon.command;


public record AxonCreateMemberMoneyCommand(
    String aggregateId,
    Long membershipId
) {

}
