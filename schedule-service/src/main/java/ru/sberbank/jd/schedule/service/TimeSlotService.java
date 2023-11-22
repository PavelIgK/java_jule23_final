package ru.sberbank.jd.schedule.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.TimeSlotDto;
import ru.sberbank.jd.schedule.entity.Order;
import ru.sberbank.jd.schedule.entity.Schedule;
import ru.sberbank.jd.schedule.repository.OrderRepository;
import ru.sberbank.jd.schedule.repository.ScheduleRepository;


/**
 * Сервис для получения свободных слотов в расписании.
 * Получает пересечения интервалов в расписании сотрудника и уже оформленных заказов.
 */
@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final ScheduleRepository scheduleRepository;

    private final OrderRepository orderRepository;

    private final ProvidedServiceService providedServiceService;

    private final int MIN_SERVICE_INTERVAL = 30;

    /**
     * Рассчитать время начала суток.
     *
     * @param day - дата расчетного дня
     * @return - дату и время начала суток
     */
    private Date getDayStart(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Рассчитать время окончания суток.
     *
     * @param day - дата расчетного дня
     * @return - дату и время окончания суток
     */
    private Date getDayEnd(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Получить список интервалов, доступных для записи в течение дня.
     *
     * @param day         - дата требуемого для записи дня
     * @param performerID - id исполнителя
     * @param serviceId   - id услуги
     * @return - список интервалов, доступных для записи
     */
    public List<TimeSlotDto> getFreeTimeSlots(Date day, UUID performerID, UUID serviceId) {
        Date dayStart = getDayStart(day);
        Date dayEnd = getDayEnd(day);
        List<Schedule> schedules = scheduleRepository.getSchedulesByPerformer_IdAndPeriod(performerID, dayStart, dayEnd);
        List<Order> orders = orderRepository.getOrdersByPerformer_IdAndPeriod(performerID, dayStart, dayEnd);
        List<TimeSlotDto> timeSlots = new ArrayList<>();

        if (schedules.size() > 0) {
            Date startDate = schedules.get(0).getStartDateTime();
            Date endDate = startDate;
            int currSchedule = 0;
            int currOrder = 0;
            while (true) {
                if (startDate.after(schedules.get(currSchedule).getEndDateTime()) ||
                        startDate.equals(schedules.get(currSchedule).getEndDateTime())) {
                    if (currSchedule < schedules.size() - 1) {
                        currSchedule++;
                        startDate = schedules.get(currSchedule).getStartDateTime();
                        endDate = startDate;
                    } else {
                        break;
                    }
                }
                if (currOrder < orders.size()) {
                    if (orders.get(currOrder).getStartDateTime().before(schedules.get(currSchedule).getEndDateTime())) {
                        if (endDate.before(orders.get(currOrder).getStartDateTime())) {
                            endDate = orders.get(currOrder).getStartDateTime();
                            timeSlots.add(new TimeSlotDto(startDate, endDate));
                            startDate = orders.get(currOrder).getEndDateTime();
                            endDate = startDate;
                            currOrder++;
                        }
                    } else {
                        endDate = schedules.get(currSchedule).getEndDateTime();
                        timeSlots.add(new TimeSlotDto(startDate, endDate));
                        startDate = endDate;
                    }
                } else {
                    endDate = schedules.get(currSchedule).getEndDateTime();
                    timeSlots.add(new TimeSlotDto(startDate, endDate));
                    startDate = endDate;
                }
            }

        }

        int seviceDuration = providedServiceService.getServicesById(serviceId).getDuration();

        List<TimeSlotDto> timeSlotsByMinInterval = new ArrayList<>();

        for (TimeSlotDto timeSlot : timeSlots) {
            LocalDateTime timeSlotBegin = LocalDateTime.ofInstant(timeSlot.getStartDateTime().toInstant(), ZoneId.systemDefault());
            LocalDateTime timeSlotEnd = LocalDateTime.ofInstant(timeSlot.getEndDateTime().toInstant(), ZoneId.systemDefault());
            LocalDateTime begin = timeSlotBegin;
            LocalDateTime end = begin.plusMinutes(seviceDuration);
            while (end.isBefore(timeSlotEnd) || end.isEqual(timeSlotEnd)) {
                timeSlotsByMinInterval
                        .add(new TimeSlotDto(Date.from(begin.atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(end.atZone(ZoneId.systemDefault()).toInstant())));
                begin = begin.plusMinutes(MIN_SERVICE_INTERVAL);
                end = begin.plusMinutes(seviceDuration);
            }
        }

        return timeSlotsByMinInterval;
    }

}
