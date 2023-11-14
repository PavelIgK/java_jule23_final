package ru.sberbank.jd.authorization.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.sberbank.jd.dto.authorization.UserDto;
import ru.sberbank.jd.schedule.entity.Client;

/**
 * Сущность "Пользователь". Хранит данные учетной записи.
 */
@Entity
@Getter
@Setter
@Table(name = "auth_user")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Логин пользователя.
     */
    private String login;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Признак актвности учетной записи пользователя.
     */
    private boolean enabled;

    /**
     * id пользователя в Telegram.
     */
    private String telegramId;

    /**
     * Список привелегий пользователя.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Authority> authorities;

    /**
     * Конвертируем Entity в DTO.
     *
     * @return UserDto
     */
    public UserDto toDto() {
        return UserDto.builder()
                .id(this.id)
                .login(this.login)
                .password(this.password)
                .enabled(this.enabled)
                .telegramId(this.telegramId)
                .build();
    }

    /**
     * Метод получения entity из DTO.
     *
     * @param userDto DTO
     * @return entity
     */
    public static User of(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                //.authorities()
                .enabled(userDto.isEnabled())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .telegramId(userDto.getTelegramId())
                .build();
    }

}
