package utility;

import java.util.*;

public class Core
{
    
    
    public static String keyStringDesGroesztenWertIntegerInHashMapFinden(HashMap<String,Integer> hashMap)
    {
        return Collections.max(hashMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
    
    
    public static ArrayList<Float> createZeroFilledFloatArrayList(int size)
    {
        ArrayList<Float> zeroFilledArrayList=new ArrayList<>();

        for (int i = 0; i < size; i++)
        {
            zeroFilledArrayList.add(0f);
        }
        
        
        return zeroFilledArrayList;
    }
    
    
    public static ArrayList<Integer> createZeroFilledIntegerArrayList(int size)
    {
        ArrayList<Integer> zeroFilledArrayList=new ArrayList<>();
        
        for (int i = 0; i < size; i++)
        {
            zeroFilledArrayList.add(0);
        }
        
        
        return zeroFilledArrayList;
    }
    
    
}
