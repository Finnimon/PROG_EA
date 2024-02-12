package utility;

import resources.Konstanten;

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
            parsedInt = Konstanten.UNBEKANNT;
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
            parsedFloat = Konstanten.UNBEKANNT;
        }
        
        
        return parsedFloat;
    }
}
