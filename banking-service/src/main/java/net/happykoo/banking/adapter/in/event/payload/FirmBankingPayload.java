package net.happykoo.banking.adapter.in.event.payload;

public record FirmBankingPayload(
    String rechargingRequestId,
    String fromBankName,
    String fromBankAccount,
    String toBankName,
    String toBankAccount,
    int moneyAmount
) {

}
