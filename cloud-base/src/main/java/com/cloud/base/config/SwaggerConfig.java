package com.cloud.base.config;

import com.cloud.base.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

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
//                    .securitySchemes(securitySchemes())
//                    .securityContexts(securityContexts());
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

    /**
     * 全局头部列表
     *
     * @return
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> list = new ArrayList<>();
        list.add(new ApiKey(Constants.HEADER_TOKEN, Constants.HEADER_TOKEN, "header"));
        return list;
    }

    /**
     * 全局参数匹配URL
     * 所有包含“auth”的接口不需要使用securitySchemes
     *
     * @return
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> list = new ArrayList<>();
        list.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        return list;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> list = new ArrayList<>();
        list.add(new SecurityReference("Authorization", authorizationScopes));
        return list;
    }
}
