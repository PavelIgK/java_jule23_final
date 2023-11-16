package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;

/**
 * Сущность "Услуга".
 * Содержит список видов предоставляемых услуг.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "service")
public class ProvidedService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Наименование услуги.
     */
    private String name;

    /**
     * Описание услуги.
     */
    private String description;

    /**
     * Длительность услуги, минуты.
     */
    private int duration;

    /**
     * Стоимость услуги, рубли.
     */
    private double price;

    /**
     * Список иполнителей услуги.
     */
    @ManyToMany
    @JoinTable(name = "service_performer",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "performer_id", referencedColumnName = "id"))
    private Set<Performer> performers;


    @OneToMany(mappedBy = "service")
    private Set<Order> orders;

    public ProvidedServiceDto toDto() {
        return ProvidedServiceDto.builder()
                .id(this.getId())
                .name(this.getName())
                .description(this.getDescription())
                .duration(this.getDuration())
                .price(this.getPrice())
                .build();
    }

    public static ProvidedService of(ProvidedServiceDto providedServiceDto) {
        return ProvidedService.builder()
                .id(providedServiceDto.getId())
                .name(providedServiceDto.getName())
                .description(providedServiceDto.getDescription())
                .duration(providedServiceDto.getDuration())
                .price(providedServiceDto.getPrice())
                .build();
    }

}
