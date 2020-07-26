package com.sitech.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //本服务自动注册到eureka
@EnableDiscoveryClient
@EnableCircuitBreaker //对Hystrix熔断器的支持
public class DeptProvider8001_Hystrix_APP {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8001_Hystrix_APP.class, args);
	}
}
