package com.sitech.springcloud.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitech.springcloud.entities.Dept;
import com.sitech.springcloud.service.DeptService;



//自带json
@RestController
public class DeptController {

	@Autowired
	private DeptService service;
	
	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	// @PathVariable 接收请求中占位符的值
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> get() {
		return service.list();
	}
	
	
	/**
	 * 服务发现测试
	 * @return
	 */
	@RequestMapping(value = "/dept/discivery", method = RequestMethod.GET)
	public Object discivery() {
		List<String> list = client.getServices();
		System.out.println("***********"+list);
		
		List<ServiceInstance> instances = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (int i = 0; i < instances.size(); i++) {
			ServiceInstance serviceInstance = instances.get(i);
			System.out.println(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost() +"\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
		}
		
		return this.client;
	}
}
