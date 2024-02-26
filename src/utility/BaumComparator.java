package utility;

import Services.BaumServices;
import Services.KatasterServices;
import model.Tree;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Comparator;

public class BaumComparator implements Comparator<Tree>
{
    //todo    private final Field vergleichendesField;
    
    //region [Konstante]
    
    
    private static final Integer[] erlaubteAttributIndize = {1, 2, 3, 4};
    
    
    public static final int INDEX_HOEHE_METER=KatasterServices.INDEX_BEZIRK_MIT_GROESZTEM_BAUM;
    
    
    public static final int INDEX_KRONE_METER=KatasterServices.INDEX_KRONE_METER_VERGLEICHEN;
    
    
    public static final int INDEX_UMFANG_ZENTIMETER=KatasterServices.INDEX_UMFANG_ZENTIMETER_VERGLEICHEN;
    
    
    public static final int INDEX_ALTER=KatasterServices.INDEX_ALTER_VERGLEICHEN;
    
    
    //endregion
    //region[Attribut]
    
    
    private final int attributIndex;
    
    
    //endregion
    //region[Konstruktor]
    
    
    public BaumComparator(int attributIndex) throws IllegalArgumentException
    {
        this.attributIndex = attributIndex;
        
        if (Arrays.binarySearch(getErlaubteAttributIndize(), getAttributIndex()) < 0)
        {
            throw new IllegalArgumentException();
        }
    }
    
    
    //endregion
    //region [Get]
    
    
    public int getAttributIndex()
    {
        return attributIndex;
    }
    
    
    public static Integer[] getErlaubteAttributIndize()
    {
        return erlaubteAttributIndize;
    }
    
    
    //endregion
    //region [Override]
    
    
    @Override
    public int compare(Tree tree, Tree otherTree)
    {
        if (null == tree && null == otherTree)
        {
            return 0;
        }
        else if (null == tree)
        {
            return -1;
        }
        else if (null == otherTree)
        {
            return 1;
        }
        float[] floats={tree.getMetrik().getAtrributNachBaumComparatorIndex(getAttributIndex()), otherTree.getMetrik().getAtrributNachBaumComparatorIndex(getAttributIndex())};
        
        return wennBaumGroeszerIstVerhaeltnisZurueckgebenAnsonstenFloatVergleichen(floats[0],floats[1]);
    }
    
    
    //endregion
    //region[Methoden]
    
    
    private float[] alterWerteZurueckgeben(@NotNull Tree tree, Tree otherTree)
    {
        float[] floats={BaumServices.bekanntesStandalterErmitteln(tree),BaumServices.bekanntesStandalterErmitteln(otherTree)};
        return floats;
    }
    
    
    private float[] hoeheMeterWerteZurueckgeben(@NotNull Tree tree, @NotNull Tree otherTree)
    {
        return new float[]{tree.getMetrik().getHoeheMeter(), otherTree.getMetrik().getHoeheMeter()};
    }
    
    
    private float[] kroneMeterWerteZurueckgeben(@NotNull Tree tree, @NotNull Tree otherTree)
    {
        float[] floats={tree.getMetrik().getKroneMeter(), otherTree.getMetrik().getKroneMeter()};
        return floats;
    }
    
    
    private float[] umfangZentimeterWerteZurueckgeben(@NotNull Tree tree, @NotNull Tree otherTree)
    {
        float[] floats={tree.getMetrik().getUmfangZentimeter(), otherTree.getMetrik().getUmfangZentimeter()};
        return floats;
    }
    
    
    private int wennBaumGroeszerIstVerhaeltnisZurueckgebenAnsonstenFloatVergleichen(float baum, float otherBaum)
    {
        int compare=Float.compare(baum,otherBaum);
        if(compare<=0|otherBaum==0)
        {
            return compare;
        }
        else
        {
            return Math.abs(Math.round(baum/otherBaum))+1;
        }
    }
 
    
    //endregion
    
    
}