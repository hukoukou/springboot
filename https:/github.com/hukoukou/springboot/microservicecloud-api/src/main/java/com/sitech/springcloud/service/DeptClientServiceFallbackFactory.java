package com.sitech.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sitech.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

//忘记添加就不好使了
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{

	@Override
	public DeptClientService create(Throwable cause) {
		return new DeptClientService() {
			
			@Override
			public List list() {
				return null;
			}
			
			@Override
			public Dept get(long id) {
				return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应信息！null--@HystrixCommand").setDb_source("no database in mysql");
			}
			
			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}
}
