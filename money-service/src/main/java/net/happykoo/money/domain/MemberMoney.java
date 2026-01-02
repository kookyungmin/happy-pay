package net.happykoo.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberMoney {

  private final String memberMoneyId;
  private final String membershipId;
  private final int balance;

  public static MemberMoney generateMemberMoney(
      MemberMoneyId memberMoneyId,
      MembershipId membershipId,
      Balance balance
  ) {
    return new MemberMoney(
        memberMoneyId.value(),
        membershipId.value(),
        balance.value()
    );
  }

  public record MemberMoneyId(String value) {

  }

  public record MembershipId(String value) {

  }

  public record Balance(int value) {

  }

}
