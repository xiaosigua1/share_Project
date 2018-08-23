package cn.bdqn.test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.bdqn.bean.Dept;
import cn.bdqn.bean.District;
import cn.bdqn.bean.Emp;
import cn.bdqn.bean.Street;
import cn.bdqn.bean.Users;
import cn.bdqn.service.DeptService;
import cn.bdqn.service.impl.DeptServiceImpl;
import cn.bdqn.util.HibernateSessionFactory;

public class HibernateTest {

	@Test
	public void test1(){
		DeptService service=new DeptServiceImpl();
		Dept dept=service.getDept(1);
		System.out.println(1111);
		System.out.println(dept);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// �����û�
	@Test
	public void newUser() {
		// ��ȡ�����ļ�
		Configuration configuration = new Configuration().configure();
		// ����sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		// ����session
		Session session = sessionFactory.openSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ���в���
		Users user = new Users(3, "С����", "����");
		// �־û�����
		session.save(user);
		// �ύ����
		transaction.commit();
		// �ر�Session
		session.close();
	}

	// �����������ָ�����û�
	@Test
	public void getUser() {
		Configuration configuration = new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		// get()��������,�����users����Ϣ
		Object object = session.get(Users.class, 9);
		System.out.println(object);
		session.close();
		/*
		 * load()Ĭ�����ӳټ���,������users��Ϣ��ʱ��,session�Ѿ��ر��� ��ʵ�ֵĻ�,�͵���ӳ���ļ��м���
		 * lazy="false"
		 */
	}

	// �޸�ָ�����û���Ϣ
	@Test
	public void updateUser() {
		Configuration configuration = new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ��ȡ��Ҫ�޸ĵ��û� 1
		Users users1 = new Users(); // ˲ʱ״̬
		users1.setId(4);
		Users users2 = (Users) session.get(Users.class, 1);
		Users users3 = (Users) session.load(Users.class, 1);
		// ���һ���ֽ��߿����ӳټ��غͼ�ʱ���ص�����
		System.out.println("*************************");
		// ����û�����Ϣ
		System.out.println(users2);
		// �޸��û�����Ϣ
		users2.setName("���ں�");
		// session.save(users);
		session.saveOrUpdate(users2);
		// �ύ����
		// session.flush();
		transaction.commit();
		// ��ʾ�޸ĺ���û���Ϣ
		System.out.println(users2);
		session.close();
	}

	// ɾ��ָ�����û���Ϣ
	@Test
	public void delUser() {
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction beginTransaction = session.beginTransaction();
		// ��ȡ��Ҫɾ�����û�
		Users users = (Users) session.get(Users.class, 1);
		// ɾ���û�
		session.delete(users);
		// �ύ����
		// beginTransaction.commit();
		session.close();
	}

	// ��ѯ�����˵���Ϣ(list)
	@Test
	public void showList() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��д��ѯ������Ա��HQL���
		String hql = "from Users";
		// ����Query����
		Query createQuery = session.createQuery(hql);
		// ִ�в�ѯ
		List<Users> list = createQuery.list();
		// �����ѯ����Ϣ
		for (Users object : list) {
			System.out.println(object);
		}
		session.close();
	}

	// ��ѯ�����˵���Ϣ(iterator) û�л�������
	@Test
	public void showIterator1() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��д��ѯ������Ա��HQL���
		String hql = "from Users";
		// ����Query����
		Query query = session.createQuery(hql);
		// ִ�в�ѯ
		Iterator iterate = query.iterate();
		// whileѭ�������ѯ����Ϣ
		while (iterate.hasNext()) {
			Object object = (Object) iterate.next();
			System.out.println(object);
		}
		session.close();
	}

	// ��ѯ�����˵���Ϣ(iterator) �л�������
	@Test
	public void showIterator2() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��д��ѯ������Ա��HQL���
		String hql = "from Users";
		// ����Query����
		Query query = session.createQuery(hql);
		// ��ʹ��list()ִ�в�ѯ
		List list = query.list();
		// ��ʹ��iterator��ѯ
		Iterator iterator = query.iterate();
		// whileѭ�������ѯ����Ϣ
		while (iterator.hasNext()) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
		session.close();
	}

	// ������:����λ�ð�
	@Test
	public void first() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��д��ѯ������"С�ں�"��HQL���
		String hql = "from Users where name like ?";
		// ����Query����
		Query query = session.createQuery(hql);
		// 01.ͨ��setString()Ϊռλ����ֵ
		// query.setString(0, "С����");
		// 02.ͨ��setParameter()Ϊռλ����ֵ
		query.setParameter(0, "%С%");
		// ִ�в�ѯ
		List<Users> list = query.list();
		// �����ѯ���
		for (Users users2 : list) {

			System.out.println(users2);
		}
		session.close();
	}

	// ������:�������ư�
	@Test
	public void second() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��д��ѯ������"���ں�"��HQL���
		String hql = "from Users where name =:name";
		// ����Query����
		Query query = session.createQuery(hql);
		// 01.ͨ��setString()Ϊ����������ֵ
		query.setString("name", "���ں�");
		// 02.ͨ��setParameter()Ϊ����������ֵ
		// ִ�в�ѯ
		Users users = (Users) query.uniqueResult();
		// �����ѯ���
		System.out.println(users);
		session.close();
	}

	// ��̬������
	@Test
	public void third() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ׼����ѯ����,Users�����װ����
		Users users = new Users();
		// idΪ1
		users.setId(1);
		// nameΪ"С�ں�"
		users.setName("С�ں�");
		// ����Ϊ"�װ�"
		users.setPassword("�װ�");
		// ׼��hql��hql����������̬����
		StringBuilder hql = new StringBuilder("from Users as users where 1=1");
		// ƴ�Ӳ���
		hql.append(" and users.id=:id");
		hql.append(" and users.name=:name");
		hql.append(" and users.password=:password");
		// ����Query����
		Query query = session.createQuery(hql.toString());
		// ͨ��setProperties()Ϊ������ֵ
		query.setProperties(users);
		// ִ�в�ѯ
		Users user = (Users) query.uniqueResult();
		// �����ѯ���
		System.out.println(user);
		session.close();
	}

	// ģ����ѯ
	@Test
	public void showLike() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��д��ѯ������������"С"�ĵ�HQL���
		String hql = "from Users where name like :name";
		// ����Query����
		Query query = session.createQuery(hql);
		// Ϊ����������ֵ
		query.setParameter("name", "%С%");
		// ִ�в�ѯ
		List list = query.list();
		// �����ѯ����Ϣ
		for (Object object : list) {
			System.out.println(object);
		}
		session.close();
	}

	// ͶӰ��ѯ,��ÿ����ѯ�����װ��object����
	@Test
	public void test01() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ѯ����������HQL���
		String hql = "select eName,job from Emp";
		// ����Query����
		Query query = session.createQuery(hql);
		// ִ�в�ѯ
		List<Object[]> list = query.list();
		// �����ѯ����Ϣ
		for (Object[] object : list) {
			System.out.println(object[0]);
			System.out.println(object[1]);
		}
	}

	// ͶӰ��ѯ,��ÿ����ѯ�����װ��object����
	@Test
	public void test02() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ѯ���������������HQL���
		String hql = "select name,password from Users";
		// ����Query����
		Query query = session.createQuery(hql);
		// ִ�в�ѯ
		List<Object[]> list = query.list();
		// �����ѯ����Ϣ
		for (Object[] object : list) {
			System.out.println("����:" + object[0]);
			System.out.println("����:" + object[1]);
			// ������ת����һ���ַ���
			System.out.println(Arrays.toString(object));
		}
		session.close();
	}

	// ͶӰ��ѯ,��ÿ����ѯ���ͨ�����캯����װ�ɶ���
	@Test
	public void test03() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ѯ����������(��ÿ����ѯ���ͨ�����캯����װ��),Ҫ��֤�������ж�Ӧ�Ĺ��캯��
		String hql = "select new Emp(eName,job) from Emp";
		// ����Query����
		List list = session.createQuery(hql).list();
		// �����ѯ����Ϣ
	}

	// ��ҳ��ѯ,��ѯ��Ա��Ϣ,����ID����,ÿҳ��ʾ2������,�����1ҳ����Ա����
	@Test
	public void page() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ѯ��Ա��Ϣ(����)
		String hql = "from Users order by id desc";
		// ����Query����
		Query query = session.createQuery(hql);
		// ÿҳ��ʾ2������
		int pageSize = 2;
		// ��ѯ�ܼ�¼����HQL
		String hqlCount = "select count(*) from Users";
		// ִ�в�ѯ�ܼ�¼��
		int pageCounts = ((Long) session.createQuery(hqlCount).uniqueResult())
				.intValue();
		// ������ҳ��(�ܼ�¼��%ҳ��С==0)?(�ܼ�¼��/ҳ��С):(�ܼ�¼��/ҳ��С+1)
		int totalPage = (pageCounts % pageSize == 0) ? (pageCounts / pageSize)
				: (pageCounts / pageSize + 1);
		// ������ʾ�ڼ�ҳ������
		int pageIndex = 2;
		// ���ôӵڼ�����ʼ���,����������(��ǰҳ��-1)*ҳ��С
		query.setFirstResult((pageIndex - 1) * pageSize);
		// ����ÿҳ��ʾ������¼��
		query.setMaxResults(pageSize);
		// ִ�в�ѯ
		List<Users> list = query.list();
		// �����ѯ����Ϣ(������ҳ,��ǰ�ڼ�ҳ)
		for (Users users : list) {
			System.out.println(users);
		}

	}

	// ����Ķ��һ(��Ӳ���)
	@Test
	public void manyToOne() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// �������Ŷ���
		Dept dept = new Dept(1, "��ѧ��", "2¥");
		// ����
		session.save(dept);
		// �ύ����
		transaction.commit();
		session.close();
	}

	// ����Ķ��һ(���Ա�������ø�Ա������ĳ����)
	@Test
	public void manyToOneAdd() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ����Emp����
		Emp emp = new Emp(6, "���6", "����Ա6");
		// ��ò��Ŷ���
		Dept dept = (Dept) session.load(Dept.class, 1);
		// ��Ա�����ֲ���
		emp.setDept(dept);
		// ����
		session.save(emp);
		// �ύ����
		transaction.commit();
		session.close();
	}

	// ����Ķ��һ(�޸�Ա�����Ѹ�Ա������ĳ����)
	@Test
	public void manyToOneUpdate() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ���Ա������
		Emp emp = (Emp) session.get(Emp.class, 1);
		// ��ò��Ŷ���
		Dept dept = (Dept) session.get(Dept.class, 1);
		// ��Ա���޸Ĳ���
		emp.setDept(dept);
		// ����
		session.save(emp);
		// �ύ����
		transaction.commit();
		session.close();
	}

	// ˫���һ�Զ�(������ص�ͬʱ��Ӹ������µ������ֵ�)
	@Test
	public void oneToMany() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ����district����
		Dept dept = new Dept(2, "�з���2", "2¥");
		// ����2��street����
		Emp emp1 = new Emp(3, "С��1", "�з���Ա1");
		Emp emp2 = new Emp(4, "С��2", "�з���Ա2");
		// ���������ӽֵ�
		emp1.setDept(dept);
		emp2.setDept(dept);
		// ����
		dept.getEmps().add(emp1);
		dept.getEmps().add(emp2);
		// ����
		session.save(dept);
		// �ύ����
		transaction.commit();
		session.close();
	}

	@Test
	public void del() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
	
		Dept dept= (Dept) session.load(Dept.class, 2);
		session.delete(dept);
		transaction.commit();
		session.close();
	}

	// ˫���һ�Զ�(���ò��ŵ�inverse����ֵΪtrue��
	// �޸�ĳ���ţ��Ӹò���������ĳԱ����)
	@Test
	public void inverse() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ��ȡ���Ŷ���
		Dept dept=(Dept) session.load(Dept.class, 1);
		// �޸Ĳ�������
		dept.setDeptName("��ѧ��222");
		// ��ȡԱ������
		Emp emp = (Emp) session.load(Emp.class, 2);
		// �Ӳ������Ƴ�Ա��
		dept.getEmps().remove(emp);
		// ����
		session.save(dept);
		// �ύ����
		transaction.commit();
		session.close();
	}

	// �������ص�<set>Ԫ�ص�order-by����Ϊ�����սֵ���ŵ�������
	@Test
	public void orderBy() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��ȡdistrict����
		District district = (District) session.get(District.class, 3);
		// ��ȡstreet����
		List<Street> streets = district.getStreets();
		// ѭ������ѯ���Ľ��
		for (Street street : streets) {
			System.out.println(street);
		}
		session.close();
	}

	// ������ӣ������Ӳ��ŵ�ͬʱ���Ӳ����µ�Ա����
	@Test
	public void cascadeAdd() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();

	}

	// ����ɾ������ɾ�����ŵ�ͬʱɾ�������µ�Ա����
	@Test
	public void cascadeDel() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
	   Dept dept=(Dept) session.load(Dept.class, 2);
	   session.delete(dept);
	   transaction.commit();
	   session.close();
	  
	}

	// ˫�����
	@Test
	public void cascadeAll() {
		// ��ȡsession
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ���һ�����Ŷ���
		// ���һ��Ա������(��Ա��û�в���)
	}

}
