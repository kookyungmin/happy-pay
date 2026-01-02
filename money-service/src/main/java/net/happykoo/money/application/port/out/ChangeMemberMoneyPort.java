package net.happykoo.money.application.port.out;

import net.happykoo.money.domain.MemberMoney;

public interface ChangeMemberMoneyPort {

  void increaseMemberMoney(
      MemberMoney.MembershipId membershipId,
      MemberMoney.Balance balance
  );

}
