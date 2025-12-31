package net.happykoo.membership.adapter.out.persistence.jpa;

import net.happykoo.membership.adapter.out.persistence.jpa.entity.MembershipJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {

}
