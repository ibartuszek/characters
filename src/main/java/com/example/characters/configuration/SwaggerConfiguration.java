package com.example.characters.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.characters.web"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInformation());
    }

    private ApiInfo getApiInformation() {
        return new ApiInfo("Characters API",
            "The aim of the API is to get a list of marvel characters' ids or information about a specified character",
            "1.0",
            "API Terms of Service URL",
            new Contact("Istvan Bartuszek", null, "istvan.bartuszek@gmail.com"),
            "API License",
            "API License URL",
            Collections.emptyList()
        );
    }

}

