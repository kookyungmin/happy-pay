package net.happykoo.membership.adapter.in.web.request;

import net.happykoo.membership.application.port.in.command.RegisterMembershipCommand;

public record RegisterMembershipRequest(
    String name,
    String address,
    String email,
    boolean isCorp
) {

  public RegisterMembershipCommand toCommand() {
    return RegisterMembershipCommand.builder()
        .name(name)
        .address(address)
        .email(email)
        .isValid(true)
        .isCorp(isCorp)
        .build();
  }

}
