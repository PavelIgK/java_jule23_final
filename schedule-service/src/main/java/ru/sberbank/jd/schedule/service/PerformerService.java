package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.authorization.entity.Authority;
import ru.sberbank.jd.authorization.repository.AuthorityRepository;
import ru.sberbank.jd.authorization.repository.UserRepository;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.schedule.entity.Performer;
import ru.sberbank.jd.schedule.repository.PerformerRepository;

/**
 * Сервис для работы с исполнителями услуг.
 */
@Service
@RequiredArgsConstructor
public class PerformerService {

    private final PerformerRepository performerRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    /**
     * Получить список всех исполнителей.
     *
     * @return - список исполнителей
     */
    public List<PerformerDto> getAllPerformers() {
        return performerRepository.findAll().stream().map(Performer::toDto).toList();
    }

    /**
     * Получить данные исполнителя по его id.
     *
     * @param id - id исполнителя
     * @return - исполнитель
     */
    public PerformerDto getPerformerById(UUID id) {
        return performerRepository.findById(id).get().toDto();
    }

    /**
     * Добавить нового исполнителя.
     *
     * @param performerDto - исполнитель
     * @return - исполнитель
     */
    public PerformerDto addPerformer(PerformerDto performerDto) {
        Performer performer = Performer.of(performerDto);
        if (performer.getId() == null) {
            performer.setId(UUID.randomUUID());
        }
        if (performer.getUser().getId() == null) {
            performer.getUser().setId(UUID.randomUUID());
        }
        userRepository.save(performer.getUser());
        Authority authority = Authority.builder()
                .id(UUID.randomUUID())
                .authority("PERFORMER")
                .user(performer.getUser())
                .build();

        authorityRepository.save(authority);

        return performerRepository.save(performer).toDto();
    }

    /**
     * Обновить исполнителя.
     *
     * @param performerDto - исполнитель
     * @return - исполнитель
     */
    public PerformerDto updatePerformer(PerformerDto performerDto) {
        Performer performer = Performer.of(performerDto);
        userRepository.save(performer.getUser());
        return performerRepository.save(performer).toDto();
    }

    /**
     * Удалить исполнителя.
     *
     * @param performer - исполнитель
     */
    public void deletePerformer(Performer performer) {
        performerRepository.delete(performer);
    }

    /**
     * Удалить исполнителя.
     *
     * @param id - ID исполнителя
     */
    public void deletePerformerById(UUID id) {
        Optional<Authority> authority = authorityRepository.getAuthorityByAuthorityAndUser_Id("PERFORMER", getPerformerById(id).getUser().getId());
        authorityRepository.delete(authority.get());
        performerRepository.deleteById(id);
    }

}
