package ru.java.pro.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.java.pro.response.BaseResponseBuilder;

@Configuration
@EnableConfigurationProperties(MyProperties.class)
public class MyStarterAutoConfiguration {

    private final MyProperties properties;

    public MyStarterAutoConfiguration(MyProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnClass(BaseResponseBuilder.class)
    public BaseResponseBuilder baseResponseBuilder() {
        return new BaseResponseBuilder(properties.getIsSimpleResponse());
    }
}
