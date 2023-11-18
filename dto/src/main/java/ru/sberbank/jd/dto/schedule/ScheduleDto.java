package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.UUID;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScheduleDto {

    private UUID id;

    private PerformerDto performer;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDateTime;

}
