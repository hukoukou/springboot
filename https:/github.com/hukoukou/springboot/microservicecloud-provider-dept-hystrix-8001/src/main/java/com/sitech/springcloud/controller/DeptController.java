package com.sitech.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sitech.springcloud.entities.Dept;
import com.sitech.springcloud.service.DeptService;

//自带json
@RestController
public class DeptController {

	@Autowired
	private DeptService service;

	/**
	 * 
	 * 接收请求中占位符的值<br/>
	 * 一旦调用失败并抛出错误信息，就会@HystrixCommand标注好的fallbackMethod调用类中指定方法
	 * <p>
	 * .<br/>
	 * 
	 * @author: dawei
	 * @date: 2020年6月7日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = service.get(id);
		if (null == dept) {
			throw new RuntimeException("该ID：" + id + "没有对应信息！");
		}
		return dept;
	}

	/**
	 * 
	 * 异常返回方法<br/>
	 * <p>
	 * .<br/>
	 * 
	 * @author: dawei
	 * @date: 2020年6月7日
	 * @param id
	 * @return
	 */
	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应信息！null--@HystrixCommand").setDb_source("no database in mysql");
	}
}
