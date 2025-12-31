package net.happykoo.membership.application.port.out;

import net.happykoo.membership.domain.Membership;

public interface FindMembershipPort {

  Membership findMembership(Membership.MembershipId membershipId);
}
