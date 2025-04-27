package com.musicband.tour.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tour extends BaseEntity {
    @Id
    @GeneratedValue(
            generator = "seq_tour",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_tour",
            sequenceName = "seq_tour",
            allocationSize = 1
    )
    @Column(name = "tour_id")
    private Long tourId;
    @Column(name = "title")
    private String title;
    @Column(name = "tour_date")
    private LocalDateTime tourDate;
    @Column(name = "country")
    private String country;
    @Column(name = "area")
    private String area;

}
