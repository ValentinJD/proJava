package ru.starter.response.logging.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Логируем перед выполнением метода
    @Before("@annotation(Loggable)")
    public void logRequest(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            logger.info("В аспекте Request to {} with body {}", arg, arg);
        }
    }

    // Логируем после успешного завершения метода
    @AfterReturning(pointcut = "@annotation(Loggable)", returning = "result")
    public void logResponse(Object result) {
        logger.info("В аспекте Response from endpoint is {}", result.toString());
    }

}