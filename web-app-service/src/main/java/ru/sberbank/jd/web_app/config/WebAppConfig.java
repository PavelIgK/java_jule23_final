package ru.sberbank.jd.web_app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Data
@Component
@ConfigurationProperties("application.properties")
public class WebAppConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Value("${schedule.service.url}")
    String scheduleServiceUrl;
    @Value("${schedule.service.port}")
    Integer scheduleServicePort;

}
