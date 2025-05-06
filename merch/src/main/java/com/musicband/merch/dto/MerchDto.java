package com.musicband.merch.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MerchDto {
    @NotEmpty(message = "Title cannot be a null or empty")
    private String title;
    @Positive(message = "Price of ticket should be greater than zero")
    private Double price;
    @NotEmpty(message = "Category cannot be a null or empty")
    private String category;
    @NotEmpty(message = "Description cannot be a null or empty")
    private String description;
    @NotEmpty(message = "Image cannot be a null or empty")
    private String image;
}
