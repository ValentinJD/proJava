package ru.java.pro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.java.pro.logging.ResponseBodyInterceptor;

@Configuration
public class InterceptorResponseConfig implements WebMvcConfigurer {

    @Autowired
    private ResponseBodyInterceptor responseBodyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseBodyInterceptor)
                .addPathPatterns("/**"); // Используем для всех путей
    }
}
