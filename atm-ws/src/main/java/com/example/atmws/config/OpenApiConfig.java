package com.example.atmws.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${springdoc.custom.titleApp}")
    private String titleApp;
    @Value("${springdoc.custom.versionApp}")
    private String versionApp;

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title(titleApp)
                        .version(versionApp)
                        .description("")
                        .license(new License().url("https://springdoc.org/"))
                );
    }
}
