package net.happykoo.membership.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.UseCase;
import net.happykoo.membership.application.port.in.RegisterMembershipUseCase;
import net.happykoo.membership.application.port.in.command.RegisterMembershipCommand;
import net.happykoo.membership.application.port.out.FindSecretValuePort;
import net.happykoo.membership.application.port.out.RegisterMembershipPort;
import net.happykoo.membership.domain.Membership;
import net.happykoo.membership.domain.Membership.MembershipAddress;
import net.happykoo.membership.domain.Membership.MembershipEmail;
import net.happykoo.membership.domain.Membership.MembershipIsCorp;
import net.happykoo.membership.domain.Membership.MembershipName;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class RegisterMembershipService implements RegisterMembershipUseCase {

  private final RegisterMembershipPort registerMembershipPort;
  private final FindSecretValuePort findSecretValuePort;

  @Override
  public Membership registerMembership(RegisterMembershipCommand command) {
    log.info("secret key : {}", findSecretValuePort.findEncryptionKey());

    return registerMembershipPort.createMembership(
        new MembershipName(command.getName()),
        new MembershipEmail(command.getEmail()),
        new MembershipAddress(command.getAddress()),
        new MembershipIsCorp(command.isCorp())
    );
  }
}
