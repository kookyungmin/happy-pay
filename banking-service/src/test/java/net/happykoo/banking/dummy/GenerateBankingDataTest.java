package net.happykoo.banking.dummy;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import net.happykoo.banking.adapter.out.persistence.jpa.JpaFirmBankingRequestRepository;
import net.happykoo.banking.adapter.out.persistence.jpa.JpaRegisteredBankAccountRepository;
import net.happykoo.banking.adapter.out.persistence.jpa.entity.JpaFirmBankingRequestEntity;
import net.happykoo.banking.adapter.out.persistence.jpa.entity.JpaRegisteredBankAccountEntity;
import net.happykoo.banking.domain.FirmBankingRequestStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Rollback(false)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenerateBankingDataTest {

  @Autowired
  private JpaFirmBankingRequestRepository firmBankingRequestRepository;

  @Autowired
  private JpaRegisteredBankAccountRepository registeredBankAccountRepository;

  private Random random = new Random();

  @Test
  @DisplayName("RegisteredBankAccount dummy 생성")
  void generateRegisteredBankAccount() {
    List<JpaRegisteredBankAccountEntity> entities = IntStream.range(1, 1000)
        .mapToObj((index) -> new JpaRegisteredBankAccountEntity(
            String.valueOf(index),
            "테스트 은행" + ((index % 3) + 1),
            "123-1234-" + index,
            true,
            null
        ))
        .toList();

    registeredBankAccountRepository.saveAll(entities);
  }

  @Test
  @DisplayName("FirmBankingRequest dummy 생성")
  void generateFirmBankingRequest() {
    var entities = IntStream.range(1, 1000)
        .mapToObj((index) -> IntStream.range(1, 4)
            .mapToObj(i -> new JpaFirmBankingRequestEntity(
                "테스트 은행" + ((index % 3) + 1),
                "123-1234-" + index,
                "해피 은행",
                "123-123-123",
                (random.nextInt(100)) * 1000 * (i % 2 == 0 ? -1 : 1),
                FirmBankingRequestStatus.SUCCESS,
                null,
                null
            ))
            .toList())
        .flatMap(List::stream)
        .toList();

    firmBankingRequestRepository.saveAll(entities);
  }
}