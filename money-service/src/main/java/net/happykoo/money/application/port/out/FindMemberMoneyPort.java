package net.happykoo.money.application.port.out;

import net.happykoo.money.domain.MemberMoney;

public interface FindMemberMoneyPort {

  MemberMoney findMemberMoneyByMembershipId(
      MemberMoney.MembershipId membershipId
  );
}
