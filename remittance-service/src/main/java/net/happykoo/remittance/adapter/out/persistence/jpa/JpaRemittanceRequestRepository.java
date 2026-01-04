package net.happykoo.remittance.adapter.out.persistence.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRemittanceRequestRepository extends
    JpaRepository<JpaRemittanceRequestEntity, Long> {

  List<JpaRemittanceRequestEntity> findByFromMembershipId(String fromMembershipId);

}
