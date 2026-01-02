package net.happykoo.money.adapter.out.persistence.jpa;

import net.happykoo.money.adapter.out.persistence.jpa.entity.JpaMoneyChangingRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMoneyChangingRequestRepository extends
    JpaRepository<JpaMoneyChangingRequestEntity, Long> {

}
