package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.ScheduleDto;
import ru.sberbank.jd.schedule.entity.Schedule;
import ru.sberbank.jd.schedule.repository.ScheduleRepository;

/**
 * Сервис для работы с расписаниями.
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleDto> getAllSchedule() {
        return scheduleRepository.findAll()
                .stream()
                .map(Schedule::toDto)
                .sorted((o1, o2) -> o1.getStartDateTime().compareTo(o2.getStartDateTime()))
                .toList();
    }

    public ScheduleDto getScheduleById(UUID id) {
        return scheduleRepository.findById(id).get().toDto();
    }

    public List<ScheduleDto> getScheduleByPerformerId(UUID id) {
        return scheduleRepository.getSchedulesByPerformer_Id(id)
                .stream()
                .map(Schedule::toDto)
                .sorted((o1, o2) -> o1.getStartDateTime().compareTo(o2.getStartDateTime()))
                .toList();
    }

    public ScheduleDto addSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = Schedule.of(scheduleDto);
        schedule.setId(UUID.randomUUID());
        return scheduleRepository.save(schedule).toDto();
    }

    public ScheduleDto updateSchedule(ScheduleDto scheduleDto) {
        return scheduleRepository.save(Schedule.of(scheduleDto)).toDto();
    }

    public void deleteSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = Schedule.of(scheduleDto);
        scheduleRepository.delete(schedule);
    }

    public void deleteScheduleById(UUID id) {
        scheduleRepository.deleteById(id);
    }

}
