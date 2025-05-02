package com.musicband.ticket.entity;

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
public class TicketOrder extends BaseEntity{

    @Id
    @GeneratedValue(
            generator = "seq_ticket_order",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_ticket_order",
            sequenceName = "seq_ticket_order",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "ticket_id")
    private Long ticketId;
    @Column(name = "status")
    private String status;
    @Column(name = "user_email")
    private String userEmail;
}
