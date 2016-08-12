package core.exception;

/**
 * JSON异常类
 * @author {李新}
 *
 */
public class JSONException extends RuntimeException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JSONException(String message)
	{
		super(message, null);
	}
	
	public JSONException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public JSONException(Throwable cause)
	{
		super(cause == null ? null : cause.toString(), cause);
	}

}
