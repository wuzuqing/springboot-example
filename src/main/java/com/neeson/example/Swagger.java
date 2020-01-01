package com.neeson.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author daile
 * @date 2017/8/14
 */
@Configuration
@EnableSwagger2
public class Swagger {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neeson.example"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API")
                .description("example")
                .termsOfServiceUrl("https://www.baidu.com/")//服务地址
//                .contact(new Contact("cqc", "https://www.baidu.com/", "leomple@163.com"))//联系信息
                .version("1.0")
                .build();
    }

}
