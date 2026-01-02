package net.happykoo.money.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.happykoo.money.domain.MoneyChangingRequestStatus;
import net.happykoo.money.domain.MoneyChangingRequestType;

@Entity
@Table(name = "money_changing_request")
@Data
@NoArgsConstructor
public class JpaMoneyChangingRequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String targetMembershipId;

  @Enumerated(value = EnumType.STRING)
  private MoneyChangingRequestType requestType;

  private int moneyAmount;

  @Enumerated(value = EnumType.STRING)
  private MoneyChangingRequestStatus status;

  private String message;

  public JpaMoneyChangingRequestEntity(
      Long id,
      String targetMembershipId,
      MoneyChangingRequestType requestType,
      int moneyAmount,
      MoneyChangingRequestStatus status,
      String message
  ) {
    this.id = id;
    this.targetMembershipId = targetMembershipId;
    this.requestType = requestType;
    this.moneyAmount = moneyAmount;
    this.status = status;
    this.message = message;
  }

  public JpaMoneyChangingRequestEntity(
      String targetMembershipId,
      MoneyChangingRequestType requestType,
      int moneyAmount,
      MoneyChangingRequestStatus status,
      String message
  ) {
    this(null, targetMembershipId, requestType, moneyAmount, status, message);
  }
}

