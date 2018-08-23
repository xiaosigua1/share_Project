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
	
	
	
	
	
	
	
	
	
	
	
	
	// 新增用户
	@Test
	public void newUser() {
		// 读取配置文件
		Configuration configuration = new Configuration().configure();
		// 创建sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		// 创建session
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 进行操作
		Users user = new Users(3, "小红人", "蓝啊");
		// 持久化操作
		session.save(user);
		// 提交事务
		transaction.commit();
		// 关闭Session
		session.close();
	}

	// 根据主键获得指定的用户
	@Test
	public void getUser() {
		Configuration configuration = new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		// get()立即加载,能输出users的信息
		Object object = session.get(Users.class, 9);
		System.out.println(object);
		session.close();
		/*
		 * load()默认是延迟加载,当输入users信息的时候,session已经关闭了 想实现的话,就得在映射文件中加上
		 * lazy="false"
		 */
	}

	// 修改指定的用户信息
	@Test
	public void updateUser() {
		Configuration configuration = new Configuration().configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 获取到要修改的用户 1
		Users users1 = new Users(); // 瞬时状态
		users1.setId(4);
		Users users2 = (Users) session.get(Users.class, 1);
		Users users3 = (Users) session.load(Users.class, 1);
		// 输出一个分解线看看延迟加载和即时加载的区别
		System.out.println("*************************");
		// 输出用户的信息
		System.out.println(users2);
		// 修改用户的信息
		users2.setName("大大黑黑");
		// session.save(users);
		session.saveOrUpdate(users2);
		// 提交事务
		// session.flush();
		transaction.commit();
		// 显示修改后的用户信息
		System.out.println(users2);
		session.close();
	}

	// 删除指定的用户信息
	@Test
	public void delUser() {
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction beginTransaction = session.beginTransaction();
		// 获取到要删除的用户
		Users users = (Users) session.get(Users.class, 1);
		// 删除用户
		session.delete(users);
		// 提交事务
		// beginTransaction.commit();
		session.close();
	}

	// 查询所有人的信息(list)
	@Test
	public void showList() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 书写查询所有人员的HQL语句
		String hql = "from Users";
		// 创建Query对象
		Query createQuery = session.createQuery(hql);
		// 执行查询
		List<Users> list = createQuery.list();
		// 输出查询的信息
		for (Users object : list) {
			System.out.println(object);
		}
		session.close();
	}

	// 查询所有人的信息(iterator) 没有缓存的情况
	@Test
	public void showIterator1() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 书写查询所有人员的HQL语句
		String hql = "from Users";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 执行查询
		Iterator iterate = query.iterate();
		// while循环输出查询的信息
		while (iterate.hasNext()) {
			Object object = (Object) iterate.next();
			System.out.println(object);
		}
		session.close();
	}

	// 查询所有人的信息(iterator) 有缓存的情况
	@Test
	public void showIterator2() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 书写查询所有人员的HQL语句
		String hql = "from Users";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 先使用list()执行查询
		List list = query.list();
		// 再使用iterator查询
		Iterator iterator = query.iterate();
		// while循环输出查询的信息
		while (iterator.hasNext()) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
		session.close();
	}

	// 参数绑定:参数位置绑定
	@Test
	public void first() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 编写查询姓名是"小黑黑"的HQL语句
		String hql = "from Users where name like ?";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 01.通过setString()为占位符赋值
		// query.setString(0, "小红人");
		// 02.通过setParameter()为占位符赋值
		query.setParameter(0, "%小%");
		// 执行查询
		List<Users> list = query.list();
		// 输出查询结果
		for (Users users2 : list) {

			System.out.println(users2);
		}
		session.close();
	}

	// 参数绑定:参数名称绑定
	@Test
	public void second() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 编写查询姓名是"大大黑黑"的HQL语句
		String hql = "from Users where name =:name";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 01.通过setString()为命名参数赋值
		query.setString("name", "大大黑黑");
		// 02.通过setParameter()为命名参数赋值
		// 执行查询
		Users users = (Users) query.uniqueResult();
		// 输出查询结果
		System.out.println(users);
		session.close();
	}

	// 动态参数绑定
	@Test
	public void third() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 准备查询条件,Users对象封装条件
		Users users = new Users();
		// id为1
		users.setId(1);
		// name为"小黑黑"
		users.setName("小黑黑");
		// 密码为"白啊"
		users.setPassword("白啊");
		// 准备hql，hql根据条件动态生成
		StringBuilder hql = new StringBuilder("from Users as users where 1=1");
		// 拼接参数
		hql.append(" and users.id=:id");
		hql.append(" and users.name=:name");
		hql.append(" and users.password=:password");
		// 创建Query对象
		Query query = session.createQuery(hql.toString());
		// 通过setProperties()为参数赋值
		query.setProperties(users);
		// 执行查询
		Users user = (Users) query.uniqueResult();
		// 输出查询结果
		System.out.println(user);
		session.close();
	}

	// 模糊查询
	@Test
	public void showLike() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 书写查询所有姓名中有"小"的的HQL语句
		String hql = "from Users where name like :name";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 为命名参数赋值
		query.setParameter("name", "%小%");
		// 执行查询
		List list = query.list();
		// 输出查询的信息
		for (Object object : list) {
			System.out.println(object);
		}
		session.close();
	}

	// 投影查询,将每条查询结果封装成object对象
	@Test
	public void test01() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 查询所有姓名的HQL语句
		String hql = "select eName,job from Emp";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 执行查询
		List<Object[]> list = query.list();
		// 输出查询的信息
		for (Object[] object : list) {
			System.out.println(object[0]);
			System.out.println(object[1]);
		}
	}

	// 投影查询,将每条查询结果封装成object数组
	@Test
	public void test02() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 查询所有姓名和密码的HQL语句
		String hql = "select name,password from Users";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 执行查询
		List<Object[]> list = query.list();
		// 输出查询的信息
		for (Object[] object : list) {
			System.out.println("姓名:" + object[0]);
			System.out.println("密码:" + object[1]);
			// 把数组转换成一个字符串
			System.out.println(Arrays.toString(object));
		}
		session.close();
	}

	// 投影查询,将每条查询结果通过构造函数封装成对象
	@Test
	public void test03() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 查询姓名和年龄(将每条查询结果通过构造函数封装成),要保证在类中有对应的构造函数
		String hql = "select new Emp(eName,job) from Emp";
		// 创建Query对象
		List list = session.createQuery(hql).list();
		// 输出查询的信息
	}

	// 分页查询,查询人员信息,按照ID排序,每页显示2条数据,输出第1页的人员数据
	@Test
	public void page() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 查询人员信息(排序)
		String hql = "from Users order by id desc";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 每页显示2条数据
		int pageSize = 2;
		// 查询总记录数的HQL
		String hqlCount = "select count(*) from Users";
		// 执行查询总记录数
		int pageCounts = ((Long) session.createQuery(hqlCount).uniqueResult())
				.intValue();
		// 计算总页数(总记录数%页大小==0)?(总记录数/页大小):(总记录数/页大小+1)
		int totalPage = (pageCounts % pageSize == 0) ? (pageCounts / pageSize)
				: (pageCounts / pageSize + 1);
		// 设置显示第几页的数据
		int pageIndex = 2;
		// 设置从第几条开始输出,不包括该条(当前页码-1)*页大小
		query.setFirstResult((pageIndex - 1) * pageSize);
		// 设置每页显示的最大记录数
		query.setMaxResults(pageSize);
		// 执行查询
		List<Users> list = query.list();
		// 输出查询的信息(共多少页,当前第几页)
		for (Users users : list) {
			System.out.println(users);
		}

	}

	// 单向的多对一(添加部门)
	@Test
	public void manyToOne() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 创建部门对象
		Dept dept = new Dept(1, "教学部", "2楼");
		// 保存
		session.save(dept);
		// 提交事务
		transaction.commit();
		session.close();
	}

	// 单向的多对一(添加员工，设置该员工属于某部门)
	@Test
	public void manyToOneAdd() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 创建Emp对象
		Emp emp = new Emp(6, "大黑6", "程序员6");
		// 获得部门对象
		Dept dept = (Dept) session.load(Dept.class, 1);
		// 给员工划分部门
		emp.setDept(dept);
		// 保存
		session.save(emp);
		// 提交事务
		transaction.commit();
		session.close();
	}

	// 单向的多对一(修改员工，把该员工调到某部门)
	@Test
	public void manyToOneUpdate() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 获得员工对象
		Emp emp = (Emp) session.get(Emp.class, 1);
		// 获得部门对象
		Dept dept = (Dept) session.get(Dept.class, 1);
		// 给员工修改部门
		emp.setDept(dept);
		// 保存
		session.save(emp);
		// 提交事务
		transaction.commit();
		session.close();
	}

	// 双向的一对多(添加区县的同时添加该区县下的两个街道)
	@Test
	public void oneToMany() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 创建district对象
		Dept dept = new Dept(2, "研发部2", "2楼");
		// 创建2个street对象
		Emp emp1 = new Emp(3, "小黄1", "研发人员1");
		Emp emp2 = new Emp(4, "小黄2", "研发人员2");
		// 给区县增加街道
		emp1.setDept(dept);
		emp2.setDept(dept);
		// 保存
		dept.getEmps().add(emp1);
		dept.getEmps().add(emp2);
		// 保存
		session.save(dept);
		// 提交事务
		transaction.commit();
		session.close();
	}

	@Test
	public void del() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
	
		Dept dept= (Dept) session.load(Dept.class, 2);
		session.delete(dept);
		transaction.commit();
		session.close();
	}

	// 双向的一对多(设置部门的inverse属性值为true，
	// 修改某部门，从该部门中移走某员工。)
	@Test
	public void inverse() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 获取部门对象
		Dept dept=(Dept) session.load(Dept.class, 1);
		// 修改部门名称
		dept.setDeptName("教学部222");
		// 获取员工对象
		Emp emp = (Emp) session.load(Emp.class, 2);
		// 从部门中移出员工
		dept.getEmps().remove(emp);
		// 保存
		session.save(dept);
		// 提交事务
		transaction.commit();
		session.close();
	}

	// 设置区县的<set>元素的order-by属性为：按照街道编号倒叙排序
	@Test
	public void orderBy() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 获取district对象
		District district = (District) session.get(District.class, 3);
		// 获取street对象
		List<Street> streets = district.getStreets();
		// 循环出查询到的结果
		for (Street street : streets) {
			System.out.println(street);
		}
		session.close();
	}

	// 级联添加（在增加部门的同时增加部门下的员工）
	@Test
	public void cascadeAdd() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();

	}

	// 级联删除（在删除部门的同时删除部门下的员工）
	@Test
	public void cascadeDel() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
	   Dept dept=(Dept) session.load(Dept.class, 2);
	   session.delete(dept);
	   transaction.commit();
	   session.close();
	  
	}

	// 双向关联
	@Test
	public void cascadeAll() {
		// 获取session
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 获得一个部门对象
		// 获得一个员工对象(此员工没有部门)
	}

}
