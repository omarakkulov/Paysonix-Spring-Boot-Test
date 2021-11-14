package ru.akkulov.paysonixspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:myApp.properties")
public class Config {

    @Bean
    public StringBuilder getStringBuilderBean() {
        return new StringBuilder();
    }

}
