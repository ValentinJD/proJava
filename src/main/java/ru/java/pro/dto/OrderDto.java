package ru.java.pro.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private List<ProductDto> products;
}
