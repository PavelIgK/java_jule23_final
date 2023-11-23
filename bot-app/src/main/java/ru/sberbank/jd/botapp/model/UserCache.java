package ru.sberbank.jd.botapp.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class UserCache {
    private String userId;
    private Long userIdTelegram;
    private String userLogin;
    private String firstName;
    private String lastName;
    private ChatInfo chatInfo;
    LocalDateTime createDateTime;
    @Builder.Default
    private boolean authorization = false;
}
