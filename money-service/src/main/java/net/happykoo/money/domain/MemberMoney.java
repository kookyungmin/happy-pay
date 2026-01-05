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
  private final String eventStreamId;

  public static MemberMoney generateMemberMoney(
      MemberMoneyId memberMoneyId,
      MembershipId membershipId,
      Balance balance,
      EventStreamId eventStreamId
  ) {
    return new MemberMoney(
        memberMoneyId.value(),
        membershipId.value(),
        balance.value(),
        eventStreamId.value()
    );
  }

  public record MemberMoneyId(String value) {

  }

  public record MembershipId(String value) {

  }

  public record Balance(int value) {

  }

  public record EventStreamId(String value) {

  }

}
