package net.happykoo.money.domain.axon.aggregate;


import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.money.application.axon.command.AxonCreateMemberMoneyCommand;
import net.happykoo.money.application.axon.command.AxonIncreaseMemberMoneyCommand;
import net.happykoo.money.domain.axon.event.AxonCreateMemberMoneyEvent;
import net.happykoo.money.domain.axon.event.AxonIncreaseMemberMoneyEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@NoArgsConstructor
@Getter
public class AxonMemberMoneyAggregate {

  @AggregateIdentifier
  private String id;

  private Long membershipId;

  private int balance;

  @CommandHandler
  public AxonMemberMoneyAggregate(AxonCreateMemberMoneyCommand command) {
    log.info("AxonCreateMemberMoneyCommand Handler >>> {}", command);

    apply(new AxonCreateMemberMoneyEvent(command.aggregateId(), command.membershipId()));
  }

  @CommandHandler
  public void increaseBalance(AxonIncreaseMemberMoneyCommand command) {
    log.info("AxonIncreaseMemberMoneyCommand Handler >>> {}", command);
    apply(new AxonIncreaseMemberMoneyEvent(command.aggregateId(), command.membershipId(),
        command.moneyAmount()));
  }

  @EventSourcingHandler
  public void on(AxonCreateMemberMoneyEvent event) {
    log.info("AxonCreateMemberMoneyEvent Sourcing Handler >>> {}", event);

    this.id = event.aggregateId();
    this.membershipId = event.membershipId();
    this.balance = 0;
  }

  @EventSourcingHandler
  public void on(AxonIncreaseMemberMoneyEvent event) {
    log.info("AxonIncreaseMemberMoneyEvent Sourcing Handler >>> {}", event);

    this.id = event.aggregateId();
    this.membershipId = event.membershipId();
    this.balance = event.moneyAmount();
  }
}
