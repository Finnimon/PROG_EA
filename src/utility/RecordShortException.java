package utility;

public class RecordShortException extends Exception
{
    public RecordShortException()
    {
    }
    
    
    public RecordShortException(String message)
    {
        super(message);
    }
    
    
    public RecordShortException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    
    public RecordShortException(Throwable cause)
    {
        super(cause);
    }
    
    
    public RecordShortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
