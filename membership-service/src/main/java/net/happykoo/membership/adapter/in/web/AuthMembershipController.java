package net.happykoo.membership.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.WebAdapter;
import net.happykoo.membership.adapter.in.web.request.LoginMembershipRequest;
import net.happykoo.membership.application.port.in.AuthMembershipUseCase;
import net.happykoo.membership.application.port.in.command.LoginMembershipCommand;
import net.happykoo.membership.domain.JwtToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter
@RequiredArgsConstructor
public class AuthMembershipController {

  private final AuthMembershipUseCase authMembershipUseCase;

  @PostMapping("/membership/login")
  ResponseEntity<JwtToken> login(@RequestBody LoginMembershipRequest request) {
    LoginMembershipCommand command = new LoginMembershipCommand(
        request.email(),
        request.password()
    );

    return ResponseEntity.ok(authMembershipUseCase.loginMembership(command));
  }

}
