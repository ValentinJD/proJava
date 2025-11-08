package ru.java.pro.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component("loggingFilterCustom")
public class LoggingFilterCustom implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        logRequest(httpRequest);
        try {
            // Продолжение цепочки фильтров
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // Запись информации об ответе и времени выполнения
            logResponse(httpRequest, httpResponse, "Объект пока не определили");
        }
    }

    private void logRequest(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        Enumeration<String> headerNames = request.getHeaderNames();
        log.info("Логирование в фильтре Запрос: {} - {} - headers {}", method, requestURI, headerNames);
    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response, Object result) {
        int statusCode = response.getStatus();
        log.info("Логирование в фильтре Ответ: HTTP {} - {} result - {}", statusCode, request.getRequestURI(), result);
    }


}

