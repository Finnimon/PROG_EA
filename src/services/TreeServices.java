package services;

import model.Tree;
import resources.Constants;
import resources.Strings;
import utility.Core;


/**
 * @Summary: A collection of methods for interacting with {@link Tree}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class TreeServices
{
    
    /**
     * @param attribute the int to parse into a String suitable for Console output.
     * @return the String representation of the int.
     * @Summary: Parses an int into a String suitable for Console output.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(int attribute)
    {
        if (attribute != Constants.UNBEKANNT)
        {
            return Integer.toString(attribute);
        }
        
        
        return Strings.UNBEKANNT;
    }
    
    
    /**
     * @param attribute the float to parse into a String suitable for Console output.
     * @return the String representation of the float.
     * @Summary: Parses a float into a String suitable for Console output.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(float attribute)
    {
        if (attribute != Constants.UNBEKANNT)
        {
            return Float.toString(attribute);
        }
        
        
        return Strings.UNBEKANNT;
    }
    
    
    /**
     * @param attribute the String to modify.
     * @return the String formatted for Console output and uniformity.
     * @Summary: Modifies a String for Console output and unifromizes it.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(String attribute)
    {
        attribute = Core.capitalizeAndTrimString(attribute);
        
        if (attribute.equals(Strings.UNBEKANNT) || attribute.isBlank())
        {
            return Strings.UNBEKANNT;
        }
        return attribute;
        
    }
    
    
}
