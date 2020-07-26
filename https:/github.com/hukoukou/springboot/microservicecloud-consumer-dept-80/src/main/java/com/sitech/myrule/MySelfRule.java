package com.sitech.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRule {

	/**
	 * 重新定义所选负载策略，核心组件，复写IRule，可自定义
	 * 7个。参考：https://www.cnblogs.com/zhangjianbin/p/7157709.html
	 * 
	 * @return
	 */
	@Bean
	public IRule myRule() {
		// 随机
		//return new RandomRule();
		// 先轮询，当其中一个宕机后，轮询几次后跳过宕机的服务
		//return new RetryRule();
		//轮询调用5次
		return new RoundRobinRule_Dawei(5);
	}
}
