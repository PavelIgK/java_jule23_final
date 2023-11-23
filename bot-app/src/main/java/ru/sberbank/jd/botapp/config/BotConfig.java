package ru.sberbank.jd.botapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
}
