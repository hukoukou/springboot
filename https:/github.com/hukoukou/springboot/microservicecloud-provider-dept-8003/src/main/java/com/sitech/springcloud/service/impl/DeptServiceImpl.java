package com.sitech.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.springcloud.dao.DeptDao;
import com.sitech.springcloud.entities.Dept;
import com.sitech.springcloud.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao dao;

	@Override
	public boolean add(Dept dept) {
		return dao.addDept();
	}

	@Override
	public Dept get(Long id) {
		return dao.findDeptId(id);
	}

	@Override
	public List<Dept> list() {
		return dao.findAll();
	}

}
