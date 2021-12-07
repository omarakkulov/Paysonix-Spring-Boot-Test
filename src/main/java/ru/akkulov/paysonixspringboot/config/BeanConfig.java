package ru.akkulov.paysonixspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:myApp.properties")
public class BeanConfig {

    @Bean
    public StringBuilder getStringBuilderBean() {
        return new StringBuilder();
    }

}
