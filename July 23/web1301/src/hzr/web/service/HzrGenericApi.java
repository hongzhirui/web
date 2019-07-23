package hzr.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HzrGenericApi
{
	// httpReq : 请求对象
	protected HttpServletRequest httpReq;
	// httpResp : 应答对象
	protected HttpServletResponse httpResp;
	
	// queryParams : URL 末属附加的参数
	// protected AfFormData queryParams; // deprecated: 由子类自己提取
	
	// charset： 字符编码
	protected String charset ; 
	
	// 子类应重写这个方法 , strReq 是请求数据 (可能为null), 应返回一段数据, 可以为null
	public abstract String execute(String strReq) throws Exception;
	
}