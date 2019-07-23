package hzr.web.service;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hzr.web.service.HzrServiceUtils;
import hzr.web.HzrFormData;
import my.AreYouGoodApi;
import my.HelloApi;
import my.HowOldApi;
import my.WhatTimeNowApi;

public class HzrGenericService extends HttpServlet {

	protected boolean enableErrorLog = false; // 是否打印异常输出
	protected int MAX_REQUEST_SIZE = 1024 * 512; // 允许上传的JSON最大长度

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理请求数据
		try{
			handleRequest(request, response);
		}catch(Exception e){
			e.printStackTrace();
			response.sendError(500, e.getMessage());
			return ;
		}
	}
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		//从URL中解析API的名字
		//servletPath:"/.../hello.api"
		String servletPath = request.getServletPath();
		int p1 = servletPath.lastIndexOf("/");
		int p2 = servletPath.lastIndexOf(".");
		String apiName = servletPath.substring(p1+1, p2);
		System.out.println("服务名:"+apiName);
		
		//根据服务名，创建不同的实例进行处理（单例 -> 多例）
		HzrGenericApi instance = null;
		if("Hello".equals(apiName)){
			instance = new HelloApi();
		}
		else if(apiName.equals("HowOld")){
			instance = new HowOldApi();
		}
		else if(apiName.equals("AreYouGood")){
			instance = new AreYouGoodApi();
		}
		else if(apiName.equals("WhatTimeNow")){
			instance = new WhatTimeNowApi();
		}
		else{
			throw new Exception("不支持这个服务：" + apiName);
		}
		
		//读取请求数据和URL里的参数
		String charset = "UTF-8";
		String strReq = HzrServiceUtils.readAsText(request.getInputStream(), charset, MAX_REQUEST_SIZE);
		
		//更改：不再提取URL里的参数，一般用不到，如果需要则有子类自己用HzrFormData提取
		String query = request.getQueryString();
		HzrFormData QueryParams = HzrFormData.parse(query, charset);
		
		//读取请求数据，转成字符串，转成JSON
		instance.httpReq = request;
		instance.httpResp = response;
		instance.charset = charset;
		String strResp = instance.execute(strReq);//具体的请求处理在execute()里
		
		
		//发送应答给客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write(strResp);
		writer.close();
	}

}
