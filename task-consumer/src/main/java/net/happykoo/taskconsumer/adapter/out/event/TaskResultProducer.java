package net.happykoo.taskconsumer.adapter.out.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.common.annotation.EventAdapter;
import net.happykoo.common.task.RechargingMoneyTask;
import net.happykoo.taskconsumer.application.port.out.SendRechargingMoneyResultTaskPort;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;

@EventAdapter
@RequiredArgsConstructor
@EnableConfigurationProperties(TaskProps.class)
@Slf4j
public class TaskResultProducer implements SendRechargingMoneyResultTaskPort {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final TaskProps taskProps;
  private final ObjectMapper objectMapper;

  @Override
  public void sendRechargeMoneyResultTask(RechargingMoneyTask task) {
    try {
      String key = task.getTaskId();
      String value = objectMapper.writeValueAsString(task);
      kafkaTemplate.send(taskProps.rechargeResultTopic(), key, value)
          .whenComplete((result, ex) -> {
            if (ex != null) {
              //전송 실패
              log.error(
                  "Kafka send failed. topic={}, key={}",
                  taskProps.rechargeResultTopic(),
                  key,
                  ex
              );
            } else {
              //전송 성공
              RecordMetadata meta = result.getRecordMetadata();
              log.info(
                  "Kafka send success. topic={}, key={}, partition={}, offset={}",
                  meta.topic(),
                  key,
                  meta.partition(),
                  meta.offset()
              );
            }
          });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
