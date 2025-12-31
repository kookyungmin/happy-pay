package net.happykoo.membership.application.port.in;

import net.happykoo.membership.application.port.in.command.RegisterMembershipCommand;
import net.happykoo.membership.domain.Membership;

public interface RegisterMembershipUseCase {

  Membership registerMembership(RegisterMembershipCommand command);

}
