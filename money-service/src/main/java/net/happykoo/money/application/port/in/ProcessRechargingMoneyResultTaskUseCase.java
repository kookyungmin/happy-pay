package net.happykoo.money.application.port.in;

import net.happykoo.common.task.RechargingMoneyTask;

public interface ProcessRechargingMoneyResultTaskUseCase {

  void processRechargingMoneyResultTask(RechargingMoneyTask task);

}
