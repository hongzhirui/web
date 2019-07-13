package my;

import java.util.ArrayList;
import java.util.List;

/* 一个演示用的数据库
 * 
 */
public class DemoDb {

	public static DemoDb i = new DemoDb();
	
	private List<Student> data = new ArrayList<Student>();
	
	private DemoDb()
	{
		// 准备一些测试数据
		data.add( new Student(20180001, "盖聂", true, "14093423232"));
		data.add( new Student(20180002, "卫庄", true, "12129000890"));
		data.add( new Student(20180003, "张良", true, "12242092393"));
		data.add( new Student(20180004, "伏念", true, "14283920202"));
		data.add( new Student(20180005, "高渐离", true, "18829292922"));
		data.add( new Student(20180006, "赤练", false, "19348343039"));
		data.add( new Student(20180007, "端木蓉", false, "12349340300"));
		data.add( new Student(20180008, "天明", true, "12230029990"));
		data.add( new Student(20180009, "月儿", false, "13049030330"));
	}
	
	public List<Student> list(){
		return data;
	}
	
	public List<Student> list(int id){
		List<Student> result = new ArrayList<Student>();
		for(Student s : data){
			if(id == s.id)
				result.add(s);
		}
		return result;
	}
	
	public List<Student> list(String name){
		List<Student> result = new ArrayList<Student>();
		for(Student s : data){
			if(name == s.name)
				result.add(s);
		}
		return result;
	}
}
