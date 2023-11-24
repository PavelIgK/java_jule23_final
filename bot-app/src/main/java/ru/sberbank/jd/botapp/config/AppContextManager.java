package ru.sberbank.jd.botapp.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Конфигурируем контекст.
 *
 */
@Component
public class AppContextManager implements ApplicationContextAware {
    private static ApplicationContext _appCtx;

    @Override
    public void setApplicationContext(ApplicationContext ctx){
        _appCtx = ctx;
    }

    public static ApplicationContext getAppContext(){
        return _appCtx;
    }
}
