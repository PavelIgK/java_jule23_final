package ru.sberbank.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска приложения.
 *
 */
@SpringBootApplication
public class ScheduleApp {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApp.class, args);
    }
}
