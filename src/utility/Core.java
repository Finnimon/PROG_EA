package utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Core
{
    public static int keyDesGroesztenWertInHashMapFinden(HashMap hashMap, Comparator comparator)
    {
        Collections.max(hashMap.entrySet(),comparator).getKey();
    }
}
