package com.nda.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI expenseAPI() {
        return new OpenAPI()
                .info(new Info().title("Employee Application")
                        .description("this is an application for employee crud operation")
                        .version("v1.0.0")
                        .contact(new Contact().name("Jaswanth Kalyan").email("jaswanthk3690@gmail.com").url("http://google.com"))
                        .license(new License().name("TOMCAT 9.0").url("http://springdoc.org")))
                        .externalDocs(new ExternalDocumentation()
                                        .description("Wiki Documentation")
                                        .url(""));
    }
}
