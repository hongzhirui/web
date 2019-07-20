package my;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


/* 一个演示用的数据库
 * 
 */
public class DemoDb{
	// 全局对象
	public static DemoDb i = new DemoDb();
	

	// 内部数据
	private List<Student> data = new ArrayList<Student>();
	
	private DemoDb(){
	}
	
	// 查看是否存在
	public boolean exist(int id){
		for(Student s: data){
			if(s.id == id)
				return true;
		}
		return false;
	}
	
	// 添加
	public void add(Student s){
		data.add( s );
	}
	
	// 获取全部
	public List<Student> list(){
		return data;
	}
	
	// 按学号查询
	public List<Student> list(int from , int to){
		List<Student> result = new ArrayList<Student>();
		for(Student s: data){
			if(s.id >= from && s.id <= to){
				result.add( s );
			}
		}
		return result;
	}
	
	// 按名字查询
	public List<Student> list(String name){
		List<Student> result = new ArrayList<Student>();
		for(Student s: data){
			if(s.name.indexOf( name ) >=0 ){
				result.add( s );
			}
		}
		return result;
	}
	
	// 按学号删除
	public boolean remove(int id){
		Iterator<Student> iter = data.iterator();
		while(iter.hasNext()){
			Student s = iter.next();
			if(s.getId() == id){
				iter.remove();
				return true;
			}
		}
		return false;
	}
}
