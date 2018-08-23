package cn.bdqn.dao;

import java.util.List;

import cn.bdqn.bean.Dept;



public interface DeptDao {

	//查询指定ID的部门信息
	public Dept getDept(Integer id);
}
