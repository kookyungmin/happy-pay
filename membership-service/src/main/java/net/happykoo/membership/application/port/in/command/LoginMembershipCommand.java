package net.happykoo.membership.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.happykoo.common.validation.SelfValidating;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginMembershipCommand extends SelfValidating<LoginMembershipCommand> {

  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;

  public LoginMembershipCommand(String email, String password) {
    this.email = email;
    this.password = password;
    validateSelf();
  }
}
