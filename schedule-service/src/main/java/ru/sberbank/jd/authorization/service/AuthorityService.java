package ru.sberbank.jd.authorization.service;


import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.dto.authorization.AuthorityDto;

/**
 * Сервис по работе с сущностью ролей.
 *
 */
public interface AuthorityService {


    /**
     * Получить всех записей с ролями.
     *
     * @return - список записей с ролями
     */
    List<AuthorityDto> findAll();

    /**
     * Получить пользователя по UUID.
     *
     * @param id - UUID
     * @return - экземпляр AuthorityDto
     */
    AuthorityDto getAuthorityById(UUID id);


    /**
     * Получить пользователя по логин паролю.
     *
     * @param authorityDto AuthorityDto
     * @return - экземпляр AuthorityDto
     */
    AuthorityDto add(AuthorityDto authorityDto);
}
