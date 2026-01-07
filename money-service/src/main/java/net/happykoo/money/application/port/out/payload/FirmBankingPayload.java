package net.happykoo.money.application.port.out.payload;

public record FirmBankingPayload(
    String rechargingRequestId,
    String fromBankName,
    String fromBankAccount,
    String toBankName,
    String toBankAccount,
    int moneyAmount
) {

}
