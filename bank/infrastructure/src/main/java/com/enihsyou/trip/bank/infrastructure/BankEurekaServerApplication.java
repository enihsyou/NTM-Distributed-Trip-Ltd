package com.enihsyou.trip.bank.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Profile;

@Profile("eureka-server")
@SpringBootApplication
@EnableEurekaServer
public class BankEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankEurekaServerApplication.class, args);
    }
}
