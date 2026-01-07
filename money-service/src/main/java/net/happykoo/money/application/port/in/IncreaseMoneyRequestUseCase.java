package net.happykoo.money.application.port.in;

import net.happykoo.money.adapter.in.web.request.RechargeMoneyRequest;
import net.happykoo.money.application.port.in.command.IncreaseMoneyRequestCommand;
import net.happykoo.money.application.port.in.command.RechargeMoneyRequestCommand;
import net.happykoo.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {

  MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);

  void increaseMoneyRequestByEvent(
      IncreaseMoneyRequestCommand command);

  void rechargeMoneyRequestByEvent(RechargeMoneyRequestCommand command);
}
