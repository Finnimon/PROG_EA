package utility;

import services.BaumServices;
import control.TreeCadastreQueryController;
import model.Tree;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Comparator;

public class BaumComparator implements Comparator<Tree>
{
    //todo    private final Field vergleichendesField;
    
    //region [Konstante]
    
    
    private static final Integer[] erlaubteAttributIndize = {1, 2, 3, 4};
    
    
    public static final int INDEX_HOEHE_METER= TreeCadastreQueryController.INDEX_BEZIRK_MIT_GROESZTEM_BAUM;
    
    
    public static final int INDEX_KRONE_METER= TreeCadastreQueryController.INDEX_KRONE_METER_VERGLEICHEN;
    
    
    public static final int INDEX_UMFANG_ZENTIMETER= TreeCadastreQueryController.INDEX_UMFANG_ZENTIMETER_VERGLEICHEN;
    
    
    public static final int INDEX_ALTER= TreeCadastreQueryController.INDEX_ALTER_VERGLEICHEN;
    
    
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
        float[] floats={tree.getMetric().getAttributeByTreeComparatorIndex(getAttributIndex()), otherTree.getMetric().getAttributeByTreeComparatorIndex(getAttributIndex())};
        
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
        return new float[]{tree.getMetric().getHeightMeters(), otherTree.getMetric().getHeightMeters()};
    }
    
    
    private float[] kroneMeterWerteZurueckgeben(@NotNull Tree tree, @NotNull Tree otherTree)
    {
        float[] floats={tree.getMetric().getTreeTopDiameterMeters(), otherTree.getMetric().getTreeTopDiameterMeters()};
        return floats;
    }
    
    
    private float[] umfangZentimeterWerteZurueckgeben(@NotNull Tree tree, @NotNull Tree otherTree)
    {
        float[] floats={tree.getMetric().getCircumferenceCentimeters(), otherTree.getMetric().getCircumferenceCentimeters()};
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