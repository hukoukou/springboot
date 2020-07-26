package com.sitech.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sitech.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {
	
	//private static final String REST_URL_PREFIX = "http://localhost:8001";
	// 通过eureka调用
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
	
	
	@Autowired
	private RestTemplate restTemplate;

	
	/**
	 * postForObject(url //REST请求地址,request //请求参数,responseType //HTTP响应抓换被转换成的对象类型)
	 * @param dept
	 * @return
	 */
	@RequestMapping(value = "/consumser/dept/add")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept, Boolean.class);
	}
	
	
	/**
	 * getForObject(url //REST请求地址,responseType //HTTP响应抓换被转换成的对象类型)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/consumser/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
	}
	
	
	/**
	 * getForObject(url //REST请求地址,responseType //HTTP响应抓换被转换成的对象类型)
	 * @return
	 */
	@RequestMapping(value = "/consumser/list")
	public List<Dept> list() {
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
	}
	
	/**
	 * 服务发现测试
	 * @return
	 */
	@RequestMapping(value = "/consumser/discivery")
	public Object discivery() {
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discivery", Object.class);
	}
}
