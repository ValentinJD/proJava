package ru.starter.response.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.starter.response.response.BaseResponseBuilder;

import java.util.Optional;


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
        Boolean isSimpleResponse = Optional.ofNullable(properties.getIsSimpleResponse())
                .orElse(false);
        return new BaseResponseBuilder(isSimpleResponse);
    }
}
