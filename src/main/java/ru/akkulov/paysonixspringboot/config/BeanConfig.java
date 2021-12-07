package ru.akkulov.paysonixspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public StringBuilder getStringBuilderBean() {
        return new StringBuilder();
    }

}
