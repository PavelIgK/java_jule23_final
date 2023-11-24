package ru.sberbank.jd.schedule.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.sberbank.jd.ScheduleApp;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import ru.sberbank.jd.dto.schedule.TimeSlotDto;
import ru.sberbank.jd.schedule.entity.Order;
import ru.sberbank.jd.schedule.entity.Performer;
import ru.sberbank.jd.schedule.entity.ProvidedService;
import ru.sberbank.jd.schedule.entity.Schedule;
import ru.sberbank.jd.schedule.repository.OrderRepository;
import ru.sberbank.jd.schedule.repository.ProvidedServiceRepository;
import ru.sberbank.jd.schedule.repository.ScheduleRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ScheduleApp.class)
class TimeSlotServiceTest {

    @Autowired
    private TimeSlotService timeSlotService;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProvidedServiceService providedServiceService;

    private Date getDate(String strDate) {
        return Date.from(LocalDateTime.parse(strDate)
                .atZone(ZoneId.systemDefault()).toInstant());
    }

    @Test
    void getFreeTimeSlotsTest() {

//        List<TimeSlotDto> timeSlots = new ArrayList<>();
//        timeSlots.add(new TimeSlotDto(getDate("2023-11-16T09:00:00"),
//                getDate("2023-11-16T13:00:00")));
//        timeSlots.add(new TimeSlotDto(getDate("2023-11-16T14:00:00"),
//                getDate("2023-11-16T18:00:00")));

        List<Schedule> schedules = new ArrayList<>();

        schedules.add(Schedule
                .builder()
                .id(UUID.fromString("00000000-0000-0000-0000-500000000001"))
                .startDateTime(getDate("2023-11-16T09:00:00"))
                .endDateTime(getDate("2023-11-16T13:00:00"))
                .build());

        schedules.add(Schedule
                .builder()
                .id(UUID.fromString("00000000-0000-0000-0000-500000000001"))
                .startDateTime(getDate("2023-11-16T14:00:00"))
                .endDateTime(getDate("2023-11-16T18:00:00"))
                .build());

        Mockito.when(scheduleRepository
                        .getSchedulesByPerformer_IdAndPeriod(any(UUID.class),
                                any(Date.class),
                                any(Date.class)))
                .thenReturn(schedules);

        List<Order> orders = new ArrayList<>();

        orders.add(Order.builder()
                .startDateTime(getDate("2023-11-16T10:00:00"))
                .endDateTime(getDate("2023-11-16T11:00:00"))
                .build());

        orders.add(Order.builder()
                .startDateTime(getDate("2023-11-16T15:00:00"))
                .endDateTime(getDate("2023-11-16T15:30:00"))
                .build());

        Mockito.when(orderRepository
                        .getOrdersByPerformer_IdAndPeriod(any(UUID.class),
                                any(Date.class),
                                any(Date.class)))
                .thenReturn(orders);

        ProvidedServiceDto serviceDto = ProvidedServiceDto
                .builder()
                .duration(90)
                .build();

        Mockito.when(providedServiceService
                        .getServicesById(any(UUID.class)))
                .thenReturn(serviceDto);


        List<TimeSlotDto> timeSlots = timeSlotService.getFreeTimeSlots(getDate("2023-11-16T14:00:00"),
                UUID.fromString("00000000-0000-0000-0000-500000000001"),
                UUID.fromString("00000000-0000-0000-0000-400000000004"));

        Assertions.assertEquals(5, timeSlots.size());
        if (timeSlots.size() == 5) {
            Assertions.assertEquals(getDate("2023-11-16T11:00:00"), timeSlots.get(0).getStartDateTime());
            Assertions.assertEquals(getDate("2023-11-16T12:30:00"), timeSlots.get(0).getEndDateTime());

            Assertions.assertEquals(getDate("2023-11-16T11:30:00"), timeSlots.get(1).getStartDateTime());
            Assertions.assertEquals(getDate("2023-11-16T13:00:00"), timeSlots.get(1).getEndDateTime());

            Assertions.assertEquals(getDate("2023-11-16T15:30:00"), timeSlots.get(2).getStartDateTime());
            Assertions.assertEquals(getDate("2023-11-16T17:00:00"), timeSlots.get(2).getEndDateTime());

            Assertions.assertEquals(getDate("2023-11-16T16:00:00"), timeSlots.get(3).getStartDateTime());
            Assertions.assertEquals(getDate("2023-11-16T17:30:00"), timeSlots.get(3).getEndDateTime());

            Assertions.assertEquals(getDate("2023-11-16T16:30:00"), timeSlots.get(4).getStartDateTime());
            Assertions.assertEquals(getDate("2023-11-16T18:00:00"), timeSlots.get(4).getEndDateTime());


        }

    }
}