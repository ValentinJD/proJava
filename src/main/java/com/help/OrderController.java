package com.help;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.starter.response.config.MyStarterAutoConfiguration;
import ru.starter.response.exception.ServiceException;
import ru.starter.response.logging.aspect.Loggable;
import ru.starter.response.response.BaseResponseBuilder;

import java.util.List;

import static ru.starter.response.exception.Error.ERROR_001;


@Slf4j
@RestController
@AllArgsConstructor
public class OrderController {

    private final BaseResponseBuilder baseResponseBuilder;
    private final MyStarterAutoConfiguration.RqUidGenerator rqUidGenerator;

    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable("id") Long id) {
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
    public OrderDto saveOrder(@RequestBody OrderDto orderDto) {
        String rqUidGeneratorRandom = rqUidGenerator.getRandom();
        log.info("В контроллере {}", rqUidGeneratorRandom);
        validateOrder(orderDto);
        log.info("В контроллере saveOrder");
        orderDto.setId(100L);
        ProductDto productDto = orderDto.getProducts().get(0);
        productDto.setId(1L);
        return orderDto;
    }

    private void validateOrder(OrderDto orderDto) {
        if (orderDto.getProducts().isEmpty()) {
            throw new ServiceException(ERROR_001.getErrorCode(), "Products is empty");
        }
    }

}
