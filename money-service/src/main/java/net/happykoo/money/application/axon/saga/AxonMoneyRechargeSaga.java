package net.happykoo.money.application.axon.saga;

import lombok.extern.slf4j.Slf4j;
import net.happykoo.money.application.port.out.FindRegisteredBankAccountPort;
import net.happykoo.money.application.port.out.FirmBankingPort;
import net.happykoo.money.application.port.out.payload.FindRegisteredBankAccountPayload;
import net.happykoo.money.application.port.out.payload.FirmBankingPayload;
import net.happykoo.money.domain.axon.event.AxonRechargeMoneyEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class AxonMoneyRechargeSaga {

  @Autowired
  private transient CommandGateway commandGateway;

  @Autowired
  private transient FindRegisteredBankAccountPort findRegisteredBankAccountPort;

  @Autowired
  private transient FirmBankingPort firmBankingPort;

  @StartSaga
  @SagaEventHandler(associationProperty = "rechargingRequestId")
  public void on(AxonRechargeMoneyEvent event) {
    log.info("AxonRechargeMoneyEvent Saga Handler >>> {}", event);

    var registeredBankAccountInfo = findRegisteredBankAccountPort.findRegisteredBankAccountInfo(
        new FindRegisteredBankAccountPayload(String.valueOf(event.membershipId()))
    );

    if (registeredBankAccountInfo == null
        || !registeredBankAccountInfo.isValid()) {
      log.error("account is not valid.");
      SagaLifecycle.end();
      return;
    }

    firmBankingPort.firmBanking(new FirmBankingPayload(
        event.rechargingRequestId(),
        registeredBankAccountInfo.getBankName(),
        registeredBankAccountInfo.getBankAccountNumber(),
        "해피해피은행",
        "1234-1234-111",
        event.moneyAmount()
    ));
  }

//  @SagaEventHandler(associationProperty = "aggregateId")
//  public void on(AxonIncreaseMemberMoneyEvent event) {
//    log.info("AxonIncreaseMemberMoneyEvent Saga Handler >>> {}", event);
//
//    var command = new AxonCheckRegisteredBankAccountCommand(
//        event.aggregateId(),
//        event.membershipId()
//    );
//    commandGateway.send(command);
//  }


}
