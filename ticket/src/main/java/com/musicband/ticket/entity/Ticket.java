package com.musicband.ticket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(
            generator = "seq_ticket",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_ticket",
            sequenceName = "seq_ticket",
            allocationSize = 1
    )
    @Column(name = "ticket_id")
    private Long ticketId;
    @Column(name = "price")
    private Double price;
    @Column(name = "place")
    private String place;
    @Column(name = "is_available")
    private Boolean isAvailable;
    private Long tourId;

}
