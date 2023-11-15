package ru.sberbank.jd.authorization.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.authorization.service.UserService;
import ru.sberbank.jd.dto.authorization.UserDto;


/**
 * Контроллер для юзеров.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Component
@Slf4j
@Tag(name="Пользователи", description="Взаимодействие с пользователями")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(
            summary = "Получить всех"
    )
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить пользователя по UUID"
    )
    public UserDto getUserById(@RequestParam UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/login")
    @Operation(
            summary = "Получить пользователя по логину"
    )
    public UserDto getUserByLonigAndPassword(@RequestHeader Map<String, String> headers) {
        return userService.getUserByLoginAndPassword(headers.get("login"), headers.get("password"));
    }

    @GetMapping("/telegramId/{telegramId}")
    @Operation(
            summary = "Получить пользователя по телеграм id"
    )
    public UserDto getUserByTelegramId(@PathVariable(value = "telegramId") String telegramId) {
        return userService.getUserByTelegramId(telegramId);
    }

    @PostMapping
    @Operation(
            summary = "Добавить пользователя"
    )
    public UserDto add(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

}
