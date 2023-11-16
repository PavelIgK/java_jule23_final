package ru.sberbank.jd.authorization.service.impl;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.authorization.entity.Authority;
import ru.sberbank.jd.authorization.exception.AuthorityNotFoundException;
import ru.sberbank.jd.authorization.repository.AuthorityRepository;
import ru.sberbank.jd.authorization.service.AuthorityService;
import ru.sberbank.jd.dto.authorization.AuthorityDto;

/**
 * Реализация сервиса по работе с сущностью пользователя.
 *
 */
@Component
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Получить всех AuthorityDto.
     *
     * @return - список AuthorityDto
     */
    @Override
    public List<AuthorityDto> findAll() {
        return authorityRepository.findAll()
                .stream()
                .map(Authority::toDto)
                .toList();
    }

    /**
     * Получить AuthorityDto по UUID.
     *
     * @param id - UUID
     * @return - экземпляр AuthorityDto
     */
    @Override
    public AuthorityDto getAuthorityById(UUID id) {
        Authority authority = authorityRepository.getAuthoritiesById(id)
                .orElseThrow(() -> new AuthorityNotFoundException("Нет Authority с id = " + id));
        return authority.toDto();
    }

    /**
     * Получить пользователя по логин паролю.
     *
     * @param authorityDto AuthorityDto
     * @return - экземпляр AuthorityDto
     */
    @Override
    public AuthorityDto add(AuthorityDto authorityDto) {
        Authority authority = Authority.of(authorityDto);
        authority = authorityRepository.save(authority);
        return authority.toDto();
    }
}
