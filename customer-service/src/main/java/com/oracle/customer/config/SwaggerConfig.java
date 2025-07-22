package com.oracle.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Onboarding API")
                        .version("1.0.0")
                        .description("This API handles customer registration, retrieval, update, and deletion for the customer onboarding workflow."));
    }
}
