package net.happykoo.taskconsumer.application.port.in;

import net.happykoo.common.task.RechargingMoneyTask;

public interface ExecuteRechargingMoneyTaskUseCase {

  void executeTask(RechargingMoneyTask task);

}
