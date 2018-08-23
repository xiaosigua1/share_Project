package cn.bdqn.service.impl;

import cn.bdqn.bean.Dept;
import cn.bdqn.dao.DeptDao;
import cn.bdqn.dao.impl.DeptDaoImpl;
import cn.bdqn.service.DeptService;

public class DeptServiceImpl implements DeptService {

	private  DeptDao dao=new DeptDaoImpl();
	
	@Override
	public Dept getDept(Integer id) {
		return dao.getDept(id);
	}

}
