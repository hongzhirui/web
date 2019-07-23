package my;

import java.text.SimpleDateFormat;

import hzr.web.service.HzrGenericApi;

public class WhatTimeNowApi extends HzrGenericApi
{

	@Override
	public String execute(String strReq) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timenow = sdf.format(System.currentTimeMillis());
		return timenow;
	}

}
