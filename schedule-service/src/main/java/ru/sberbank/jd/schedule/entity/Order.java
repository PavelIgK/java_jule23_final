package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.UUID;

import lombok.*;
import ru.sberbank.jd.dto.schedule.OrderDto;

/**
 * Сущность "Заказ услуги".
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Дата и время начала услуги.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    /**
     * Дата и время окончания услуги.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    /**
     * Исполнитель услуг.
     */
    @ManyToOne
    private Performer performer;

    /**
     * Клиент.
     */
    @ManyToOne
    private Client client;

    /**
     * Услуга.
     */
    @ManyToOne
    private ProvidedService service;

    /**
     * Метод получения DTO из entity.
     *
     * @return DTO объект
     */
    public OrderDto toDto() {
        return OrderDto.builder()
                .id(this.getId())
                .startDateTime(this.getStartDateTime())
                .endDateTime(this.getEndDateTime())
                .performer(this.getPerformer().toDto())
                .client(this.getClient().toDto())
                .service(this.getService().toDto())
                .build();
    }

    /**
     * Метод получения entity из DTO.
     *
     * @param orderDto DTO
     * @return entity
     */
    public static Order of(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .startDateTime(orderDto.getStartDateTime())
                .endDateTime(orderDto.getEndDateTime())
                .performer(Performer.of(orderDto.getPerformer()))
                .client(Client.of(orderDto.getClient()))
                .service(ProvidedService.of(orderDto.getService()))
                .build();
    }

}
