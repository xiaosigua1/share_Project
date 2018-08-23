<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="a"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>

<%-- XDF    second --%>
   <%--  <a href="DeptServlet?id=1">aaa</a> --%>
 <!-- dsdss -->
 <%
 //hello ！！
 %>
  <table>
     <tr>
      <td>用户编号</td>
      <td>用户名</td>
      <td>职位</td>
     </tr>
    <a:forEach items="${list}" var="emp">
     <tr>
      <td>${emp.empNo}</td>
      <td>${emp.empName}</td>
      <td>${emp.job}</td>
     </tr>
    </a:forEach>
  
  </table>

</body>
</html>
