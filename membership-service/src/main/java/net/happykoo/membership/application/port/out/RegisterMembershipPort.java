package net.happykoo.membership.application.port.out;


import net.happykoo.membership.domain.Membership;

public interface RegisterMembershipPort {

  Membership createMembership(
      Membership.MembershipName membershipName,
      Membership.MembershipEmail membershipEmail,
      Membership.MembershipAddress membershipAddress,
      Membership.MembershipIsCorp membershipIsCorp
  );

}
