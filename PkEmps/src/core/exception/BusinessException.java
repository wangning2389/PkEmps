package core.exception;

/**
 * 业务异常
 * 
 * @author Yuanjinqiao 2015年7月10日 上午9:49:30
 *
 * @Since
 */
public class BusinessException extends RuntimeException
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
	public static final String EX_TYPE_FILTER_LOGON = "FILTER_LOGON";
	public static final String LOGON_EXC= "LOGON_EXC";
	private String type = "TYPE";// 错误类型
    public BusinessException(String msg)
    {
        super(msg);
    }
    
    public BusinessException(String msg, Throwable e)
    {
        super(msg, e);
    }
    
    public BusinessException(Throwable cause)
	{
		super(cause == null ? null : cause.toString(), cause);
	}
    public BusinessException(String type,String msg) {
		super(msg);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
