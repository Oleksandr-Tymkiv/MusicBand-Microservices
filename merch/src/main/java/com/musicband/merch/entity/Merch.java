package com.musicband.merch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Merch extends BaseEntity {
    @Id
    @GeneratedValue(
            generator = "seq_merch",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_merch",
            sequenceName = "seq_merch",
            allocationSize = 1
    )
    @Column(name = "merch_id")
    private Long merchId;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Double price;
    @Column(name = "category")
    private String category;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
}
