package com.delores.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

/**
 * @author William
 * @date 5/12/21 11:21 PM
 * @description
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.delores.medusa.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false);
//                .securitySchemes(newArrayList(apiKey()))
//                .securityContexts(newArrayList(securityContext()));;
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Medusa ACL API Documentation")
                .description("利用swagger2构建的API文档 -- 用restful风格写接口")
                .contact(new Contact("Delores", "www.delores.com", "chinawjm1993@gmail.com"))
                .termsOfServiceUrl("www.tands.com")
                .version("1.0")
                .build();
    }

//    private ApiKey apiKey() {
//        return new ApiKey("BearerToken", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/api/.*"))
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return newArrayList(new SecurityReference("BearerToken", authorizationScopes));
//    }
}
