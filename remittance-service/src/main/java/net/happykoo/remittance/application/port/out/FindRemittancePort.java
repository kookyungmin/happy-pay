package net.happykoo.remittance.application.port.out;

import java.util.List;
import net.happykoo.remittance.domain.RemittanceRequest;

public interface FindRemittancePort {

  List<RemittanceRequest> findRemittanceHistory(String membershipId);
}
