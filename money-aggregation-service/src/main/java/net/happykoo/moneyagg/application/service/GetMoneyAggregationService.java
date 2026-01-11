package net.happykoo.moneyagg.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.UseCase;
import net.happykoo.moneyagg.application.port.in.GetMoneySumUseCase;
import net.happykoo.moneyagg.application.port.out.FindMemberMoneyPort;
import net.happykoo.moneyagg.application.port.out.FindMembershipPort;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class GetMoneyAggregationService implements GetMoneySumUseCase {

  private final FindMembershipPort findMembershipPort;
  private final FindMemberMoneyPort findMemberMoneyPort;

  @Override
  public long getMoneySumByAddress(String address) {
    long sum = 0;

    List<String> membershipIds = findMembershipPort.findMembershipIdsByAddress(address);

    int chunkSize = 100;
    int chunkCount = (membershipIds.size() / chunkSize) + 1;

    int idx = 0;
    while (idx < chunkCount) {
      int startIdx = idx * chunkSize;
      int endIdx = Math.min(membershipIds.size(), ((idx + 1) * chunkSize - 1));
      sum += findMemberMoneyPort.findMoneySumByMembershipIds(
          membershipIds.subList(startIdx, endIdx)
      );
      log.info("API count: {}, startIndex: {}, endIndex: {}, sum: {}", idx, startIdx, endIdx, sum);

      idx++;
    }

    return sum;
  }
}
