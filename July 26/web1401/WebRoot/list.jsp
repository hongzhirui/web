<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="jsp.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
   	<script type="text/javascript" src="js/jquery.min.js" ></script>
	<script type="text/javascript" src="js/afquery.js" ></script>

  </head>
  
  <body>
  	<div class='content'>
  	</div>
  </body>
  <script>
  	var data = <%=new JspSupport(request).getList() %>;
  	var target = $('.content');
  	for(var row of data){
  		var str = "学号：" + row.id + "，姓名：" + row.name + "<br>";
  		target.append(str);
  	}
  </script>
</html>
