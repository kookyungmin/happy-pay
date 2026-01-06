package net.happykoo.banking.adapter.out.persistence.jpa;

import java.util.Optional;
import net.happykoo.banking.adapter.out.persistence.jpa.entity.JpaFirmBankingRequestEntity;
import net.happykoo.banking.domain.FirmBankingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFirmBankingRequestRepository extends
    JpaRepository<JpaFirmBankingRequestEntity, Long> {

  Optional<JpaFirmBankingRequestEntity> findByEventStreamId(String eventStreamId);

}
