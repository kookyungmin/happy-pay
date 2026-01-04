package net.happykoo.banking.application.port.out;

import net.happykoo.banking.domain.RegisteredBankAccount;

public interface RequestMembershipInfoPort {

  boolean existsMembership(RegisteredBankAccount.MembershipId membershipId);
}
