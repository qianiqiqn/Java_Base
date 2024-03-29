package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wenqianqian
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.study.*")
public class JavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(JavaApplication.class, args);
    }
}
