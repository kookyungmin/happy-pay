package net.happykoo.remittance.application.service;


import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.UseCase;
import net.happykoo.remittance.application.port.in.FindRemittanceUseCase;
import net.happykoo.remittance.application.port.in.command.FindRemittanceCommand;
import net.happykoo.remittance.application.port.out.FindRemittancePort;
import net.happykoo.remittance.domain.RemittanceRequest;
import net.happykoo.remittance.domain.RemittanceRequest.RemittanceRequestId;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {

  private final FindRemittancePort findRemittancePort;

  @Override
  public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
    return findRemittancePort.findRemittanceHistory(command.getMembershipId());
  }
}
