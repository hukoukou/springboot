package com.sitech.myrule;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 需求：轮询调用，但是每个要求被调用5次后再换
 * 
 * @author dawei
 *
 */
public class RoundRobinRule_Dawei extends AbstractLoadBalancerRule {

	private int chooseInt;

	// total：定义指针，每调用一次，+1，当指针==5，指针下移
	// currentIndex：当前对外提供服务的服务器地址（服务下标）
	// 当前问题：我们5次，但是服务只有三个

	// 总共被调用的次数，目前要求调用n次
	private int total = 0;
	// 当前服务的机器号
	private int currentIndex = 0;

	public Server choose(ILoadBalancer lb, Object key, int chooseInt) {
		if (lb == null) {
			return null;
		}

		// 此时还不知到哪一个server来相应请求，所以暂时设置为null
		Server server = null;
		// 此时用while，表明当线程苏醒后，还会再判断一次
		while (server == null) {
			// 线程是否中断
			if (Thread.interrupted()) {
				return null;
			}
			// 获取可用服务
			List<Server> upList = lb.getReachableServers();
			// 获取所有服务
			List<Server> allList = lb.getAllServers();
			int serverCount = allList.size();

			if (serverCount == 0) {
				return null;
			}
			// 不用了，自己写
			// int index = chooseRandomInt(serverCount);
			// 获取随机下标服务
			// server = upList.get(index);

			// 自定义随机规则
			if (total < chooseInt) {
				server = upList.get(currentIndex);
				total++;
			} else {
				total = 0;
				currentIndex++;
				if (currentIndex >= upList.size()) {
					currentIndex = 0;
				}
			}

			if (server == null) {
				Thread.yield();
				continue;
			}

			// 判断服务是否存活
			if (server.isAlive()) {
				return (server);
			}
			server = null;
			Thread.yield();
		}
		return server;

	}

	/**
	 * 随机挑选服务的下标
	 * 
	 * @param serverCount
	 * @return
	 */
	protected int chooseRandomInt(int serverCount) {
		return ThreadLocalRandom.current().nextInt(serverCount);
	}

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key, chooseInt);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {

	}

	public RoundRobinRule_Dawei(int chooseInt) {
		super();
		this.chooseInt = chooseInt;
	}

	public RoundRobinRule_Dawei() {
		super();
	}
}
