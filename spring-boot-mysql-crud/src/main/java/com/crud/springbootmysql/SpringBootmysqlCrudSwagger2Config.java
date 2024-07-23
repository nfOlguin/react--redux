package com.crud.springbootmysql;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = {"com.crud.springbootmysql.controller"})
@EnableSwagger2
public class SpringBootmysqlCrudSwagger2Config {

    @Bean
    public Docket loginApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("crud-springboot-react")
                .forCodeGeneration(true)
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
                .build()
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .enableUrlTemplating(false)
                .useDefaultResponseMessages(false);
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayOperationId(true)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .filter(true)
                .displayRequestDuration(true)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .operationsSorter(OperationsSorter.METHOD)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    private ApiInfo apiInfo(ReloadableResourceBundleMessageSource messageSource) {
        return new ApiInfoBuilder().title("crud-task")
                .version(messageSource.getMessage("spring-boot-mysql-crud.api.version", null, Locale.getDefault()))
                .description("spring-boot-mysql-crud").build();
    }
}
