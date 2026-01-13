package net.happykoo.membership.application.service;

import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.UseCase;
import net.happykoo.membership.application.port.in.AuthMembershipUseCase;
import net.happykoo.membership.application.port.in.command.LoginMembershipCommand;
import net.happykoo.membership.application.port.out.FindMembershipPort;
import net.happykoo.membership.application.port.out.JwtTokenPort;
import net.happykoo.membership.domain.JwtToken;
import net.happykoo.membership.domain.JwtToken.AccessToken;
import net.happykoo.membership.domain.JwtToken.MembershipId;
import net.happykoo.membership.domain.JwtToken.RefreshToken;
import net.happykoo.membership.domain.Membership;
import net.happykoo.membership.domain.Membership.MembershipEmail;

//TODO: 원래 인증 로직은 JWT 에 대한 의존성을 가지면 안됨 -> 추상화
@UseCase
@RequiredArgsConstructor
public class AuthMembershipService implements AuthMembershipUseCase {

  private final JwtTokenPort jwtTokenPort;
  private final FindMembershipPort findMembershipPort;

  @Override
  public JwtToken loginMembership(LoginMembershipCommand command) {
    Membership membership = findMembershipPort.findMembershipByEmail(
        new MembershipEmail(command.getEmail())
    );

    //TODO: 비번 체크

    String accessToken = jwtTokenPort.generateAccessToken(membership.getMembershipId());
    String refreshToken = jwtTokenPort.generateRefreshToken(membership.getMembershipId());

    return JwtToken.generateJwtToken(
        new MembershipId(membership.getMembershipId()),
        new AccessToken(accessToken),
        new RefreshToken(refreshToken)
    );
  }
}
