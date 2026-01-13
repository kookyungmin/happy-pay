package net.happykoo.membership.application.port.out;

public interface JwtTokenPort {

  String generateAccessToken(
      String membershipId
  );

  String generateRefreshToken(
      String membershipId
  );

  boolean validateToken(
      String token
  );

}
