package net.happykoo.remittance.application.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.UseCase;
import net.happykoo.remittance.application.port.in.RequestRemittanceUseCase;
import net.happykoo.remittance.application.port.in.command.RequestRemittanceCommand;
import net.happykoo.remittance.application.port.out.RequestFirmBankingPort;
import net.happykoo.remittance.application.port.out.RequestMembershipInfoPort;
import net.happykoo.remittance.application.port.out.RequestMoneyChangingPort;
import net.happykoo.remittance.application.port.out.RequestMoneyInfoPort;
import net.happykoo.remittance.application.port.out.SaveRemittancePort;
import net.happykoo.remittance.application.port.out.payload.FirmBankingPayload;
import net.happykoo.remittance.application.port.out.payload.MoneyChangingPayload;
import net.happykoo.remittance.domain.RemittanceRequest;
import net.happykoo.remittance.domain.RemittanceRequest.RemittanceRequestId;
import net.happykoo.remittance.domain.RemittanceRequestType;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestRemittanceService implements RequestRemittanceUseCase {

  private final SaveRemittancePort saveRemittancePort;
  private final RequestFirmBankingPort requestFirmBankingPort;
  private final RequestMembershipInfoPort requestMembershipInfoPort;
  private final RequestMoneyChangingPort requestMoneyChangingPort;
  private final RequestMoneyInfoPort requestMoneyInfoPort;

  @Override
  public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {
    //0. 송금 요청 상태 기록
    var remittanceRequest = saveRemittancePort.createRemittanceRequestHistory(
        new RemittanceRequest.FromMembershipId(command.getFromMembershipId()),
        new RemittanceRequest.ToMembershipId(command.getToMembershipId()),
        new RemittanceRequest.ToBankName(command.getToBankName()),
        new RemittanceRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
        new RemittanceRequest.RemittanceType(command.getRemittanceType()),
        new RemittanceRequest.Amount(command.getAmount())
    );

    //1. from 멤버쉽
    var isValidFromMembership = requestMembershipInfoPort.existsMembership(
        command.getFromMembershipId());

    if (isValidFromMembership) {
      return failedRemittanceRequest(remittanceRequest, "from membership is not valid.");
    }

    //2. 잔액 존재하는지 확인 (money service)
    var moneyInfoData = requestMoneyInfoPort.requestMoneyInfo(
        command.getFromMembershipId()
    );

    //2-1 잔액이 충분하지 않으면 충전 요청 (money service)
    if (moneyInfoData.getBalance() < command.getAmount()) {
      var isMoneyRechargingSuccess = requestMoneyChangingPort.requestMoneyRecharging(
          new MoneyChangingPayload(
              command.getFromMembershipId(),
              Math.max(10_000, command.getAmount() - moneyInfoData.getBalance())
          ));
      if (!isMoneyRechargingSuccess) {
        return failedRemittanceRequest(remittanceRequest, "failed to recharge money.");
      }
    }

    //3. 송금 타입 (내부 / 은행)
    if (command.getRemittanceType() == RemittanceRequestType.MEMBERSHIP) {
      //3-1 내부 고객일 경우, from 고객 머니 감액, to 고객 머니 증액 (money service)
      return requestRemittanceToMembership(command, remittanceRequest);
    } else if (command.getRemittanceType() == RemittanceRequestType.BANK) {
      //3-2 외부 은행일 경우, 외부 은행 계좌 적절한지 확인 (banking service)
      //법인 계좌 -> 외부 은행 펌뱅킹 요청 (banking service)
      //from 고객 머니 감액 (money service)
      return requestRemittanceToBank(command, remittanceRequest);
    } else {
      return failedRemittanceRequest(remittanceRequest, "remittance type is not supported.");
    }
  }

  private RemittanceRequest requestRemittanceToMembership(RequestRemittanceCommand command,
      RemittanceRequest remittanceRequest) {
    var isValidToMembership = requestMembershipInfoPort.existsMembership(
        command.getFromMembershipId());

    if (isValidToMembership) {
      return failedRemittanceRequest(remittanceRequest, "to membership is not valid.");
    }

    var isMoneyIncreasingSuccess = requestMoneyChangingPort.requestMoneyIncrease(
        new MoneyChangingPayload(
            command.getFromMembershipId(),
            command.getAmount()
        )
    );

    if (!isMoneyIncreasingSuccess) {
      return failedRemittanceRequest(remittanceRequest,
          "failed to increase money of from membership.");
    }

    var isMoneyDecreasingSuccess = requestMoneyChangingPort.requestMoneyDecrease(
        new MoneyChangingPayload(
            command.getToMembershipId(),
            command.getAmount()
        )
    );

    if (!isMoneyDecreasingSuccess) {
      return failedRemittanceRequest(remittanceRequest,
          "failed to decrease money of to membership.");
    }

    return successRemittanceRequest(remittanceRequest);
  }

  private RemittanceRequest requestRemittanceToBank(
      RequestRemittanceCommand command,
      RemittanceRequest remittanceRequest
  ) {
    var isSuccessFirmBanking = requestFirmBankingPort.requestFirmBanking(
        new FirmBankingPayload(
            command.getToBankName(),
            command.getToBankAccountNumber(),
            command.getAmount()
        )
    );

    if (!isSuccessFirmBanking) {
      return failedRemittanceRequest(remittanceRequest, "failed to request firm banking.");
    }

    var isSuccessMoneyDecreasing = requestMoneyChangingPort.requestMoneyDecrease(
        new MoneyChangingPayload(
            command.getFromMembershipId(),
            command.getAmount()
        ));

    if (!isSuccessMoneyDecreasing) {
      return failedRemittanceRequest(remittanceRequest, "failed to decrease money.");
    }

    return successRemittanceRequest(remittanceRequest);
  }

  private RemittanceRequest failedRemittanceRequest(RemittanceRequest remittanceRequest,
      String errorMessage) {
    remittanceRequest.failed(errorMessage);
    return updateRemittanceRequest(remittanceRequest);
  }

  private RemittanceRequest successRemittanceRequest(RemittanceRequest remittanceRequest) {
    remittanceRequest.success();
    return updateRemittanceRequest(remittanceRequest);
  }

  private RemittanceRequest updateRemittanceRequest(RemittanceRequest remittanceRequest) {
    return saveRemittancePort.updateRemittanceRequestStatus(
        new RemittanceRequestId(remittanceRequest.getRemittanceRequestId()),
        new RemittanceRequest.RemittanceStatus(remittanceRequest.getRemittanceStatus()),
        new RemittanceRequest.Message(remittanceRequest.getMessage())
    );
  }
}
