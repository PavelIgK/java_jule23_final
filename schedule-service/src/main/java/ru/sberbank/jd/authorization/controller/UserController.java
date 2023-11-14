package ru.sberbank.jd.authorization.controller;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.jd.authorization.service.UserService;
import ru.sberbank.jd.dto.authorization.UserDto;


/**
 * Контроллер для юзеров.
 *
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Component
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping()
    public UserDto get(@RequestParam UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/login")
    public UserDto get(@RequestHeader Map<String, String> headers) {
        return userService.getUserByLoginAndPassword(headers.get("login"), headers.get("password"));
    }

}
