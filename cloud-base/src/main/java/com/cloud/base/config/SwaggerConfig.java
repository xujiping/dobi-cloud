package com.cloud.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author xujiping
 * @date 2018/6/14 15:30
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Value("${swagger.package}")
    private String swaggerPackage;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.name}")
    private String name;

    @Value("${swagger.url}")
    private String url;

    @Value("${swagger.email}")
    private String email;

    @Value("${swagger.description}")
    private String description;

    @Bean
    public Docket createRestApi() {
        if (swaggerShow) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    //当前包路径
                    .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                    .paths(PathSelectors.any())
                    .build();
        } else {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    //当前包路径
                    .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                    .paths(PathSelectors.none())
                    .build();
        }

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(name, url, email))
                .version(version)
                .description(description)
                .build();
    }
}
