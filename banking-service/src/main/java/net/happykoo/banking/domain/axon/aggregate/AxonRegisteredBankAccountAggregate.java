package net.happykoo.banking.domain.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.banking.application.axon.command.AxonCheckRegisteredBankAccountCommand;
import net.happykoo.banking.application.axon.command.AxonCreateRegisteredBankAccountCommand;
import net.happykoo.banking.domain.axon.event.AxonCreateRegisteredBankAccountEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Getter
@NoArgsConstructor
@Slf4j
public class AxonRegisteredBankAccountAggregate {

  @AggregateIdentifier
  private String id;

  private String membershipId;

  private String bankName;

  private String bankAccountNumber;

  @CommandHandler
  public AxonRegisteredBankAccountAggregate(AxonCreateRegisteredBankAccountCommand command) {
    log.info("AxonCreateRegisteredBankAccountCommand Handler >>> {}", command);
    var event = new AxonCreateRegisteredBankAccountEvent(
        command.aggregateId(),
        command.membershipId(),
        command.bankName(),
        command.bankAccountNumber()
    );

    apply(event);
  }

  @CommandHandler
  public void checkRegisteredBankAccount(AxonCheckRegisteredBankAccountCommand command) {
    log.info("AxonCheckRegisteredBankAccountCommand Handler >>> {}", command);

  }

  @EventSourcingHandler
  public void on(AxonCreateRegisteredBankAccountEvent event) {
    log.info("AxonCreateRegisteredBankAccountEvent Sourcing Handler >>> {}", event);

    id = event.aggregateId();
    membershipId = event.membershipId();
    bankName = event.bankName();
    bankAccountNumber = event.bankAccountNumber();
  }
}
