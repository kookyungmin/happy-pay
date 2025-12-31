package net.happykoo.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Membership {

  private final String membershipId;

  private final String name;

  private final String email;

  private final String address;

  private final boolean isValid;

  private final boolean isCorp;

  public static Membership generateMember(
      MembershipId membershipId,
      MembershipName membershipName,
      MembershipEmail membershipEmail,
      MembershipAddress membershipAddress,
      MembershipIsValid membershipIsValid,
      MembershipIsCorp membershipIsCorp
  ) {
    return new Membership(
        membershipId.value(),
        membershipName.value(),
        membershipEmail.value(),
        membershipAddress.value(),
        membershipIsValid.value(),
        membershipIsCorp.value()
    );
  }


  public record MembershipId(String value) {

  }

  public record MembershipName(String value) {

  }

  public record MembershipEmail(String value) {

  }

  public record MembershipAddress(String value) {

  }

  public record MembershipIsValid(boolean value) {

  }

  public record MembershipIsCorp(boolean value) {

  }

}
