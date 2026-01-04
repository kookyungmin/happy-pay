package net.happykoo.remittance.application.port.out.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyInfoData {

  private String membershipId;
  private int balance;
}
