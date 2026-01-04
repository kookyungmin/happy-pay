package net.happykoo.remittance.application.port.in;


import net.happykoo.remittance.application.port.in.command.RequestRemittanceCommand;
import net.happykoo.remittance.domain.RemittanceRequest;

public interface RequestRemittanceUseCase {

  RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}
