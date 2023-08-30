package com.study.config;

import org.codehaus.plexus.component.annotations.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: pengzhe
 * @Date: 2021/11/3 11:18
 * @Desc:
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
