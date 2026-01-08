package net.happykoo.payment.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.happykoo.common.annotation.WebAdapter;
import net.happykoo.payment.adapter.in.web.request.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter
@RequiredArgsConstructor
public class RequestPaymentController {

  @PostMapping("/payment/request")
  ResponseEntity<Void> requestPayment(
      @RequestBody PaymentRequest request
  ) {
    return ResponseEntity.ok().build();
  }
}
