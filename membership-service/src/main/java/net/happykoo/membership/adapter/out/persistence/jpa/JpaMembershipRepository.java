package net.happykoo.membership.adapter.out.persistence.jpa;

import java.util.List;
import java.util.Optional;
import net.happykoo.membership.adapter.out.persistence.jpa.entity.JpaMembershipEntity;
import net.happykoo.membership.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMembershipRepository extends JpaRepository<JpaMembershipEntity, Long> {

  List<JpaMembershipEntity> findByAddress(String address);

  Optional<JpaMembershipEntity> findByEmail(String email);
}
