package services;

import control.TreeCadastreQueryController;
import model.Tree;
import model.Metric;
import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Strings;

import java.util.Objects;

public class BaumServices
{
    
    
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(int attribut)
    {
        if (bekanntheitPruefen(attribut))
        {
            return Integer.toString(attribut);
        }
        return Strings.UNBEKANNT;
    }
    
    
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(float attribut)
    {
        if (bekanntheitPruefen(attribut))
        {
            return Float.toString(attribut);
        }
        return Strings.UNBEKANNT;
    }
    
    
    public static String ausgabeStringUnbekanntesAttributZurueckgeben(String attribut)
    {
        if (bekanntheitPruefen(attribut))
        {
            return attribut;
        }
        //        attribut.isBlank() | attribut.toLowerCase() == Strings.UNBEKANNT_AUSGESCHRIEBEN.toLowerCase()
        return Strings.UNBEKANNT;
    }
    
    
    public static boolean bekanntheitPruefen(int attribut)
    {
        return attribut != Constants.UNBEKANNT;
    }
    
    public static boolean bekanntheitPruefen(float attribut)
    {
        return attribut != Constants.UNBEKANNT;
    }
    
    public static boolean bekanntheitPruefen(String attribut)
    {
        return !Objects.equals(attribut, Strings.UNBEKANNT) &!attribut.isBlank();
    }
    
    
    public static int bekanntesStandalterErmitteln(@NotNull Tree tree)
    {
        
        
        Metric metric = tree.getMetric();
        
        int standalter=0;
        
        if (BaumServices.bekanntheitPruefen(metric.getAgeInYears()))
        {
            standalter= metric.getAgeInYears();
        }
        else if(BaumServices.bekanntheitPruefen(metric.getPlantingYear()))
        {
            standalter= TreeCadastreQueryController.JAHR_DER_ERHEBUNG- metric.getPlantingYear();
        }
        
        
        return standalter;
    }
    
    
}
