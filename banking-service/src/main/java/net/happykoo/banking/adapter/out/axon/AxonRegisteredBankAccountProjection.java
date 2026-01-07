package net.happykoo.banking.adapter.out.axon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.banking.adapter.out.persistence.jpa.JpaRegisteredBankAccountRepository;
import net.happykoo.banking.adapter.out.persistence.jpa.entity.JpaRegisteredBankAccountEntity;
import net.happykoo.banking.domain.axon.event.AxonCreateRegisteredBankAccountEvent;
import net.happykoo.common.annotation.EventAdapter;
import org.axonframework.eventhandling.EventHandler;

@EventAdapter
@RequiredArgsConstructor
@Slf4j
public class AxonRegisteredBankAccountProjection {

  private final JpaRegisteredBankAccountRepository jpaRegisteredBankAccountRepository;

  @EventHandler
  public void on(AxonCreateRegisteredBankAccountEvent event) {
    var entity = new JpaRegisteredBankAccountEntity(
        event.membershipId(),
        event.bankName(),
        event.bankAccountNumber(),
        true,
        event.aggregateId()
    );

    jpaRegisteredBankAccountRepository.save(entity);
  }

}
