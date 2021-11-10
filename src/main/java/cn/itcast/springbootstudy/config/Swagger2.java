package cn.itcast.springbootstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*http://localhost:8888/swagger-ui.html通过该地址访问swagger2,可以通过swagger2提供的API注解对代码进行注解，进而对其页面中的属性进行描述*/
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.itcast.springbootstudy"))
                .paths(PathSelectors.regex("/rest/.*"))
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring_Boot利用Swagger构建Api文档")
                .description("简单优雅的restful风格")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("v1.0")
                .build();
    }
}