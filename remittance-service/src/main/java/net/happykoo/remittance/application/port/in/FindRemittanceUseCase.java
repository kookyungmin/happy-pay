package net.happykoo.remittance.application.port.in;


import java.util.List;
import net.happykoo.remittance.application.port.in.command.FindRemittanceCommand;
import net.happykoo.remittance.domain.RemittanceRequest;

public interface FindRemittanceUseCase {

  List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command);
}
