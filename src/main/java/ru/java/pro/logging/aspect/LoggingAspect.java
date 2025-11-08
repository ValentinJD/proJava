package ru.java.pro.logging.aspect;


import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.java.pro.dto.OrderDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static ru.java.pro.logging.LogUtil.extractBody;

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Логируем перед выполнением метода
    @Before("@annotation(Loggable)")
    public void logRequest(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof OrderDto request) {
//                String body = extractBody(request);
                logger.info("В аспекте Request to {} with body {}", request, request);
            }
        }
    }

    // Логируем после успешного завершения метода
    @AfterReturning(pointcut="@annotation(Loggable)", returning="result")
    public void logResponse(Object result) {
        logger.info("В аспекте Response from endpoint is {}", result.toString());
    }

}