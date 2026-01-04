package net.happykoo.remittance.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.WebAdapter;
import net.happykoo.remittance.adapter.in.web.request.RequestRemittanceRequest;
import net.happykoo.remittance.application.port.in.RequestRemittanceUseCase;
import net.happykoo.remittance.application.port.in.command.RequestRemittanceCommand;
import net.happykoo.remittance.domain.RemittanceRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@WebAdapter
@RequiredArgsConstructor
public class RequestRemittanceController {

  private final RequestRemittanceUseCase requestRemittanceUseCase;

  @PostMapping(path = "/remittance")
  RemittanceRequest requestRemittance(@RequestBody RequestRemittanceRequest request) {
    RequestRemittanceCommand command = RequestRemittanceCommand.builder()
        .fromMembershipId(request.fromMembershipId())
        .toMembershipId(request.toMembershipId())
        .toBankName(request.toBankName())
        .toBankAccountNumber(request.toBankAccountNumber())
        .amount(request.amount())
        .remittanceType(request.remittanceType())
        .build();

    return requestRemittanceUseCase.requestRemittance(command);
  }
}
