package cn.bdqn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.bdqn.util.HibernateSessionFactory;

public class OpenSessionInView implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Session session=null;
		Transaction transaction=null;
              try {
            		session = HibernateSessionFactory.getSession();
            		transaction = session.beginTransaction();
            		System.out.println(session.hashCode());
            		chain.doFilter(request, response);
            		transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}finally{
				session.close();
				
			}
              
	
		
		
		 
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
