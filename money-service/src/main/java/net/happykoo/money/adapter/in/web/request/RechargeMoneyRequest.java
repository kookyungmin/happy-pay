package net.happykoo.money.adapter.in.web.request;

public record RechargeMoneyRequest(
    String targetMembershipId,
    int moneyAmount
) {

}
