package ru.java.pro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "response.type")
public class MyProperties {

    private Boolean isSimpleResponse;

}
