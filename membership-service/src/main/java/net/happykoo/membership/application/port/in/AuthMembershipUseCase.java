package net.happykoo.membership.application.port.in;

import net.happykoo.membership.application.port.in.command.LoginMembershipCommand;
import net.happykoo.membership.domain.JwtToken;

public interface AuthMembershipUseCase {

  JwtToken loginMembership(LoginMembershipCommand command);

}
