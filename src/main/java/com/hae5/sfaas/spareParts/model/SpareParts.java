package com.hae5.sfaas.spareParts.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpareParts {
    private String itemCode;
    private String itemName;
    private String location;
    private Integer stock;
    private Integer safetyStock;
    private Integer shortageAmount;
    private Double price;
    private Long factoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static SpareParts create(String itemCode, String itemName, String location,
            Integer stock, Integer safetyStock, Integer shortageAmount,
            Double price, Long factoryId, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return SpareParts.builder()
                .itemCode(itemCode)
                .itemName(itemName)
                .location(location)
                .stock(stock)
                .safetyStock(safetyStock)
                .shortageAmount(shortageAmount)
                .price(price)
                .factoryId(factoryId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
