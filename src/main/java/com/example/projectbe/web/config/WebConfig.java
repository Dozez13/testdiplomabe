package com.example.projectbe.web.config;

import com.example.projectbe.core.serializer.AppLocalDateTimeDeserializer;
import com.example.projectbe.core.serializer.AppLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class WebConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Module afterBurner() {
        return new AfterburnerModule();
    }

    @Bean
    public Module appLocalDateTime(AppLocalDateTimeSerializer appLocalDateTimeSerializer, AppLocalDateTimeDeserializer appLocalDateTimeDeserializer) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, appLocalDateTimeSerializer);
        module.addDeserializer(LocalDateTime.class, appLocalDateTimeDeserializer);
        return module;
    }
}
