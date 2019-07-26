package my;

import java.util.ArrayList;
import java.util.List;

/* 一个演示用的数据库
 * 
 */
public class DemoDb
{
	// 全局对象
	public static DemoDb i = new DemoDb();
	
	// 内部数据
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
	// 添加
	public void add(Student s)
	{
		data.add( s );
	}
	// 获取全部
	public List<Student> list()
	{
		return data;
	}
	
	// 按学号查询
	public List<Student> list(int from , int to)
	{
		List<Student> result = new ArrayList<Student>();
		for(Student s: data)
		{
			if(s.id >= from && s.id <= to)
			{
				result.add( s );
			}
		}
		return result;
	}
	
	// 按名字查询
	public List<Student> list(String name)
	{
		List<Student> result = new ArrayList<Student>();
		for(Student s: data)
		{
			if(s.name.indexOf( name ) >=0 ) 
			{
				result.add( s );
			}
		}
		return result;
	}
	
}
