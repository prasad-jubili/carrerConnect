package com.carrerconnect.job_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Add Global API Info (Enterprise Level), Now Swagger UI looks professional.
@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Career Connect Job Service API")
                            .version("1.0")
                            .description("API documentation for Job Service"));
        }
    }

