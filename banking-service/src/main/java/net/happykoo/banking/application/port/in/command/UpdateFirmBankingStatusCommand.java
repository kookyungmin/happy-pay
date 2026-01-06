package net.happykoo.banking.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.happykoo.banking.domain.FirmBankingRequestStatus;
import net.happykoo.common.validation.SelfValidating;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmBankingStatusCommand extends SelfValidating<UpdateFirmBankingStatusCommand> {

  private final String eventStreamId;
  private final FirmBankingRequestStatus status;
  private final String errorMessage;

  @Builder
  public UpdateFirmBankingStatusCommand(
      String eventStreamId,
      FirmBankingRequestStatus status,
      String errorMessage
  ) {
    this.eventStreamId = eventStreamId;
    this.status = status;
    this.errorMessage = errorMessage;

    validateSelf();
  }
}
