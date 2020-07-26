package com.sitech.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.sitech.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
// 自定义负载策略，不能放在@ComponentScan所扫描的当前包和自包下（@SpringBootApplication里面是ComponentScan）
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class)
public class DeptConsumer80_APP {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_APP.class, args);
	}
}
