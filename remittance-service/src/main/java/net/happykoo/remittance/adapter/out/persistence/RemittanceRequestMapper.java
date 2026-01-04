package net.happykoo.remittance.adapter.out.persistence;

import net.happykoo.remittance.adapter.out.persistence.jpa.JpaRemittanceRequestEntity;
import net.happykoo.remittance.domain.RemittanceRequest;
import net.happykoo.remittance.domain.RemittanceRequest.Amount;
import net.happykoo.remittance.domain.RemittanceRequest.FromMembershipId;
import net.happykoo.remittance.domain.RemittanceRequest.Message;
import net.happykoo.remittance.domain.RemittanceRequest.RemittanceRequestId;
import net.happykoo.remittance.domain.RemittanceRequest.RemittanceStatus;
import net.happykoo.remittance.domain.RemittanceRequest.RemittanceType;
import net.happykoo.remittance.domain.RemittanceRequest.ToBankAccountNumber;
import net.happykoo.remittance.domain.RemittanceRequest.ToBankName;
import net.happykoo.remittance.domain.RemittanceRequest.ToMembershipId;
import org.springframework.stereotype.Component;

@Component
public class RemittanceRequestMapper {

  RemittanceRequest mapToDomainEntity(JpaRemittanceRequestEntity entity) {
    return RemittanceRequest.generateRemittanceRequest(
        new RemittanceRequestId(entity.getId().toString()),
        new FromMembershipId(entity.getFromMembershipId()),
        new ToMembershipId(entity.getToMembershipId()),
        new ToBankName(entity.getToBankName()),
        new ToBankAccountNumber(entity.getToBankAccountNumber()),
        new RemittanceType(entity.getRemittanceType()),
        new Amount(entity.getAmount()),
        new RemittanceStatus(entity.getRemittanceStatus()),
        new Message(entity.getErrorMsg())
    );
  }
}
