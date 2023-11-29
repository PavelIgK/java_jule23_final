package ru.sberbank.jd.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для авторизации.
 *
 */
@Controller
public class AuthorizationController {

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "access_denied";
    }
}
