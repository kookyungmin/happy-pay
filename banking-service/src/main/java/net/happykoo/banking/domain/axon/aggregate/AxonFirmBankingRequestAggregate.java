package net.happykoo.banking.domain.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.banking.application.axon.command.AxonCreateFirmBankingRequestCommand;
import net.happykoo.banking.application.axon.command.AxonUpdateFirmBankingRequestStatusCommand;
import net.happykoo.banking.domain.FirmBankingRequestStatus;
import net.happykoo.banking.domain.axon.event.AxonCreateFirmBankingRequestEvent;
import net.happykoo.banking.domain.axon.event.AxonUpdateFirmBankingRequestStatusEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Getter
@NoArgsConstructor
@Slf4j
public class AxonFirmBankingRequestAggregate {

  @AggregateIdentifier
  private String id;

  private String fromBankName;
  private String fromBankAccountNumber;
  private String toBankName;
  private String toBankAccountNumber;
  private int moneyAmount;
  private FirmBankingRequestStatus requestStatus;
  private String errorMsg;
  private String firmBankingRequestId;

  @CommandHandler
  public AxonFirmBankingRequestAggregate(AxonCreateFirmBankingRequestCommand command) {
    log.info("AxonCreateFirmBankingRequestCommand Handler >>> {}", command);

    apply(new AxonCreateFirmBankingRequestEvent(
        command.aggregateId(),
        command.fromBankName(),
        command.fromBankAccountNumber(),
        command.toBankName(),
        command.toBankAccountNumber(),
        command.moneyAmount(),
        command.firmBankingRequestId()
    ));
  }

  @EventSourcingHandler
  public void on(AxonCreateFirmBankingRequestEvent event) {
    log.info("AxonCreateFirmBankingRequestEvent Sourcing Handler >>> {}", event);

    this.id = event.aggregateId();
    this.fromBankName = event.fromBankName();
    this.fromBankAccountNumber = event.fromBankAccountNumber();
    this.toBankName = event.toBankName();
    this.toBankAccountNumber = event.toBankAccountNumber();
    this.moneyAmount = event.moneyAmount();
    this.requestStatus = FirmBankingRequestStatus.SUCCESS;
    this.firmBankingRequestId = event.firmBankingRequestId();
  }

  @CommandHandler
  public void updateStatus(AxonUpdateFirmBankingRequestStatusCommand command) {
    log.info("AxonUpdateFirmBankingRequestStatusCommand Handler >>> {}", command);

    apply(new AxonUpdateFirmBankingRequestStatusEvent(
        command.aggregateId(),
        command.status(),
        command.errorMessage(),
        command.firmBankingRequestId()
    ));
  }

  @EventSourcingHandler
  public void on(AxonUpdateFirmBankingRequestStatusEvent event) {
    log.info("AxonUpdateFirmBankingRequestStatusEvent Sourcing Handler >>> {}", event);

    this.requestStatus = event.status();
    this.errorMsg = event.errorMessage();
    this.firmBankingRequestId = event.firmBankingRequestId();
  }
}
