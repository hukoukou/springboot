package com.sitech.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//本服务自动注册到eureka
@EnableEurekaClient
@EnableDiscoveryClient
public class DeptProvider8002_APP {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8002_APP.class, args);
	}
}
