package net.happykoo.banking.application.axon.saga;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.banking.application.axon.command.AxonUpdateFirmBankingRequestStatusCommand;
import net.happykoo.banking.application.port.out.RequestBankAccountInfoPort;
import net.happykoo.banking.application.port.out.RequestFirmBankingPort;
import net.happykoo.banking.application.port.out.payload.BankAccountPayload;
import net.happykoo.banking.application.port.out.payload.FirmBankingPayload;
import net.happykoo.banking.domain.FirmBankingRequestStatus;
import net.happykoo.banking.domain.axon.event.AxonCreateFirmBankingRequestEvent;
import net.happykoo.banking.domain.axon.event.AxonUpdateFirmBankingRequestStatusEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class AxonFirmBankingSaga {

  @Autowired
  private transient RequestBankAccountInfoPort requestBankAccountInfoPort;
  @Autowired
  private transient RequestFirmBankingPort requestFirmBankingPort;
  @Autowired
  private transient CommandGateway commandGateway;

  @StartSaga
  @SagaEventHandler(associationProperty = "aggregateId")
  public void on(AxonCreateFirmBankingRequestEvent event) {
    log.info("AxonCreateFirmBankingRequestEvent Saga Handler >>> {}", event);
    var fromBankData = requestBankAccountInfoPort.requestBankAccountInfo(
        new BankAccountPayload(event.fromBankName(), event.fromBankAccountNumber())
    );
    var toBankData = requestBankAccountInfoPort.requestBankAccountInfo(
        new BankAccountPayload(event.toBankName(), event.toBankAccountNumber())
    );

    if (!fromBankData.isValid() || !toBankData.isValid()) {
      sendErrorStatusCommand(event.aggregateId(), "account is not valid.");
    } else {
      //3. 외부 은행에 펌뱅킹 요청
      var result = requestFirmBankingPort.requestFirmBanking(new FirmBankingPayload(
          event.fromBankName(),
          event.fromBankAccountNumber(),
          event.toBankName(),
          event.toBankAccountNumber(),
          event.moneyAmount()
      ));
      if (result.isSuccess()) {
        sendSuccessStatusCommand(event.aggregateId());
      } else {
        sendErrorStatusCommand(event.aggregateId(), "failed to request firm banking.");
      }
    }
  }

  @EndSaga
  @SagaEventHandler(associationProperty = "aggregateId")
  public void on(AxonUpdateFirmBankingRequestStatusEvent event) {
    log.info("Saga ended: {}", event.aggregateId());
  }

  private void sendSuccessStatusCommand(String aggregateId) {
    AxonUpdateFirmBankingRequestStatusCommand command = new AxonUpdateFirmBankingRequestStatusCommand(
        aggregateId,
        FirmBankingRequestStatus.SUCCESS,
        null
    );
    commandGateway.send(command);
  }

  private void sendErrorStatusCommand(String aggregateId, String errorMsg) {
    AxonUpdateFirmBankingRequestStatusCommand command = new AxonUpdateFirmBankingRequestStatusCommand(
        aggregateId,
        FirmBankingRequestStatus.FAILED,
        errorMsg
    );
    commandGateway.send(command);
  }
}
