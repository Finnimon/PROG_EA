package Services;

import control.TreeCadastreQueryController;
import Model.Tree;
import Model.Metric;
import org.jetbrains.annotations.NotNull;
import Resources.Constants;
import Resources.Strings;
import Utility.Core;

import java.util.Objects;

public class TreeServices
{
    
    
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(int attribute)
    {
        if (attribute != Constants.UNBEKANNT)
        {
            return Integer.toString(attribute);
        }
        
        
        return Strings.UNBEKANNT;
    }
    
    
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(float attribute)
    {
        if (attribute != Constants.UNBEKANNT)
        {
            return Float.toString(attribute);
        }
        
        
        return Strings.UNBEKANNT;
    }
    
    
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(String attribute)
    {
        attribute= Core.capitalizeAndTrimString(attribute);
        
        if (attribute.equals(Strings.UNBEKANNT)|| attribute.isBlank())
        {
            return Strings.UNBEKANNT;
        }
        return attribute;
        
    }
    
    
    public static boolean bekanntheitPruefen(int attribut)
    {
        return attribut != Constants.UNBEKANNT;
    }
    
    
    public static int bekanntesStandalterErmitteln(@NotNull Tree tree)
    {
        Metric metric = tree.getMetric();
        
        int standalter=0;
        
        if (TreeServices.bekanntheitPruefen(metric.getAgeInYears()))
        {
            standalter= metric.getAgeInYears();
        }
        else if(TreeServices.bekanntheitPruefen(metric.getPlantingYear()))
        {
            standalter= TreeCadastreQueryController.YEAR_OF_THE_SURVEY - metric.getPlantingYear();
        }
        
        
        return standalter;
    }
    
    
}
