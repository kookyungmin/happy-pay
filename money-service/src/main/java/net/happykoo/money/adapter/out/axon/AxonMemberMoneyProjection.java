package net.happykoo.money.adapter.out.axon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.EventAdapter;
import net.happykoo.money.adapter.out.persistence.jpa.JpaMemberMoneyRepository;
import net.happykoo.money.adapter.out.persistence.jpa.entity.JpaMemberMoneyEntity;
import net.happykoo.money.domain.axon.event.AxonCreateMemberMoneyEvent;
import net.happykoo.money.domain.axon.event.AxonIncreaseMemberMoneyEvent;
import org.axonframework.eventhandling.EventHandler;

@EventAdapter
@RequiredArgsConstructor
@Slf4j
public class AxonMemberMoneyProjection {

  private final JpaMemberMoneyRepository jpaMemberMoneyRepository;

  @EventHandler
  public void on(AxonCreateMemberMoneyEvent event) {
    log.info("AxonCreateMemberMoneyEvent Handler >>> {}", event);
    var isExists = jpaMemberMoneyRepository.existsByMembershipId(
        String.valueOf(event.membershipId()));

    if (isExists) {
      throw new IllegalArgumentException("entity already exists : " + event.membershipId());
    }

    var entity = new JpaMemberMoneyEntity(
        String.valueOf(event.membershipId()),
        0,
        event.aggregateId()
    );
    jpaMemberMoneyRepository.save(entity);
  }

  @EventHandler
  public void on(AxonIncreaseMemberMoneyEvent event) {
    log.info("AxonIncreaseMemberMoneyEvent Handler >>> {}", event);
    var entity = jpaMemberMoneyRepository.findByMembershipId(String.valueOf(event.membershipId()))
        .orElseThrow(
            () -> new IllegalArgumentException("entity does not exist : " + event.membershipId()));

    entity.setBalance(entity.getBalance() + event.moneyAmount());

    jpaMemberMoneyRepository.save(entity);
  }
}
