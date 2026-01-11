package net.happykoo.moneyagg.adapter.out.external.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMoneyResponse {

  private String memberMoneyId;
  private String membershipId;
  private int balance;
  private String eventStreamId;
}
