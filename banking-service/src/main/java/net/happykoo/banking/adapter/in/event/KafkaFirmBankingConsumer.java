package net.happykoo.banking.adapter.in.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.banking.adapter.in.event.payload.FirmBankingPayload;
import net.happykoo.banking.application.port.in.RequestFirmBankingUseCase;
import net.happykoo.banking.application.port.in.command.RequestFirmBankingCommand;
import net.happykoo.common.annotation.EventAdapter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@EventAdapter
@Slf4j
@RequiredArgsConstructor
public class KafkaFirmBankingConsumer {

  private final ObjectMapper objectMapper;
  private final RequestFirmBankingUseCase requestFirmBankingUseCase;


  @KafkaListener(
      topics = "${task.firm-banking-topic}",
      groupId = "${spring.kafka.group-id}")
  public void receive(ConsumerRecord<String, String> record) {
    try {
      FirmBankingPayload payload = objectMapper.readValue(record.value(), FirmBankingPayload.class);
      RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
          .fromBankName(payload.fromBankName())
          .fromBankAccountNumber(payload.fromBankAccount())
          .toBankName(payload.toBankName())
          .toBankAccountNumber(payload.toBankAccount())
          .moneyAmount(payload.moneyAmount())
          .externalRequestId(payload.rechargingRequestId())
          .build();
      requestFirmBankingUseCase.requestFirmBankingByEvent(command);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
