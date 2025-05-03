package com.musicband.merch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MerchDto {
    private String title;
    private Double price;
    private String category;
    private String description;
}
