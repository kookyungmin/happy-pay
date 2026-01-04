package net.happykoo.common.task;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RechargingMoneyTask {

  private String taskId;
  private String taskName;
  private String membershipId;
  @Builder.Default
  private List<SubTask> subTasks = new ArrayList<>();
  private String toBankName; //법인 계좌
  private String toBankAccountNumber; //법인 계좌 번호
  private int moneyAmount; // only won

  public void addSubTask(SubTask subTask) {
    subTasks.add(subTask);
  }
}
