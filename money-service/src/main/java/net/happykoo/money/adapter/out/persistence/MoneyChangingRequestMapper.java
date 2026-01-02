package net.happykoo.money.adapter.out.persistence;

import net.happykoo.money.adapter.out.persistence.jpa.entity.JpaMoneyChangingRequestEntity;
import net.happykoo.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {

  MoneyChangingRequest mapToDomainEntity(JpaMoneyChangingRequestEntity entity) {
    return MoneyChangingRequest.generateMoneyChangingRequest(
        new MoneyChangingRequest.MoneyChangingRequestId(entity.getId().toString()),
        new MoneyChangingRequest.TargetMembershipId(entity.getTargetMembershipId()),
        new MoneyChangingRequest.RequestType(entity.getRequestType()),
        new MoneyChangingRequest.MoneyAmount(entity.getMoneyAmount()),
        new MoneyChangingRequest.RequestStatus(entity.getStatus()),
        new MoneyChangingRequest.Message(entity.getMessage())
    );
  }
}
