package net.happykoo.money.application.port.out.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredBankAccountInfo {

  private String membershipId;
  private String bankName;
  private String bankAccountNumber;
  private boolean isValid;
}
