package com.help;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private List<ProductDto> products;
}
