package model;

public class ElementFaultyException extends Exception
{
    public ElementFaultyException()
    {
    }
    
    
    public ElementFaultyException(String s)
    {
        super(s);
    }
    
    
    public ElementFaultyException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    
    public ElementFaultyException(Throwable cause)
    {
        super(cause);
    }
}
