package net.happykoo.remittance.application.port.out;

import net.happykoo.remittance.application.port.out.payload.MoneyChangingPayload;

public interface RequestMoneyChangingPort {

  boolean requestMoneyRecharging(MoneyChangingPayload payload);

  boolean requestMoneyIncrease(MoneyChangingPayload payload);

  boolean requestMoneyDecrease(MoneyChangingPayload payload);
}
