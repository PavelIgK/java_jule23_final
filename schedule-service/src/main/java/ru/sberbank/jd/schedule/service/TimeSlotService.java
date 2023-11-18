package ru.sberbank.jd.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import ru.sberbank.jd.dto.schedule.TimeSlotDto;
import ru.sberbank.jd.schedule.entity.Order;
import ru.sberbank.jd.schedule.entity.Schedule;
import ru.sberbank.jd.schedule.repository.OrderRepository;
import ru.sberbank.jd.schedule.repository.ScheduleRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервис для получения свободных слотов в расписании.
 *
 */
@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final ScheduleRepository scheduleRepository;

    private final OrderRepository orderRepository;

    private Date getDayStart(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getDayEnd(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public List<TimeSlotDto> getFreeTimeSlots(Date day, UUID performerID, UUID serviceId) {
        Date dayStart = getDayStart(day);
        Date dayEnd = getDayEnd(day);
        List<Schedule> schedules = scheduleRepository.getSchedulesByPerformer_IdAndPeriod(performerID, dayStart, dayEnd);
        List<Order> orders = orderRepository.getOrdersByPerformer_IdAndPeriod(performerID, dayStart,dayEnd);
        List<TimeSlotDto> timeSlotDtos = new ArrayList<>();

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
                            timeSlotDtos.add(new TimeSlotDto(startDate, endDate));
                            startDate = orders.get(currOrder).getEndDateTime();
                            endDate = startDate;
                            currOrder++;
                        }
                    } else {
                        endDate = schedules.get(currSchedule).getEndDateTime();
                        timeSlotDtos.add(new TimeSlotDto(startDate, endDate));
                        startDate = endDate;
                    }
                } else {
                    endDate = schedules.get(currSchedule).getEndDateTime();
                    timeSlotDtos.add(new TimeSlotDto(startDate, endDate));
                    startDate = endDate;
                }
            }

        }

        return timeSlotDtos;
    }

}
