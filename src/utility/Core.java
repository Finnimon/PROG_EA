package utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Core
{
    public static String keyStringDesGroesztenWertIntegerInHashMapFinden(HashMap<String,Integer> hashMap)
    {
        return Collections.max(hashMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}
