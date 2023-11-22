package ru.sberbank.jd.botapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CallbackData {
    private String commandClass;
    private String data;
}
