package net.happykoo.remittance.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.happykoo.common.validation.SelfValidating;
import net.happykoo.remittance.domain.RemittanceRequestType;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

  @NotNull
  private String fromMembershipId; // from membership

  private String toMembershipId; // to membership

  private String toBankName;

  private String toBankAccountNumber;

  private RemittanceRequestType remittanceType;

  // 송금요청 금액
  @NotNull
  @NotBlank
  private int amount;

  public RequestRemittanceCommand(
      String fromMembershipId,
      String toMembershipId,
      String toBankName,
      String toBankAccountNumber,
      RemittanceRequestType remittanceType,
      int amount
  ) {
    this.fromMembershipId = fromMembershipId;
    this.toMembershipId = toMembershipId;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.remittanceType = remittanceType;
    this.amount = amount;

    this.validateSelf();
  }
}
