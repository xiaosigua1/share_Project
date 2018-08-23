package cn.bdqn.test;

import java.util.List;

import oracle.net.aso.d;
import oracle.net.aso.s;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.bdqn.bean.Dept;
import cn.bdqn.bean.Emp;
import cn.bdqn.util.HibernateSessionFactory;

public class Test3 {

	/*
	 * 原生SQL查询 查询员工姓名中有'xx'并且职位是'程序员'的员工
	 */
	@Test
	public void test01() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createSQLQuery(
						"select *from emp where empname like :name and job=:job")
				.setString("name", "%小黑%").setString("job", "程序员");
		List<Object[]> list = query.list();
		for (Object[] emp : list) {
			System.out.print(emp[0]);
			System.out.print(emp[1]);
			System.out.print(emp[2]);
			System.out.println(emp[3]);
		}
	}

	/**
	 * SQLQuery接口提供了addEntity()方法把查询结果集中的关系数据映射为对象。
	 */
	@Test
	public void test02() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createSQLQuery(
						"select *from emp where empname like :name and job=:job")
				.addEntity(Emp.class).setString("name", "%黑%")
				.setString("job", "程序员");
		List<Emp> list = query.list();
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}

	/**
	 * 查询指定职位的员工，并打印员工姓名和所在部门名称
	 */
	@Test
	public void test03() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createSQLQuery(
						"select e.*,d.* from emp e,dept d where e.deptno=d.deptno and job=:job")
				.addEntity("e", Emp.class).addJoin("d", "e.dept")
				.setString("job", "程序员");
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			Emp emp = (Emp) object[0];
			Dept dept = (Dept) object[1];
			System.out.println(emp.getEmpName());
			System.out.println(emp.getDept().getDeptName());
		}
	}

	// HQL查询 根据员工姓名查询员工
	@Test
	public void test04() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.getNamedQuery("selectEmpByName").setString(
				"name", "大黑");
		List list = query.list();
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	// SQL查询 根据员工姓名查询员工
	@Test
	public void test05() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.getNamedQuery("sqlSelect").setString(
				"name", "大黑");
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			System.out.println(object[0]);
			System.out.println(object[1]);
			System.out.println(object[2]);
			System.out.println(object[3]);
		}
	}
	
	/**
	 * 查询指定职位的员工，并打印员工姓名和所在部门名称
	 */
	@Test
	public void test06() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.getNamedQuery("selectToDept").setString("job", "程序员");
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			Emp emp = (Emp) object[0];
			Dept dept = (Dept) object[1];
			System.out.println(emp.getEmpName());
			System.out.println(emp.getDept().getDeptName());
		}
	}
	
	
	
	
	//一级缓存
	//两次加载同一个dept对象
	@Test
	public  void test07(){
		 Session session = HibernateSessionFactory.getSession();
		 Dept dept1=(Dept) session.get(Dept.class, 1);
		 System.out.println(session.hashCode());
		 Dept dept3=(Dept) session.get(Dept.class, 2);
		 System.out.println(session.hashCode());
		 session.evict(dept1);
		 //123行执行完,缓存中就已经存在dept对象了
		 Dept dept2=(Dept) session.get(Dept.class, 1);
		 //125首先会去找缓存中有没有!有就不会进行一次查询了
		 System.out.println(session.hashCode());
		 session.close();
		
	}

	
	
	
	
	
	
	
	
	
	/**
	 * 使用evict()方法清除指定的持久化对象。
	 */
	@Test
	public void test08(){
		Session session = HibernateSessionFactory.getSession();
		 Dept dept1=(Dept) session.get(Dept.class, 1);
		 //使用evic()清楚dept1
		 session.evict(dept1);
		 System.out.println("**********************");
		 Dept dept2=(Dept) session.get(Dept.class, 1);
		 System.out.println(dept1+"++++"+dept2);
		 session.close();
	}
	
	/**
	 * 使用evict()方法新增一个对象
	 */
	@Test
	public void test09(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Dept dept=new Dept(2, "明星部", "18楼");
		session.save(dept);
		//save()进行flush()之后再evict()也能新增成功
		//session.flush();
		//使用evict()清楚对象后,就不会提交成功
		session.evict(dept);
		//提交事务
		transaction.commit();
		session.close();
	}
	
	
	
	@Test
	public void test999(){
	  Session session = HibernateSessionFactory.getSession();
	   Transaction transaction = session.beginTransaction();
	   Dept dept=null;
	   for (int i = 6; i <=1000; i++) {
		  dept=(Dept) session.get(Dept.class, i);
		  session.delete(dept);
	  }
	  transaction.commit();
	  session.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 修改dept对象
	 */
	@Test
	public void test10(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Dept dept=(Dept) session.get(Dept.class, 1);
		dept.setDeptName("haha");
		dept.setDeptName("aaaa");
		//这样只会保存后面修改的内容
		transaction.commit();
		session.close();
	}
	
	
	/**
	 * 批量添加Emp对象
	 * 
	 */
	@Test
	public void test11(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Emp emp=null;
		for (int i =1; i <100; i++) {
			emp=new Emp(i, "emp"+i, "job"+i);
			session.save(emp);
			if (i%20==0) {
				session.flush();
				session.clear();
			}
		}
		transaction.commit();
		session.close();
	}
	
	
	//2级缓存
	@Test
	public void test12(){
		Session session = HibernateSessionFactory.getSession();
		Dept dept1=(Dept) session.get(Dept.class, 1);
		System.out.println(dept1);
		session.close();
		session=HibernateSessionFactory.getSession();
		Dept dept2=(Dept) session.get(Dept.class, 1);
		System.out.println(dept2);
		session.close();
		session=HibernateSessionFactory.getSession();
		Dept dept3=(Dept) session.get(Dept.class, 1);
		System.out.println(dept3);
		session.close();
	}
	
	
	//查询缓存
	@Test
	public void test13(){
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Emp");
		//手动开启查询缓存
		query.setCacheable(true);
		List list = query.list();
		for (Object object : list) {
			System.out.println(object);
		}
		Query query2 = session.createQuery("from Emp");
		//手动开启查询缓存
		query2.setCacheable(true);
		List list2 = query2.list();
		for (Object object : list2) {
			System.out.println(object);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
