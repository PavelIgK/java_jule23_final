package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sberbank.jd.authorization.entity.User;
import ru.sberbank.jd.authorization.repository.UserRepository;
import ru.sberbank.jd.dto.schedule.ClientDto;

/**
 * Сущность "Клиент".
 * Предназначена для хранения клиентов, заказывающих услуги.
 * Связана с сущностью User (Пользователь) 1:1
 *
 */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends BaseUser{

    /**
     * Пользователь (учетная запись), к которому привязан клиент.
     */
    @OneToOne
    private User user;

    /**
     * Список заказов, сделанных данным пользователем.
     */
    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    public ClientDto toDto(){
        return ClientDto.builder()
                .id(this.getId())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .phoneNumber(this.getPhoneNumber())
                .user(this.getUser().toDto())
                .build();
    }

    public static Client of(ClientDto clientDto){

        return Client.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .phoneNumber(clientDto.getPhoneNumber())
                .user(User.of(clientDto.getUser()))
                .build();
    }

}
