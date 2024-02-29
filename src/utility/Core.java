package utility;

import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Strings;

import java.util.*;


/**
 * @Summary: This class contains static methods for general use.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class Core
{
    
    /**
     * @param stringArray The string array to parse.
     * @return The parsed float array.
     * @throws NumberFormatException if a string cannot be parsed into a float.
     * @Summary: Parses a string array into a float array.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static ArrayList<Float> parseStringArrayIntoFloatArrayList(String[] stringArray)
    {
        ArrayList<Float> floats = new ArrayList<>();
        for (String string : stringArray)
        {
            floats.add(Float.parseFloat(string));
        }
        
        
        return floats;
    }
    
    
    /**
     * @param element The element to fill the ArrayList with.
     * @param size    the size of the returned ArrayList.
     * @return An ArrayList filled with element.
     * @Summary: Creates an ArrayList filled with element.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static <T> ArrayList<T> createFilledArrayListOfSize(int size, T element)
    {
        ArrayList<T> zeroFilledArrayList = new ArrayList<>();
        
        for (int i = 0; i < size; i++)
        {
            zeroFilledArrayList.add(element);
        }
        
        
        return zeroFilledArrayList;
    }
    
    
    /**
     * @param collection    The {@link Collection} to check.
     * @param specificValue the specific value to check for.
     * @return Whether the collection is filled with only specificValue.
     * @Summary: Checks whether all elements in the collection are equal to specificValue.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static <T> boolean areAllValuesInCollectionEqualToSpecificValue(@NotNull Collection<T> collection, @NotNull T specificValue)
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
    
    
    /**
     * @return string.trim().toUpperCase().
     * @Summary: Trims and capitalizes a string.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String capitalizeAndTrimString(String string)
    {
        return string.trim().toUpperCase();
    }
    
    
    /**
     * @param string the string to parse.
     * @return The string parsed into an int or {@link Constants#UNBEKANNT} if a {@link NumberFormatException} occurs.
     * @Summary: Safely parses a string into an int and returns placeholder value if a {@link NumberFormatException} occurs.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static int safeParseInt(String string)
    {
        return Math.round(safeParseFloat(string));
    }
    
    
    /**
     * @param string the string to parse.
     * @return The string parsed into a float or {@link Constants#UNBEKANNT} if a {@link NumberFormatException} occurs.
     * @Summary: Safely parses a string into a float and returns placeholder value if a {@link NumberFormatException} occurs.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
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
    
    
    /**
     * @param hashMap the hashmap to find the key of greates value in
     * @return The key with the maximum value.
     * @Summary: removes the {@link Strings#UNBEKANNT} from the hashmap and returns the key with the highest value
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String returnMaxByValueAfterRemovingUnknown(@NotNull HashMap<String, Float> hashMap)
    {
        hashMap.remove(Strings.UNBEKANNT);
        
        
        return Collections.max(hashMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    
}