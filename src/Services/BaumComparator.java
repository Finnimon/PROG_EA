package Services;

import model.Baum;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class BaumComparator implements Comparator<Baum>
{
    //todo    private final Field vergleichendesField;
    
    //region [Konstante]
    
    
    private static final Integer[] erlaubteAttributIndize = {1, 2, 3, 4};
    
    
    //endregion
    //region[Attribut]
    
    
    private final int attributIndex;
    
    
    //endregion
    //region[Konstruktor]
    
    
    public BaumComparator(int attributIndex) throws IllegalArgumentException
    {
        this.attributIndex = attributIndex;
        
        if (Arrays.binarySearch(erlaubteAttributIndize, getAttributIndex()) < 0)
        {
            throw new IllegalArgumentException();
        }
    }
    
    
    //endregion
    //region [Get]
    
    
    private int getAttributIndex()
    {
        return attributIndex;
    }
    
    
    //endregion
    //region [Override]
    
    
    @Override
    public int compare(Baum baum, Baum otherBaum)
    {
        if (null == baum && null == otherBaum)
        {
            return 0;
        }
        else if (null == baum)
        {
            return -1;
        }
        else if (null == otherBaum)
        {
            return 1;
        }
        
        
        if (getAttributIndex() == KatasterServices.INDEX_BEZIRK_MIT_GROESZTEM_BAUM)
        {
            return hoeheMeterVergleichen(baum, otherBaum);
        }
        else if (getAttributIndex() == KatasterServices.INDEX_UMFANG_ZENTIMETER_VERGLEICHEN)
        {
            return umfangZentimeterVergleichen(baum, otherBaum);
        }
        else if (getAttributIndex() == KatasterServices.INDEX_KRONE_METER_VERGLEICHEN)
        {
            return kroneMeterVergleichen(baum, otherBaum);
        }
        else
        {
            return alterVergleichen(baum, otherBaum);
        }
        
    }
    
    
    //endregion
    //region[Methoden]
    
    
    public static int alterVergleichen(@NotNull Baum baum, Baum otherBaum)
    {
        return Integer.compare(BaumServices.bekanntesStandalterErmitteln(baum), BaumServices.bekanntesStandalterErmitteln(otherBaum));
    }
    
    
    public static int hoeheMeterVergleichen(@NotNull Baum baum, @NotNull Baum otherBaum)
    {
        return Float.compare(baum.getMetrik().getHoeheMeter(), otherBaum.getMetrik().getHoeheMeter());
    }
    
    
    public static int kroneMeterVergleichen(@NotNull Baum baum, @NotNull Baum otherBaum)
    {
        return Float.compare(baum.getMetrik().getKroneMeter(), otherBaum.getMetrik().getKroneMeter());
    }
    
    
    public static int umfangZentimeterVergleichen(@NotNull Baum baum, @NotNull Baum otherBaum)
    {
        return Float.compare(baum.getMetrik().getUmfangZentimeter(), otherBaum.getMetrik().getUmfangZentimeter());
    }
 
    
    //endregion
    
    
}