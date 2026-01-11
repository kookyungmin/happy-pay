package net.happykoo.moneyagg.application.port.out;

import java.util.List;

public interface FindMemberMoneyPort {

  long findMoneySumByMembershipIds(List<String> membershipIds);
}
