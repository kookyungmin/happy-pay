package net.happykoo.moneyagg.adapter.out.external.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponse {

  private String membershipId;

  private String name;

  private String email;

  private String address;

  private boolean isValid;

  private boolean isCorp;
}
