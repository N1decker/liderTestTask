package ru.nidecker.liderTestTask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact()
                .email("eg0r.ershoff@yandex.ru")
                .name("Egor")
                .url("https://github.com/N1decker");

        Info info = new Info()
                .title("LiderTestTask")
                .contact(
                        contact
                )
                .description("Test task with technologies: java 8, maven, spring boot, hibernate, postgresql, liquibase");

        return new OpenAPI().info(info);
    }
}
