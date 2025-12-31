package net.happykoo.membership.application.port.in;

import net.happykoo.membership.application.port.in.command.FindMembershipCommand;
import net.happykoo.membership.domain.Membership;

public interface FindMembershipUseCase {

  Membership findMembership(FindMembershipCommand command);

}
