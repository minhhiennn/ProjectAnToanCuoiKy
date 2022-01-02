package com.projectcuoiky.atbm.configure.thymeleafDirect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

@Configuration
public class MyConfiguration {

    @Bean(name = "langugesContext")
    public LangugesControl langugesService() {
        return () -> {
            String currentLanguage = LocaleContextHolder.getLocale().toString();
            return currentLanguage;
        };
    }
}

