package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.schedule.entity.ScheduleTemplate;
import ru.sberbank.jd.schedule.repository.ScheduleTemplateRepository;

@Service
@RequiredArgsConstructor
public class ScheduleTemplateService {

    private final ScheduleTemplateRepository scheduleTemplateRepository;

    public List<ScheduleTemplate> getAllScheduleTemplate() {
        return scheduleTemplateRepository.findAll();
    }

    public ScheduleTemplate getScheduleTemplateById(UUID id) {
        return scheduleTemplateRepository.findById(id).get();
    }

    public List<ScheduleTemplate> getScheduleTemplateByPerformerId(UUID id) {
        return scheduleTemplateRepository.getScheduleTemplatesByPerformer_Id(id);
    }

    public ScheduleTemplate getScheduleTemplateByPerformerIdAnd(UUID id, String scheduleName) {
        return scheduleTemplateRepository.getScheduleTemplatesByPerformer_IdAndScheduleName(id, scheduleName);
    }

    public ScheduleTemplate addScheduleTemplate(ScheduleTemplate scheduleTemplate) {
        scheduleTemplate.setId(UUID.randomUUID());
        return scheduleTemplateRepository.save(scheduleTemplate);
    }

    public ScheduleTemplate updateScheduleTemplate(ScheduleTemplate scheduleTemplate) {
        return scheduleTemplateRepository.save(getScheduleTemplateById(scheduleTemplate.getId()));
    }

    public void deleteScheduleTemplate(ScheduleTemplate scheduleTemplate) {
        scheduleTemplateRepository.delete(scheduleTemplate);
    }

    public void deleteScheduleTemplateById(UUID id) {
        scheduleTemplateRepository.deleteById(id);
    }

}
