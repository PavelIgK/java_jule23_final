package ru.sberbank.jd.schedule.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.authorization.entity.User;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.schedule.entity.Performer;

/**
 * Класс для преобразования DTO в Entity и обратно.
 */
@Component
public class MappingUtils {

    /**
     * Метод преобразования из сущности Performer в DTO PerformerDto.
     *
     * @param performer - экземпляр класса Performer
     * @return - полученный экземпляр PerformerDto
     */
    public PerformerDto mapToPerformerDto(Performer performer) {
        return PerformerDto.builder()
                .id(performer.getId())
                .firstName(performer.getFirstName())
                .lastName(performer.getLastName())
                .phoneNumber(performer.getPhoneNumber())
                .user(performer.getUser().toDto())
                .build();
    }

    /**
     * Метод преобразования из PerformerDto в сущность Performer.
     *
     * @param dto - экземпляр PerformerDto
     * @return - полученный экземпляр Performer
     */
    public Performer mapToPerformerEntity(PerformerDto dto) {
        return Performer.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .user(User.of(dto.getUser()))
                .build();
    }
}
