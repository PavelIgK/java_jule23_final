package ru.sberbank.jd.botapp.model;


import lombok.Builder;
import lombok.Data;
import ru.sberbank.jd.botapp.utils.CommandCatalog;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Data
@Builder(toBuilder = true)
public class UserCache {
    private Long userIdTelegram;
    private String userLogin;
    private String firstName;
    private String lastName;
    @Builder.Default
    private boolean authorization = false;
    @Builder.Default
    LinkedList<CommandCatalog> breadcrumbs = new LinkedList<>();
    LocalDateTime createDateTime;
}
