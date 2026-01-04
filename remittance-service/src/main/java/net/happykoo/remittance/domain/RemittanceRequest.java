package net.happykoo.remittance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RemittanceRequest { // 송금 요청에 대한 상태 클래스.

  private final String remittanceRequestId;
  private final String fromMembershipId;
  private final String toMembershipId;
  private final String toBankName;
  private final String toBankAccountNumber;
  private RemittanceRequestType remittanceType;
  // 송금요청 금액
  private int amount;
  private RemittanceRequestStatus remittanceStatus;
  private String message;

  public static RemittanceRequest generateRemittanceRequest(
      RemittanceRequestId remittanceRequestId,
      FromMembershipId fromMembershipId,
      ToMembershipId toMembershipId,
      ToBankName toBankName,
      ToBankAccountNumber toBankAccountNumber,
      RemittanceType remittanceType,
      Amount amount,
      RemittanceStatus remittanceStatus,
      Message message
  ) {
    return new RemittanceRequest(
        remittanceRequestId.value,
        fromMembershipId.value,
        toMembershipId.value,
        toBankName.value,
        toBankAccountNumber.value,
        remittanceType.value,
        amount.value,
        remittanceStatus.value,
        message.value
    );
  }

  public void success() {
    this.remittanceStatus = RemittanceRequestStatus.SUCCESS;
  }

  public void failed(String message) {
    this.remittanceStatus = RemittanceRequestStatus.FAILED;
    this.message = message;
  }

  public record RemittanceRequestId(String value) {

  }

  public record FromMembershipId(String value) {

  }

  public record ToMembershipId(String value) {

  }

  public record ToBankName(String value) {

  }

  public record ToBankAccountNumber(String value) {

  }

  public record Amount(int value) {

  }

  public record RemittanceType(RemittanceRequestType value) {

  }

  public record RemittanceStatus(RemittanceRequestStatus value) {

  }

  public record Message(String value) {

  }
}
