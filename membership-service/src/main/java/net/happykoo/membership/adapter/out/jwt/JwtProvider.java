package net.happykoo.membership.adapter.out.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.membership.application.port.out.JwtTokenPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtProvider implements JwtTokenPort {

  private final SecretKey key;
  private static final long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60;
  private static final long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24;

  public JwtProvider(@Value("${jwt.secret-key}") String secretKey) {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }


  @Override
  public String generateAccessToken(String membershipId) {
    Claims claims = Jwts.claims()
        .subject(membershipId)
        .build();

    return generateToken(claims, ACCESS_TOKEN_VALID_TIME);
  }

  @Override
  public String generateRefreshToken(String membershipId) {
    Claims claims = Jwts.claims()
        .subject(membershipId)
        .build();

    return generateToken(claims, REFRESH_TOKEN_VALID_TIME);
  }

  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(key)
          .build()
          .parse(token);
      return true;
    } catch (Exception e) {
      log.error("invalid token : {}", e.getMessage());
      return false;
    }
  }

  private String generateToken(Claims claims, long validTime) {
    Date now = new Date();
    Date validateDate = new Date(now.getTime() + validTime);

    return Jwts.builder()
        .claims(claims)
        .expiration(validateDate)
        .issuedAt(now)
        .signWith(key)
        .compact();
  }

}
