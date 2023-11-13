package ru.sberbank.jd.schedule.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.Order;

/**
 * Репозиторий для сущности "Заказ услуги".
 *
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> getOrderByClient_IdAndStartDateTimeAfter(UUID clientId, Date startDateTime);

    List<Order> getOrderByClient_Id(UUID clientId);

    List<Order> getOrderByPerformer_IdAndStartDateTimeAfter(UUID performerId, Date startDateTime);

    List<Order> getOrderByPerformer_Id(UUID performerId);
}
