package ru.java.pro.controller;

import org.springframework.web.bind.annotation.*;
import ru.java.pro.dto.OrderDto;
import ru.java.pro.dto.ProductDto;

import java.util.List;

@RestController
public class OrderController {

    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable("id") Long id){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(100L);
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Product");
        orderDto.setProducts(List.of(productDto));
        return orderDto;
    }

    @PostMapping("/order")
    public OrderDto saveOrder(@RequestBody OrderDto orderDto){
        orderDto.setId(100L);
        ProductDto productDto = orderDto.getProducts().get(0);
        productDto.setId(1L);
        return orderDto;
    }
}
