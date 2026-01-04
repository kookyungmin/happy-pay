package net.happykoo.taskconsumer.adapter.in.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.EventAdapter;
import net.happykoo.common.task.RechargingMoneyTask;
import net.happykoo.taskconsumer.application.port.in.ExecuteRechargingMoneyTaskUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@EventAdapter
@Slf4j
@RequiredArgsConstructor
public class KafkaTaskConsumer {

  private final ExecuteRechargingMoneyTaskUseCase executeRechargingMoneyTaskUseCase;
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = "${task.recharge-topic}",
      groupId = "${spring.kafka.group-id}")
  public void receive(ConsumerRecord<String, String> record) {
    String payload = record.value();
    try {
      RechargingMoneyTask task = objectMapper.readValue(payload, RechargingMoneyTask.class);
      executeRechargingMoneyTaskUseCase.executeTask(task);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
