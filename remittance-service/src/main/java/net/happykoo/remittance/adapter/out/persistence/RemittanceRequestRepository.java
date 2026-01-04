package net.happykoo.remittance.adapter.out.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.PersistenceAdapter;
import net.happykoo.remittance.adapter.out.persistence.jpa.JpaRemittanceRequestEntity;
import net.happykoo.remittance.adapter.out.persistence.jpa.JpaRemittanceRequestRepository;
import net.happykoo.remittance.application.port.out.FindRemittancePort;
import net.happykoo.remittance.application.port.out.SaveRemittancePort;
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
import net.happykoo.remittance.domain.RemittanceRequestStatus;

@PersistenceAdapter
@RequiredArgsConstructor
public class RemittanceRequestRepository implements SaveRemittancePort, FindRemittancePort {

  private final JpaRemittanceRequestRepository jpaRemittanceRequestRepository;
  private final RemittanceRequestMapper remittanceRequestMapper;

  @Override
  public RemittanceRequest createRemittanceRequestHistory(
      FromMembershipId fromMembershipId,
      ToMembershipId toMembershipId,
      ToBankName toBankName,
      ToBankAccountNumber toBankAccountNumber,
      RemittanceType remittanceType,
      Amount amount
  ) {
    var entity = new JpaRemittanceRequestEntity(
        fromMembershipId.value(),
        toMembershipId.value(),
        toBankName.value(),
        toBankAccountNumber.value(),
        remittanceType.value(),
        amount.value(),
        RemittanceRequestStatus.REQUESTED,
        null
    );
    return remittanceRequestMapper.mapToDomainEntity(jpaRemittanceRequestRepository.save(entity));
  }

  @Override
  public RemittanceRequest updateRemittanceRequestStatus(
      RemittanceRequestId remittanceRequestId,
      RemittanceStatus remittanceRequestStatus,
      Message message
  ) {
    var entity = jpaRemittanceRequestRepository.findById(
            Long.parseLong(remittanceRequestId.value()))
        .orElseThrow(() -> new IllegalArgumentException(
            "entity does not exists : " + remittanceRequestId.value()));

    entity.setRemittanceStatus(remittanceRequestStatus.value());
    entity.setErrorMsg(message.value());
    return remittanceRequestMapper.mapToDomainEntity(jpaRemittanceRequestRepository.save(entity));
  }

  @Override
  public List<RemittanceRequest> findRemittanceHistory(String membershipId) {
    return jpaRemittanceRequestRepository.findByFromMembershipId(membershipId)
        .stream()
        .map(remittanceRequestMapper::mapToDomainEntity)
        .toList();
  }
}
