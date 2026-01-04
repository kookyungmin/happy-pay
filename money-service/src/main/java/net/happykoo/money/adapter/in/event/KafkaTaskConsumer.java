package net.happykoo.money.adapter.in.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.EventAdapter;
import net.happykoo.common.task.RechargingMoneyTask;
import net.happykoo.money.application.port.in.ProcessRechargingMoneyResultTaskUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@EventAdapter
@Slf4j
@RequiredArgsConstructor
public class KafkaTaskConsumer {

  private final ObjectMapper objectMapper;
  private final ProcessRechargingMoneyResultTaskUseCase processRechargingMoneyResultTask;


  @KafkaListener(
      topics = "${task.recharge-result-topic}",
      groupId = "${spring.kafka.group-id}")
  public void receive(ConsumerRecord<String, String> record) {
    String payload = record.value();
    try {
      RechargingMoneyTask task = objectMapper.readValue(payload, RechargingMoneyTask.class);
      processRechargingMoneyResultTask.processRechargingMoneyResultTask(task);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
