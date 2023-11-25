package ru.sberbank.jd.web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для обработки запроса главной страницы.
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Получить шаблон для главной страницы.
     *
     * @return шаблон главной страницы
     */
    @GetMapping
    public String getMainPage() {
        return "main_page";
    }
}
