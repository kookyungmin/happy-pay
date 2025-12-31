package net.happykoo.membership.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.happykoo.membership.adapter.in.web.request.RegisterMembershipRequest;
import net.happykoo.membership.application.port.in.RegisterMembershipUseCase;
import net.happykoo.membership.common.WebAdapter;
import net.happykoo.membership.domain.Membership;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

  private final RegisterMembershipUseCase registerMembershipUseCase;

  @PostMapping("/membership/register")
  ResponseEntity<Membership> registerMembership(@RequestBody RegisterMembershipRequest request) {
    Membership membership = registerMembershipUseCase.registerMembership(request.toCommand());

    return ResponseEntity.ok(membership);
  }
}
