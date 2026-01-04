package net.happykoo.remittance.application.port.out;

import net.happykoo.remittance.application.port.out.data.MoneyInfoData;

public interface RequestMoneyInfoPort {

  MoneyInfoData requestMoneyInfo(String membershipId);
}
