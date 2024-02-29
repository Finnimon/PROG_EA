package Utility;

import Resources.Constants;

import java.util.*;

public class Core
{
    
    
    public static ArrayList<Float> parseStringArrayIntoFloatArrayList(String[] stringArray)
    {
        ArrayList<Float> floats=new ArrayList<>();
        for (String string:stringArray
             )
        {
            floats.add(Float.parseFloat(string));
        }
        
        
        return floats;
    }
    
    
    public static ArrayList<Float> createZeroFilledFloatArrayList(int size)
    {
        ArrayList<Float> zeroFilledArrayList = new ArrayList<>();
        
        for (int i = 0; i < size; i++)
        {
            zeroFilledArrayList.add(0f);
        }
        
        
        return zeroFilledArrayList;
    }
    
    
    public static ArrayList<Integer> createZeroFilledIntegerArrayList(int size)
    {
        ArrayList<Integer> zeroFilledArrayList = new ArrayList<>();
        
        for (int i = 0; i < size; i++)
        {
            zeroFilledArrayList.add(0);
        }
        
        
        return zeroFilledArrayList;
    }
    
    
    public static <T> boolean areAllValuesInCollectionEqualToSpecificValue(Collection<T> collection, T specificValue)
    {
        for (T value : collection)
        {
            if (!value.equals(specificValue))
            {
                return false;
            }
        }
        
        
        return true;
    }
    
    
    public static String capitalizeAndTrimString(String string)
    {
        string=string.trim();
        return string.toUpperCase();
    }
    
    
    public static int safeParseInt(String string)
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
    
    
    public static float safeParseFloat(String string)
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
