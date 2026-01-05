package net.happykoo.money.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_money")
@Data
@NoArgsConstructor
public class JpaMemberMoneyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String membershipId;
  private int balance;
  private String eventStreamId;

  public JpaMemberMoneyEntity(
      Long id,
      String membershipId,
      int balance,
      String eventStreamId
  ) {
    this.id = id;
    this.membershipId = membershipId;
    this.balance = balance;
    this.eventStreamId = eventStreamId;
  }

  public JpaMemberMoneyEntity(
      String membershipId,
      int balance,
      String eventStreamId
  ) {
    this(null, membershipId, balance, eventStreamId);
  }
}
