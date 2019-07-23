package hzr.web;

/**
 * 通用的异常类,带 error和reason两个属性
 */
public class HzrWebException extends Exception
{
	public int error;
	public String reason;
	
	public HzrWebException()
	{		
	}
	public HzrWebException(int error, String reason)
	{
		this.error = error;
		this.reason = reason;
	}
	@Override
	public String getMessage()
	{
		return reason + "(" + error + ")";
	}
	
	
}
