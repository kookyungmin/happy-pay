package net.happykoo.moneyagg.application.port.out;

import java.util.List;

public interface FindMembershipPort {

  List<String> findMembershipIdsByAddress(String address);
}
