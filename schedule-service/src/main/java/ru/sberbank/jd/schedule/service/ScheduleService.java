package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.schedule.entity.Schedule;
import ru.sberbank.jd.schedule.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(UUID id) {
        return scheduleRepository.findById(id).get();
    }

    public List<Schedule> getScheduleByPerformerId(UUID id) {
        return scheduleRepository.getSchedulesByPerformer_Id(id);
    }

    public Schedule addSchedule(Schedule schedule) {
        schedule.setId(UUID.randomUUID());
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(getScheduleById(schedule.getId()));
    }

    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    public void deleteScheduleById(UUID id) {
        scheduleRepository.deleteById(id);
    }

}
