package com.kk.mall.api;

import com.kk.mall.common.Const;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = Const.BASE_PACKAGE)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
