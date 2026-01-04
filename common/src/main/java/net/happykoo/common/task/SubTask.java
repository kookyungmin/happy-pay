package net.happykoo.common.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {

  private String taskId;
  private String membershipId;
  private String subTaskName;
  private String taskType; // "banking", "membership"
  private TaskStatus status; // "success", "fail", "ready"

  public void success() {
    this.status = TaskStatus.SUCCESS;
  }
}
