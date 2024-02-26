package Services;

import model.Tree;
import model.Metrik;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;

import java.util.Objects;

public class BaumServices
{
    
    public static void baumPruefen(Tree tree)
    {
    //todo und reparieren
    }
    
    
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
        return attribut !=Konstanten.UNBEKANNT;
    }
    
    public static boolean bekanntheitPruefen(float attribut)
    {
        return attribut !=Konstanten.UNBEKANNT;
    }
    
    public static boolean bekanntheitPruefen(String attribut)
    {
        return !Objects.equals(attribut, Strings.UNBEKANNT) &!attribut.isBlank();
    }
    
    
    public static int bekanntesStandalterErmitteln(@NotNull Tree tree)
    {
        
        
        Metrik metrik = tree.getMetrik();
        
        int standalter=0;
        
        if (BaumServices.bekanntheitPruefen(metrik.getStandAlter()))
        {
            standalter= metrik.getStandAlter();
        }
        else if(BaumServices.bekanntheitPruefen(metrik.getPflanzJahr()))
        {
            standalter= KatasterServices.JAHR_DER_ERHEBUNG- metrik.getPflanzJahr();
        }
        
        
        return standalter;
    }
    
    
}
