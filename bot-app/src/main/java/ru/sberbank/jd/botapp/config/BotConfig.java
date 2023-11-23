package ru.sberbank.jd.botapp.config;

import java.time.Duration;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Конфиги.
 */
@Data
@Component
@ConfigurationProperties("application.properties")
public class BotConfig {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String token;
    @Value("${organization.longitude}")
    Double orgLong;
    @Value("${organization.latitude}")
    Double orgLat;
    @Value("http://${schedule.service.url}:${schedule.service.port}")
    String scheduleServiceUrl;
    @Value("${cache.lifetime.seconds}")
    Integer cacheLifetime;

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        //return new RestTemplate();
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }
}
