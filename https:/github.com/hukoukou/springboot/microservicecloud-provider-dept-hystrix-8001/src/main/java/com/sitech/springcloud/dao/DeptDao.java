package com.sitech.springcloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sitech.springcloud.entities.Dept;

@Mapper
public interface DeptDao {

	public boolean addDept();
	
	public Dept findDeptId(Long deptno);
	
	public List<Dept> findAll();
	
}
