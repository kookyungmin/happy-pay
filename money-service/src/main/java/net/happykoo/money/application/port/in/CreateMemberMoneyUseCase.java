package net.happykoo.money.application.port.in;

import net.happykoo.money.application.port.in.command.CreateMemberMoneyCommand;

public interface CreateMemberMoneyUseCase {

  void createMemberMoney(CreateMemberMoneyCommand command);

}
