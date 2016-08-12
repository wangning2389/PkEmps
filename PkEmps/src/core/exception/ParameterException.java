package core.exception;

/**
 * 参数异常类
 * 
 * @author Yuanjinqiao 2015年7月10日 上午9:49:16
 *
 * @Since
 */
public class ParameterException extends RuntimeException
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ParameterException(String message)
    {
        super(message);
    }
    
}
