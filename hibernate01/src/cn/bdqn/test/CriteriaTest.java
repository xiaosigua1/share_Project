package cn.bdqn.test;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import cn.bdqn.bean.Dept;
import cn.bdqn.bean.Emp;
import cn.bdqn.util.HibernateSessionFactory;

public class CriteriaTest {
	
	  
	@Test
	public  void test111(){
		Session session = HibernateSessionFactory.getSession();
		List list = session.createQuery("from Dept").list();
		for (Object object : list) {
			System.out.println(object);
		}
		session.close();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// �������Ӳ�ѯ���ű�
	@Test
	public void test1() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Dept d  left join d.emps");
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			System.out.println("�� �ߵı�!!!"+object[0]);
			System.out.println("�� �ߵı�!!!"+object[1]);
		}
	}

	// �����������Ӳ�ѯ���ű�
	@Test
	public void test2() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Dept d left join fetch  d.emps");
	   List<Dept> list = query.list();
	  for (Dept dept : list) {
		System.out.println(dept.getEmps());
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
		// Ϊʲôû�������������Ӳ�ѯ
		@Test
		public void test3() {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from Emp e right join fetch  e.dept");
			List<Emp> list = query.list();
			for (Emp emp : list) {
				System.out.println(emp.getDept());
			}
	}
	

}
