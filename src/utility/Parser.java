package utility;

import resources.Constants;

public class Parser
{
    public static int parseInt(String string)
    {
        int parsedInt;
        try
        {
            parsedInt = Integer.parseInt(string);
        }
        catch (NumberFormatException e)
        {
            parsedInt = Constants.UNBEKANNT;
        }
        
        
        return parsedInt;
    }
    
    
    public static float parseFloat(String string)
    {
        
        float parsedFloat;
        try
        {
            parsedFloat = Float.parseFloat(string);
        }
        catch (NumberFormatException e)
        {
            parsedFloat = Constants.UNBEKANNT;
        }
        
        
        return parsedFloat;
    }
}
