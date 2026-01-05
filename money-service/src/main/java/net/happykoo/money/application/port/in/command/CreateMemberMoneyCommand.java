package net.happykoo.money.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import net.happykoo.common.validation.SelfValidating;

@Getter
public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {

  @NotNull
  @NotBlank
  private final String membershipId;

  public CreateMemberMoneyCommand(
      String membershipId
  ) {
    this.membershipId = membershipId;
    validateSelf();
  }
}
