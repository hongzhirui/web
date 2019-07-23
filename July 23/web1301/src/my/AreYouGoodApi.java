package my;

import hzr.web.service.HzrGenericApi;

public class AreYouGoodApi extends HzrGenericApi
{
	@Override
	public String execute(String strReq) throws Exception
	{
		return "非常好,谢谢";
	}

}
