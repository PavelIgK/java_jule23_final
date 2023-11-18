package ru.sberbank.jd.botapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInfo {

    private String performer;
    private String service;
    private String datetime;

}
