package cn.bdqn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.bdqn.bean.Dept;
import cn.bdqn.dao.DeptDao;
import cn.bdqn.util.HibernateSessionFactory;

public class DeptDaoImpl implements DeptDao {
	@Override
	public Dept getDept(Integer id) {
	Session session=HibernateSessionFactory.getSession();
	Dept dept=(Dept) session.load(Dept.class, id);
	System.out.println(session.hashCode());
	return dept;
	}


}
