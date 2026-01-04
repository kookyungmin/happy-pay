package net.happykoo.money.application.port.out;

import net.happykoo.money.domain.MoneyChangingRequest;

public interface SaveMoneyChangingRequestPort {

  MoneyChangingRequest createMoneyChangingRequest(
      MoneyChangingRequest.TargetMembershipId targetMembershipId,
      MoneyChangingRequest.RequestType requestType,
      MoneyChangingRequest.MoneyAmount moneyAmount
  );

  MoneyChangingRequest updateMoneyChangingStatus(
      MoneyChangingRequest.MoneyChangingRequestId moneyChangingRequestId,
      MoneyChangingRequest.RequestStatus requestStatus,
      MoneyChangingRequest.Message message
  );
}
