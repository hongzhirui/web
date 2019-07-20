package lesson1;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import my.DemoDb;
import my.Student;

@WebServlet("/Example1")
public class Example1 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	    Student stu = new Student();
		//提取请求
		stu.id = Integer.valueOf(request.getParameter("id"));
		stu.name = request.getParameter("name");
		stu.phone = request.getParameter("phone");
		
		//处理数据
		boolean ok = handleRequest(stu);
		
		//返回结果
		JSONObject jresp = new JSONObject();
		if(ok){
			jresp.put("error", 0);
			jresp.put("reason", "OK");
		}
		else{
			jresp.put("error", 1);
			jresp.put("reason", "学号重复");
		}
		
		//发送应答给客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write( jresp.toString(2) );
		writer.close();
	}

	private boolean handleRequest(Student stu){
		if(DemoDb.i.exist(stu.id)){
			return false;
		}
		else{
			DemoDb.i.add(stu);
			return true;
		}
	}
}
