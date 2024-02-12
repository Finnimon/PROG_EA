package control;

import model.Baum;
import model.Metrik;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;

public class BaumController
{
    
    public static void baumPruefen(Baum baum)
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
        return attribut >= 0;
    }
    
    public static boolean bekanntheitPruefen(float attribut)
    {
        return attribut >= 0;
    }
    
    public static boolean bekanntheitPruefen(String attribut)
    {
        return attribut != Strings.UNBEKANNT&!attribut.isBlank();
    }
    
    
    public static int alterVergleichen(@NotNull Baum baum, Baum otherBaum)
    {
        Metrik metrik = baum.getMetrik();
        Metrik otherMetrik = otherBaum.getMetrik();
        
        
        int standalter;
        
        //todo modularisieren
        if ((standalter = metrik.getStandalter()) == Konstanten.UNBEKANNT)
        {
            if ((standalter = metrik.getPflanzJahr()) != Konstanten.UNBEKANNT)
            {
                standalter = KatasterController.JAHR_DER_ERHEBUNG - standalter;
            }
        }
        
        
        int otherStandalter;
        
        if ((otherStandalter = otherMetrik.getStandalter()) == Konstanten.UNBEKANNT)
        {
            if ((otherStandalter = metrik.getPflanzJahr()) != Konstanten.UNBEKANNT)
            {
                otherStandalter = KatasterController.JAHR_DER_ERHEBUNG - otherStandalter;
            }
        }
        
        
        return standalter - otherStandalter;
    }
    
    
    public static int hoeheMeterVergleichen(@NotNull Baum baum, @NotNull Baum otherBaum)
    {
        return Float.compare(baum.getMetrik().getHoeheMeter(),otherBaum.getMetrik().getHoeheMeter());
    }
    
    
    public static int kroneMeterVergleichen(@NotNull Baum baum, @NotNull Baum otherBaum)
    {
        return Float.compare(baum.getMetrik().getKroneMeter() , otherBaum.getMetrik().getKroneMeter());
    }
    
    
    public static int umfangZentimeterVergleichen(@NotNull Baum baum, @NotNull Baum otherBaum)
    {
        return Float.compare( baum.getMetrik().getUmfangZentimeter() , otherBaum.getMetrik().getUmfangZentimeter());
    }
}
