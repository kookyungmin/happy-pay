package net.happykoo.remittance.application.port.out.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingPayload {

  private String membershipId;
  private int moneyAmount;
}
