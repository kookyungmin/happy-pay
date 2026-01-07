package net.happykoo.banking.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registered_bank_account")
@Data
@NoArgsConstructor
public class JpaRegisteredBankAccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String membershipId;
  private String bankName;
  private String bankAccountNumber;
  private boolean isValid;
  private String eventStreamId;

  public JpaRegisteredBankAccountEntity(
      Long id,
      String membershipId,
      String bankName,
      String bankAccountNumber,
      boolean isValid,
      String eventStreamId
  ) {
    this.id = id;
    this.membershipId = membershipId;
    this.bankName = bankName;
    this.bankAccountNumber = bankAccountNumber;
    this.isValid = isValid;
  }

  public JpaRegisteredBankAccountEntity(
      String membershipId,
      String bankName,
      String bankAccountNumber,
      boolean isValid,
      String eventStreamId
  ) {
    this(null, membershipId, bankName, bankAccountNumber, isValid, eventStreamId);
  }
}
