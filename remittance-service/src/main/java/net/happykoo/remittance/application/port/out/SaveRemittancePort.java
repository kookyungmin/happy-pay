package net.happykoo.remittance.application.port.out;


import net.happykoo.remittance.domain.RemittanceRequest;

public interface SaveRemittancePort {

  RemittanceRequest createRemittanceRequestHistory(
      RemittanceRequest.FromMembershipId fromMembershipId,
      RemittanceRequest.ToMembershipId toMembershipId,
      RemittanceRequest.ToBankName toBankName,
      RemittanceRequest.ToBankAccountNumber toBankAccountNumber,
      RemittanceRequest.RemittanceType remittanceType,
      RemittanceRequest.Amount amount
  );

  RemittanceRequest updateRemittanceRequestStatus(
      RemittanceRequest.RemittanceRequestId remittanceRequestId,
      RemittanceRequest.RemittanceStatus remittanceRequestStatus,
      RemittanceRequest.Message message
  );
}
