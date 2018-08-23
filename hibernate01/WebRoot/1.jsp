<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.8.3.js">
</script>
<script type="text/javascript">
  $(function(){
	  
	  $("#btn").click(function(){
		var name= $("#name").val();
		  $.ajax({
			  url:"DeptServlet?id=1",
			  dataType:"json",
			  type:"post",
			  beforeSend:function(){
				  alert("正在请求!!请稍后.......");
			  },
			  success:function(data){
					 $("#myDiv").append("<span>姓名</span>&nbsp&nbsp&nbsp&nbsp");
					 $("#myDiv").append("<span>密码</span><br>");
				//遍历传过来的list  
				$(data).each(function(i){
					 $("#myDiv").append("<span>"+data.deptNo+"</span>&nbsp&nbsp&nbsp&nbsp");
					 $("#myDiv").append("<span>"+data.deptName+"</span><br>");
				 }) 
				  alert(data);
				 if(data!=null)
				 alert(data.deptNo);
				 else
					 alert(111); 
			  }
			  
			  
			  
			  
		  })
		  
		  
		  
		  
		  
	  })
	  
	  
	  
	  
  })





</script>
  </head>
  
  <body>
    <input type="button" id="btn" value="查看">
    <input type="text" id="name"><br>
    <div id="myDiv"></div>
  </body>
</html>
