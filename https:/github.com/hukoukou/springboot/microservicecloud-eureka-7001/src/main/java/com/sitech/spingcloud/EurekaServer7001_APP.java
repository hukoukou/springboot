package com.sitech.spingcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//Eureka服务
@EnableEurekaServer
public class EurekaServer7001_APP {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer7001_APP.class, args);
	}

}
