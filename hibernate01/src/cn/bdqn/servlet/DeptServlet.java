package cn.bdqn.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import cn.bdqn.bean.Dept;
import cn.bdqn.bean.Emp;
import cn.bdqn.service.DeptService;
import cn.bdqn.service.impl.DeptServiceImpl;

public class DeptServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取前台传来的参数
		String id = request.getParameter("id");
		int deptNo = Integer.parseInt(id);
		DeptService service = new DeptServiceImpl();
		Dept dept = service.getDept(deptNo);
		/*List<Emp> emps = dept.getEmps();
		for (Emp emp : emps) {
			System.out.println(emp.getEmpName());
		}*/
			Gson gson=new Gson();
		String json = gson.toJson(dept);
		System.out.println(json);
		response.getWriter().print(json);
		/*request.setAttribute("list", emps);
		request.getRequestDispatcher("index.jsp").forward(request, response);*/
		
	
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		
		
	}


