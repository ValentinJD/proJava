package ru.java.pro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
