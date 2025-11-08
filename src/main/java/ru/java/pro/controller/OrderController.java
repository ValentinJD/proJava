package ru.java.pro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.java.pro.dto.OrderDto;
import ru.java.pro.dto.ProductDto;
import ru.java.pro.logging.aspect.Loggable;

import java.util.List;

@Slf4j
@RestController
public class OrderController {

    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable("id") Long id){
        log.info("В контроллере getOrder");
        OrderDto orderDto = new OrderDto();
        orderDto.setId(100L);
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Product");
        orderDto.setProducts(List.of(productDto));
        return orderDto;
    }

    @Loggable
    @PostMapping("/order")
    public OrderDto saveOrder(@RequestBody OrderDto orderDto){
        log.info("В контроллере saveOrder");
        orderDto.setId(100L);
        ProductDto productDto = orderDto.getProducts().get(0);
        productDto.setId(1L);
        return orderDto;
    }
}
