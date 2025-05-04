package com.musicband.payment.controller;

import com.musicband.payment.dto.OrderDto;
import com.musicband.payment.dto.PaymentDto;
import com.musicband.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> getPayment(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentService.getOrderInfo(orderId));
    }

    @PostMapping("complete-payment")
    public ResponseEntity<String> completePayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.completePayment(paymentDto)
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Payment completed successfully")
                : ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Payment failed");
    }
}
