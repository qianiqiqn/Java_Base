package com.study.swaggers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author wenqianqian
 *
 * 访问页面地址：ip:端口/doc.html
 */
@Profile({"local","dev","test"})
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.sutpc.ruichang.expressway.controller")
                .title("swagger-ui")
                .description("相关接口文档")
                .contactName("macro")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }

}
