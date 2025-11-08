package ru.java.pro.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.java.pro.logging.LoggingFilterCustom;

@Configuration
public class LogConfig  {

//    @Bean
//    public FilterRegistrationBean<LoggingFilterCustom> loggingFilter(){
//        var registrationBean = new FilterRegistrationBean<LoggingFilterCustom>();
//        registrationBean.setFilter(new LoggingFilterCustom());
//        registrationBean.addUrlPatterns("/*"); // Регистрация для всех путей
//        return registrationBean;
//    }
}

