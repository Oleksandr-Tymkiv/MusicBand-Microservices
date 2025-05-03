package com.musicband.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(
            generator = "seq_payment",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_payment",
            sequenceName = "seq_payment",
            allocationSize = 1
    )
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "price")
    private Double price;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType orderType;
    @Column(name = "status")
    private String status;
    @Column(name = "user_email")
    private String email;
}
