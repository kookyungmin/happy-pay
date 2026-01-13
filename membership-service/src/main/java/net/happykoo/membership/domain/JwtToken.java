package net.happykoo.membership.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class JwtToken {

  private String membershipId;
  private String accessToken;
  private String refreshToken;

  public static JwtToken generateJwtToken(
      MembershipId membershipId,
      AccessToken accessToken,
      RefreshToken refreshToken
  ) {
    return new JwtToken(
        membershipId.value(),
        accessToken.value(),
        refreshToken.value()
    );
  }

  public record MembershipId(String value) {

  }

  public record AccessToken(String value) {

  }

  public record RefreshToken(String value) {

  }

}
