package net.happykoo.taskconsumer.application.port.out;

import net.happykoo.common.task.RechargingMoneyTask;

public interface SendRechargingMoneyResultTaskPort {

  void sendRechargeMoneyResultTask(RechargingMoneyTask task);

}
