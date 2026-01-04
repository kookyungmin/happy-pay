package net.happykoo.money.application.port.out;

import net.happykoo.common.task.RechargingMoneyTask;

public interface SendRechargingMoneyTaskPort {

  void sendRechargingMoneyTask(
      RechargingMoneyTask task
  );

}
