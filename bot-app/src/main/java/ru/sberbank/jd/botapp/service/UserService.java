package ru.sberbank.jd.botapp.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.dto.authorization.UserDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return botConfig.getScheduleServiceUrl() + "/user";
    }

    public List<UserDto> findAllUsers() {
        return Arrays.asList(restTemplate.getForObject(getUri(), UserDto[].class));
    }

    public UserDto getUserByTelegramId(String id) {
        return restTemplate.getForObject(getUri()+"/telegramId/"+id,UserDto.class);
    }

}
