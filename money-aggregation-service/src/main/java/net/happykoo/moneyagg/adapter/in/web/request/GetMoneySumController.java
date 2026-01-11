package net.happykoo.moneyagg.adapter.in.web.request;

import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.WebAdapter;
import net.happykoo.moneyagg.application.port.in.GetMoneySumUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@WebAdapter
@RequiredArgsConstructor
public class GetMoneySumController {

  private final GetMoneySumUseCase getMoneySumUseCase;

  @GetMapping("/money-aggregation/sum/by-address/{address}")
  ResponseEntity<Long> getMoneySumByAddress(@PathVariable String address) {
    return ResponseEntity.ok(getMoneySumUseCase.getMoneySumByAddress(address));
  }

}
