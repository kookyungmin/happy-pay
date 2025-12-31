package net.happykoo.membership.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(apiInfo());
  }

  private Info apiInfo() {
    return new Info()
        .title("Happy Pay 멤버쉽 서비스")
        .description("Spring docs swagger UI")
        .version("1.0.0");
  }

//  @Bean
//  public OpenAPI openAPI() {
//    return new OpenAPI()
//        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
//        .components(
//            new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
//        .info(apiInfo());
//  }

//  private SecurityScheme createAPIKeyScheme() {
//    return new SecurityScheme().type(Type.HTTP)
//        .bearerFormat("JWT")
//        .scheme("bearer");
//  }

}
