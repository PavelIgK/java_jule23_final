package ru.sberbank.jd.authorization.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.authorization.entity.Authority;

/**
 * Репозиторий для работы с сущностью "Привилегии".
 *
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
    Optional<Authority> getAuthoritiesById(UUID id);

    Optional<Authority> getAuthorityByAuthorityAndUser_Id(String authority,UUID id);

}
