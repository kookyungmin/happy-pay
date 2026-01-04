package net.happykoo.remittance.adapter.out.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.happykoo.remittance.domain.RemittanceRequestStatus;
import net.happykoo.remittance.domain.RemittanceRequestType;

@Entity
@Table(name = "remittance_request")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JpaRemittanceRequestEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String fromMembershipId; // from membership
  private String toMembershipId; // to membership
  private String toBankName;
  private String toBankAccountNumber;
  @Enumerated(EnumType.STRING)
  private RemittanceRequestType remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)
  // 송금요청 금액
  private int amount;
  @Enumerated(EnumType.STRING)
  private RemittanceRequestStatus remittanceStatus;
  private String errorMsg;

  public JpaRemittanceRequestEntity(
      String fromMembershipId,
      String toMembershipId,
      String toBankName,
      String toBankAccountNumber,
      RemittanceRequestType remittanceType,
      int amount,
      RemittanceRequestStatus remittanceStatus,
      String errorMsg
  ) {
    this(null, fromMembershipId, toMembershipId, toBankName, toBankAccountNumber, remittanceType,
        amount, remittanceStatus, errorMsg);
  }
}
