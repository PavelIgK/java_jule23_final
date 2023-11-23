package ru.sberbank.jd.schedule.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sberbank.jd.schedule.entity.Order;
import ru.sberbank.jd.schedule.entity.Schedule;

/**
 * Репозиторий для сущности "Заказ услуги".
 *
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> getOrderByClient_IdAndStartDateTimeAfter(UUID clientId, Date startDateTime);

    List<Order> getOrderByClient_Id(UUID clientId);

    List<Order> getOrderByPerformer_IdAndStartDateTimeAfter(UUID performerId, Date startDateTime);

    List<Order> getOrderByPerformer_Id(UUID performerId);

    @Query("select o from Order o where o.performer.id = :performer_id "
            + "and o.startDateTime >= :begin and o.endDateTime <= :end order by o.startDateTime")
    List<Order> getOrdersByPerformer_IdAndPeriod(
            @Param("performer_id") UUID performerId,
            @Param("begin") Date begin,
            @Param("end") Date end);
}
