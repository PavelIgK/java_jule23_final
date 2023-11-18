package ru.sberbank.jd.dto.schedule;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDto {

    private Date startDateTime;

    private Date endDateTime;

}
