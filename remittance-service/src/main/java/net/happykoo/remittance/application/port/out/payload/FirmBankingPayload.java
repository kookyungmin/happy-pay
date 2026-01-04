package net.happykoo.remittance.application.port.out.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirmBankingPayload {

  private String toBankName;
  private String toBankAccountNumber;
  private int moneyAmount;
}
