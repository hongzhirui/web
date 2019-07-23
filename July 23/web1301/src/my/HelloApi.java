package my;

import hzr.web.service.HzrGenericApi;

public class HelloApi extends HzrGenericApi
{

	@Override
	public String execute(String strReq) throws Exception
	{
		return "你好, 我是HZR!";
	}

}
