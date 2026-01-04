package net.happykoo.taskconsumer.application.port;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.UseCase;
import net.happykoo.common.task.RechargingMoneyTask;
import net.happykoo.common.task.SubTask;
import net.happykoo.taskconsumer.application.port.in.ExecuteRechargingMoneyTaskUseCase;
import net.happykoo.taskconsumer.application.port.out.SendRechargingMoneyResultTaskPort;

@UseCase
@RequiredArgsConstructor
public class ExecuteRechargingMoneyService implements ExecuteRechargingMoneyTaskUseCase {

  private final SendRechargingMoneyResultTaskPort sendRechargingMoneyResultTaskPort;

  @Override
  public void executeTask(RechargingMoneyTask task) {
    List<SubTask> subTasks = task.getSubTasks();

    for (SubTask subTask : subTasks) {
      //모든 테스크 성공했다고 가정 (원래는 멤버쉽 서비스, 뱅킹 서비스 호출 결과)
      subTask.success();
    }

    sendRechargingMoneyResultTaskPort.sendRechargeMoneyResultTask(task);
  }
}
