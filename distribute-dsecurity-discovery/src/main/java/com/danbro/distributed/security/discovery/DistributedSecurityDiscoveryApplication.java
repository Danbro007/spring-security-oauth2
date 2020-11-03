package com.danbro.distributed.security.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DistributedSecurityDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedSecurityDiscoveryApplication.class, args);
    }

}
