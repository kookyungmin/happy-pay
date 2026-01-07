package net.happykoo.banking.adapter.out.axon;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.banking.adapter.out.persistence.jpa.JpaFirmBankingRequestRepository;
import net.happykoo.banking.adapter.out.persistence.jpa.entity.JpaFirmBankingRequestEntity;
import net.happykoo.banking.domain.FirmBankingRequestStatus;
import net.happykoo.banking.domain.axon.event.AxonCreateFirmBankingRequestEvent;
import net.happykoo.banking.domain.axon.event.AxonUpdateFirmBankingRequestStatusEvent;
import net.happykoo.common.annotation.EventAdapter;
import org.axonframework.eventhandling.EventHandler;

@EventAdapter
@RequiredArgsConstructor
@Slf4j
public class AxonFirmBankingProjection {

  private final JpaFirmBankingRequestRepository jpaFirmBankingRequestRepository;

  @EventHandler
  public void on(AxonCreateFirmBankingRequestEvent event) {
    JpaFirmBankingRequestEntity entity = new JpaFirmBankingRequestEntity(
        event.fromBankName(),
        event.fromBankAccountNumber(),
        event.toBankName(),
        event.toBankAccountNumber(),
        event.moneyAmount(),
        FirmBankingRequestStatus.REQUESTED,
        null,
        event.aggregateId()
    );

    jpaFirmBankingRequestRepository.save(entity);
  }

  @EventHandler
  public void on(AxonUpdateFirmBankingRequestStatusEvent event) {
    jpaFirmBankingRequestRepository.findByEventStreamId(
            event.aggregateId())
        .ifPresentOrElse(
            entity -> {
              entity.setStatus(event.status());
              entity.setErrorMsg(event.errorMessage());

              jpaFirmBankingRequestRepository.save(entity);
            }, () -> {
              log.error("entity does not exists : {}", event.aggregateId());
            }
        );
  }
}
