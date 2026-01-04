package net.happykoo.remittance.application.port.out;

public interface RequestMembershipInfoPort {

  boolean existsMembership(String membershipId);
}
