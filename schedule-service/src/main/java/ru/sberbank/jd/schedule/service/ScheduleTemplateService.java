package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.ScheduleTemplateDto;
import ru.sberbank.jd.schedule.entity.Schedule;
import ru.sberbank.jd.schedule.entity.ScheduleTemplate;
import ru.sberbank.jd.schedule.repository.ScheduleTemplateRepository;

/**
 * Сервис для работы с шаблонами расписаний.
 */
@Service
@RequiredArgsConstructor
public class ScheduleTemplateService {

    private final ScheduleTemplateRepository scheduleTemplateRepository;

    public List<ScheduleTemplateDto> getAllScheduleTemplate() {
        return scheduleTemplateRepository.findAll().stream().map(ScheduleTemplate::toDto).toList();
    }

    public ScheduleTemplateDto getScheduleTemplateById(UUID id) {
        return scheduleTemplateRepository.findById(id).get().toDto();
    }

    public List<ScheduleTemplateDto> getScheduleTemplateByPerformerId(UUID id) {
        return scheduleTemplateRepository.getScheduleTemplatesByPerformer_Id(id).stream().map(ScheduleTemplate::toDto).toList();
    }

    public ScheduleTemplateDto getScheduleTemplateByPerformerIdAnd(UUID id, String scheduleName) {
        return scheduleTemplateRepository.getScheduleTemplatesByPerformer_IdAndScheduleName(id, scheduleName).toDto();
    }

    public ScheduleTemplateDto addScheduleTemplate(ScheduleTemplateDto scheduleTemplateDto) {
        ScheduleTemplate scheduleTemplate = ScheduleTemplate.of(scheduleTemplateDto);
        scheduleTemplate.setId(UUID.randomUUID());
        return scheduleTemplateRepository.save(scheduleTemplate).toDto();
    }

    public ScheduleTemplateDto updateScheduleTemplate(ScheduleTemplateDto scheduleTemplateDto) {
        return scheduleTemplateRepository.save(ScheduleTemplate.of(scheduleTemplateDto)).toDto();
    }

    public void deleteScheduleTemplate(ScheduleTemplateDto scheduleTemplateDto) {
        scheduleTemplateRepository.delete(ScheduleTemplate.of(scheduleTemplateDto));
    }

    public void deleteScheduleTemplateById(UUID id) {
        scheduleTemplateRepository.deleteById(id);
    }

}
