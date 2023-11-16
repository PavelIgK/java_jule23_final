package ru.sberbank.jd.authorization.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sberbank.jd.dto.authorization.AuthorityDto;

/**
 * Сущность "Права доступа".
 * Содержит права доступа для пользователей.
 *
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Привелегия.
     */
    private String authority;

    /**
     * Список пользователей, обладающих привилегией.
     */
    @ManyToOne
    private User user;

    /**
     * Конвертируем Entity в DTO.
     *
     * @return AuthorityDto
     */
    public AuthorityDto toDto() {
        return AuthorityDto.builder()
                .id(this.id)
                .authority(this.authority)
                .user(this.user.toDto())
                .build();
    }

    /**
     * Метод получения entity из DTO.
     *
     * @param authorityDto DTO
     * @return entity
     */
    public static Authority of(AuthorityDto authorityDto) {
        return Authority.builder()
                .id(authorityDto.getId())
                .authority(authorityDto.getAuthority())
                .user(User.of(authorityDto.getUser()))
                .build();
    }

}
