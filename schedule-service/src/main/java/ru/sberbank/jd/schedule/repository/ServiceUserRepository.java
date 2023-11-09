package ru.sberbank.jd.schedule.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.ProvidedService;
import ru.sberbank.jd.schedule.entity.ServiceUser;

/**
 * Репозиторий для связки сущностей "Услуга" и "Пользователь".
 *
 */
public interface ServiceUserRepository extends JpaRepository<ServiceUser, UUID> {

    /**
     * Получить список услуг по id исполнителя.
     *
     * @param performerId - id исполнителя
     * @return - список услуг
     */
    List<ServiceUser> findAllByPerformerId(UUID performerId);

    /**
     * Получить список услуг по логину исполнителя.
     *
     * @param performerLogin - логин исполнителя
     * @return - список услуг
     */
    List<ServiceUser> findAllByPerformerLogin(String performerLogin);

    //    /**
    //     * Получить список исполнителей по услуге.
    //     *
    //     * @param providedService - id услуги
    //     * @return - список id исполнителей
    //     */
    //    List<ServiceUser> findAllByProvidedService(ProvidedService providedService);
}
