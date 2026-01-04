package net.happykoo.remittance.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.happykoo.common.validation.SelfValidating;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindRemittanceCommand extends SelfValidating<FindRemittanceCommand> {

  @NotNull
  private String membershipId; // from membership

  public FindRemittanceCommand(String membershipId) {
    this.membershipId = membershipId;
    this.validateSelf();
  }
}
