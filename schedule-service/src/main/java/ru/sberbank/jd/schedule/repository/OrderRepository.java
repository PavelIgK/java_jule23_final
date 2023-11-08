package ru.sberbank.jd.schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.Order;

/**
 * Репозиторий для сущности "Заказ услуги".
 *
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
