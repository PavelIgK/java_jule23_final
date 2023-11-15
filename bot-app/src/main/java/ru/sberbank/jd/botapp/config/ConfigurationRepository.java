package ru.sberbank.jd.botapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberbank.jd.botapp.repository.UserCacheRepository;
import ru.sberbank.jd.botapp.repository.UserCacheRepositoryImpl;

@Configuration
public class ConfigurationRepository {

    @Bean
    public UserCacheRepository userCacheRepository() {
        return new UserCacheRepositoryImpl();
    }
}
