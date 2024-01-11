package com.example.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceRequestDTO {

	private LocalDateTime date;
    private Long productId;
    private Long brandId;
}
