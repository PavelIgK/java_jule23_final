package ru.sberbank.jd.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска приложения.
 *
 */
@SpringBootApplication
public class AuthorizationApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApp.class, args);
    }
}
