package net.happykoo.remittance.adapter.in.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.WebAdapter;
import net.happykoo.remittance.application.port.in.FindRemittanceUseCase;
import net.happykoo.remittance.application.port.in.command.FindRemittanceCommand;
import net.happykoo.remittance.domain.RemittanceRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@WebAdapter
@RequiredArgsConstructor
public class FindRemittanceHistoryController {

  private final FindRemittanceUseCase findRemittanceUseCase;

  @GetMapping("/remittance/{membershipId}")
  List<RemittanceRequest> findRemittanceHistory(@PathVariable String membershipId) {
    FindRemittanceCommand command = FindRemittanceCommand.builder()
        .membershipId(membershipId)
        .build();

    return findRemittanceUseCase.findRemittanceHistory(command);
  }
}
