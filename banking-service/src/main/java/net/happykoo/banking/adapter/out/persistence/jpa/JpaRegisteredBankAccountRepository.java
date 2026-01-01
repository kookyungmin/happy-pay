package net.happykoo.banking.adapter.out.persistence.jpa;

import net.happykoo.banking.adapter.out.persistence.jpa.entity.JpaRegisteredBankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRegisteredBankAccountRepository extends
    JpaRepository<JpaRegisteredBankAccountEntity, Long> {

  boolean existsByMembershipIdAndBankNameAndBankAccountNumber(
      String membershipId,
      String bankName,
      String bankAccountNumber
  );

}
