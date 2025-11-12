package ru.starter.response.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.starter.response.logging.ResponseBodyInterceptor;
import ru.starter.response.response.BaseResponseBuilder;

import java.util.Optional;
import java.util.UUID;


@ComponentScan("ru.starter.response")
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

    @Bean
    public ResponseBodyInterceptor responseBodyInterceptor() {
        return new ResponseBodyInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "rquid-generator.bean", name = "enable", havingValue = "true")
    public RqUidGenerator rquidGenerator() {
        return new RqUidGenerator();
    }

    public class RqUidGenerator {
        public String getRandom() {
            return UUID.randomUUID().toString();
        }
    }

}
