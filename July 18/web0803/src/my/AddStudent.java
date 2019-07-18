package my;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class AddStudent
 */
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//读取请求数据，转成字符串
		String reqText = readAsText(request.getInputStream(), "UTF-8");
		
		JSONObject jreq = new JSONObject(reqText);
		
		//URL末尾，由问号和引号的字符串（在RESTful中也可能有部分参数在URL里）
		//例如：mode=admin&token=132445
		String query = request.getQueryString();
		HashMap<String, String> queryParams = parseQuery(query);
		
		int id = jreq.getInt("id");
		String name = jreq.getString("name");
		String phone = jreq.getString("phone");
		boolean sex = "male".equals(jreq.getString("sex")); 
		
		Student s = new Student(id, name, sex, phone);
		DemoDb.i.add(s);
		
		JSONObject jresp = new JSONObject();
		jresp.put("error", 0); // 错误码,0表示成功
		jresp.put("reason", "OK"); // 错误原因描述, 如果没有错误则提示OK
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write( jresp.toString(2));
		writer.close();
	}
	
	//从Stream中读取数据直到读完
	public String readAsText(InputStream streamIn, String charset) throws IOException{
		ByteArrayOutputStream cache = new ByteArrayOutputStream(1024*16);
		byte[] data = new byte[1024];
		while(true){
			int n = streamIn.read(data);//n:实际读取的字节数
			if(n<0) break;//连接断开
			if(n==0)continue;//数据未完  
			
			//缓存起来
			cache.write(data, 0, n);
			if(cache.size() > 1024*512)//上限
				break;
		}
		return cache.toString(charset);
	}

	public HashMap<String,String> parseQuery(String query){
		HashMap<String,String> params = new HashMap<String, String>();
		String[] ppp = query.split("&");
		for(String p : ppp){
			String[] kv = p.split("=");
			String key = kv[0];
			String value = "";
			if(key.length() > 1)
				value = kv[1];
			params.put(key, value);
		}
		return params;
	}
}
