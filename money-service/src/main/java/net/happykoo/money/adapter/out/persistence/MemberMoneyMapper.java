package net.happykoo.money.adapter.out.persistence;

import net.happykoo.money.adapter.out.persistence.jpa.entity.JpaMemberMoneyEntity;
import net.happykoo.money.adapter.out.persistence.jpa.entity.JpaMoneyChangingRequestEntity;
import net.happykoo.money.domain.MemberMoney;
import net.happykoo.money.domain.MemberMoney.Balance;
import net.happykoo.money.domain.MemberMoney.EventStreamId;
import net.happykoo.money.domain.MemberMoney.MemberMoneyId;
import net.happykoo.money.domain.MemberMoney.MembershipId;
import net.happykoo.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyMapper {

  MemberMoney mapToDomainEntity(JpaMemberMoneyEntity entity) {
    return MemberMoney.generateMemberMoney(
        new MemberMoneyId(entity.getId().toString()),
        new MembershipId(entity.getMembershipId()),
        new Balance(entity.getBalance()),
        new EventStreamId(entity.getEventStreamId())
    );
  }
}
