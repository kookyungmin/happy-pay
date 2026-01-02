package net.happykoo.money.adapter.in.web.request;

public record IncreaseMoneyRequest(
    String targetMembershipId,
    int moneyAmount
) {

}
