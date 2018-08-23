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
	 * ԭ��SQL��ѯ ��ѯԱ����������'xx'����ְλ��'����Ա'��Ա��
	 */
	@Test
	public void test01() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createSQLQuery(
						"select *from emp where empname like :name and job=:job")
				.setString("name", "%С��%").setString("job", "����Ա");
		List<Object[]> list = query.list();
		for (Object[] emp : list) {
			System.out.print(emp[0]);
			System.out.print(emp[1]);
			System.out.print(emp[2]);
			System.out.println(emp[3]);
		}
	}

	/**
	 * SQLQuery�ӿ��ṩ��addEntity()�����Ѳ�ѯ������еĹ�ϵ����ӳ��Ϊ����
	 */
	@Test
	public void test02() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createSQLQuery(
						"select *from emp where empname like :name and job=:job")
				.addEntity(Emp.class).setString("name", "%��%")
				.setString("job", "����Ա");
		List<Emp> list = query.list();
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}

	/**
	 * ��ѯָ��ְλ��Ա��������ӡԱ�����������ڲ�������
	 */
	@Test
	public void test03() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createSQLQuery(
						"select e.*,d.* from emp e,dept d where e.deptno=d.deptno and job=:job")
				.addEntity("e", Emp.class).addJoin("d", "e.dept")
				.setString("job", "����Ա");
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			Emp emp = (Emp) object[0];
			Dept dept = (Dept) object[1];
			System.out.println(emp.getEmpName());
			System.out.println(emp.getDept().getDeptName());
		}
	}

	// HQL��ѯ ����Ա��������ѯԱ��
	@Test
	public void test04() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.getNamedQuery("selectEmpByName").setString(
				"name", "���");
		List list = query.list();
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	// SQL��ѯ ����Ա��������ѯԱ��
	@Test
	public void test05() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.getNamedQuery("sqlSelect").setString(
				"name", "���");
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			System.out.println(object[0]);
			System.out.println(object[1]);
			System.out.println(object[2]);
			System.out.println(object[3]);
		}
	}
	
	/**
	 * ��ѯָ��ְλ��Ա��������ӡԱ�����������ڲ�������
	 */
	@Test
	public void test06() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.getNamedQuery("selectToDept").setString("job", "����Ա");
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			Emp emp = (Emp) object[0];
			Dept dept = (Dept) object[1];
			System.out.println(emp.getEmpName());
			System.out.println(emp.getDept().getDeptName());
		}
	}
	
	
	
	
	//һ������
	//���μ���ͬһ��dept����
	@Test
	public  void test07(){
		 Session session = HibernateSessionFactory.getSession();
		 Dept dept1=(Dept) session.get(Dept.class, 1);
		 System.out.println(session.hashCode());
		 Dept dept3=(Dept) session.get(Dept.class, 2);
		 System.out.println(session.hashCode());
		 session.evict(dept1);
		 //123��ִ����,�����о��Ѿ�����dept������
		 Dept dept2=(Dept) session.get(Dept.class, 1);
		 //125���Ȼ�ȥ�һ�������û��!�оͲ������һ�β�ѯ��
		 System.out.println(session.hashCode());
		 session.close();
		
	}

	
	
	
	
	
	
	
	
	
	/**
	 * ʹ��evict()�������ָ���ĳ־û�����
	 */
	@Test
	public void test08(){
		Session session = HibernateSessionFactory.getSession();
		 Dept dept1=(Dept) session.get(Dept.class, 1);
		 //ʹ��evic()���dept1
		 session.evict(dept1);
		 System.out.println("**********************");
		 Dept dept2=(Dept) session.get(Dept.class, 1);
		 System.out.println(dept1+"++++"+dept2);
		 session.close();
	}
	
	/**
	 * ʹ��evict()��������һ������
	 */
	@Test
	public void test09(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Dept dept=new Dept(2, "���ǲ�", "18¥");
		session.save(dept);
		//save()����flush()֮����evict()Ҳ�������ɹ�
		//session.flush();
		//ʹ��evict()��������,�Ͳ����ύ�ɹ�
		session.evict(dept);
		//�ύ����
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
	 * �޸�dept����
	 */
	@Test
	public void test10(){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Dept dept=(Dept) session.get(Dept.class, 1);
		dept.setDeptName("haha");
		dept.setDeptName("aaaa");
		//����ֻ�ᱣ������޸ĵ�����
		transaction.commit();
		session.close();
	}
	
	
	/**
	 * �������Emp����
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
	
	
	//2������
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
	
	
	//��ѯ����
	@Test
	public void test13(){
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Emp");
		//�ֶ�������ѯ����
		query.setCacheable(true);
		List list = query.list();
		for (Object object : list) {
			System.out.println(object);
		}
		Query query2 = session.createQuery("from Emp");
		//�ֶ�������ѯ����
		query2.setCacheable(true);
		List list2 = query2.list();
		for (Object object : list2) {
			System.out.println(object);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
