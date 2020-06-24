package com.kk.mall.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.kk.mall.common.Const;
import com.kk.mall.common.exception.CommonErrno;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/3/10 10:39 上午
 */
@Profile({"dev","test"})
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value = {"knife4j.enable"}, matchIfMissing = true)
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Arrays.stream(CommonErrno.values()).forEach(errorEnums -> {
            responseMessageList.add(
                    new ResponseMessageBuilder()
                            .code(errorEnums.getStatus())
                            .message(errorEnums.getMessage())
                            .build()
            );
        });

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("v1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage(Const.WEB_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .pathMapping("/");

    }
    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("Authorization", "Authorization", "header"));
    }
    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("kk_mall")
                .description("kk_mall")
                .version("1.0")
                .build();
    }

}

