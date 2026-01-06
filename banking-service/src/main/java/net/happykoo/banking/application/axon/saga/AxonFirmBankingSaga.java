package net.happykoo.banking.application.axon.saga;

import lombok.RequiredArgsConstructor;
import net.happykoo.banking.application.axon.command.AxonUpdateFirmBankingRequestStatusCommand;
import net.happykoo.banking.application.port.out.RequestBankAccountInfoPort;
import net.happykoo.banking.application.port.out.RequestFirmBankingPort;
import net.happykoo.banking.application.port.out.payload.BankAccountPayload;
import net.happykoo.banking.application.port.out.payload.FirmBankingPayload;
import net.happykoo.banking.domain.FirmBankingRequestStatus;
import net.happykoo.banking.domain.axon.event.AxonCreateFirmBankingRequestEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.spring.stereotype.Saga;

@Saga
@RequiredArgsConstructor
public class AxonFirmBankingSaga {

  private final RequestBankAccountInfoPort requestBankAccountInfoPort;
  private final RequestFirmBankingPort requestFirmBankingPort;
  private final CommandGateway commandGateway;

  @SagaEventHandler(associationProperty = "aggregateId")
  public void on(AxonCreateFirmBankingRequestEvent event) {
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
