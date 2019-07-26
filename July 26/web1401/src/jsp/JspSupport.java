package jsp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import my.DemoDb;
import my.Student;

public class JspSupport {
	HttpServletRequest request;
	public JspSupport(HttpServletRequest request){
		this.request = request;
	}
	
	public JSONArray getList(){
		List<Student> rows = DemoDb.i.list();
		return new JSONArray(rows);
	}
}
