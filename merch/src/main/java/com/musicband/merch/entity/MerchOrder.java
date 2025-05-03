package com.musicband.merch.entity;

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
public class MerchOrder extends BaseEntity {

    @Id
    @GeneratedValue(
            generator = "seq_merch_order",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_merch_order",
            sequenceName = "seq_merch_order",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "merch_id")
    private Long merchId;
    @Column(name = "status")
    private String status;
    @Column(name = "user_email")
    private String userEmail;

}
