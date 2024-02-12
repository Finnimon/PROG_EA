package control;

import model.Baum;
import model.Kataster;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;
import utility.MyIO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class KatasterController
{
    public static final int HOEHE_METER_VERGLEICHEN = Konstanten.EINS;
    public static final int UMFANG_ZENTIMETER_VERGLEICHEN = Konstanten.ZWEI;
    public static final int KRONE_METER_VERGLEICHEN = Konstanten.DREI;
    public static final int ALTER_VERGLEICHEN = Konstanten.VIER;
    public static final int BAUMARTEN_ZAEHLEN = Konstanten.FUENF;
    public static final int GATTUNGEN_ZAEHLEN = Konstanten.SECHS;
    public static final int HAEUFIGSTE_GATTUNG_ZAEHLEN = Konstanten.SIEBEN;
    public static final int HAEUFIGSTEN_BEZIRK_ZAEHLEN = Konstanten.ACHT;
    public static final int JAHR_DER_ERHEBUNG = Konstanten.ZWEITAUSEND_UND_DREI_UND_ZWANZIG;
    
    
    //todo for question hashmap of with key string bezirk... and int counter
    
    
    public static boolean frageBeantworten(Kataster kataster, int fragenWahl)
    {
        int key;
        boolean bool = false;
        StringBuilder stringBuilder = new StringBuilder();
        if (HOEHE_METER_VERGLEICHEN <= fragenWahl && fragenWahl <= ALTER_VERGLEICHEN)
        {
            key = extremstenBaumFinden(kataster, fragenWahl);
            stringBuilder.append(kataster.getKataster().get(key).toString());
        }
        else if (BAUMARTEN_ZAEHLEN <= fragenWahl && fragenWahl <= GATTUNGEN_ZAEHLEN)
        {
            stringBuilder.append(baumArtenGattungenZaehlen(kataster, fragenWahl));
        }
        else if (HAEUFIGSTE_GATTUNG_ZAEHLEN <= fragenWahl && fragenWahl <= HAEUFIGSTEN_BEZIRK_ZAEHLEN)
        {
            stringBuilder.append(haeufigsteGattungBezirkZaehlen(kataster, fragenWahl));
        }
        
        MyIO.printLn(stringBuilder.toString());
        return bool;
    }
    
    
    public static int extremstenBaumFinden(Kataster kataster, int attribut)
    {
        //• Welche Gattung wächst am höchsten? avg
        //• Welche Gattung hat den größten Umfang? avg
        HashMap<Integer, Baum> baeume = kataster.getKataster();
        int keyGroeszterBaum = Konstanten.EINS; //could be any integer needs to be initialized
        
        for (Integer key : baeume.keySet())
        {
            if ((baeume.get(keyGroeszterBaum)).compareTo(baeume.get(key), attribut) < 0)
            {
                keyGroeszterBaum = key;
            }
        }
        
        
        return keyGroeszterBaum;
    }
    
    
    public static int baumArtenGattungenZaehlen(@NotNull Kataster kataster, int attribut)
    {
        HashSet<String> set = new HashSet<>();
        
        HashMap<Integer, Baum> baeume = kataster.getKataster();
        String string;
        
        //if ausserhalb der for schleife da so trotz redundantem code eine wiederholte abfrage des if vermieden wird
        if (attribut == BAUMARTEN_ZAEHLEN)
        {
            for (Baum baum : baeume.values())
            {
                string = baum.getTaxonomie().getSpeziesBot();
                set.add(string);
            }
        }
        else if (attribut == GATTUNGEN_ZAEHLEN)
        {
            for (Baum baum : baeume.values())
            {
                string = baum.getTaxonomie().getGattungBot();
                set.add(string);
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }
        
        set.remove(Strings.UNBEKANNT);
        
        
        return set.size();
    }
    
    
    public static String haeufigsteGattungBezirkZaehlen(Kataster kataster, int fragenIndex)
    {
        HashMap<Integer, Baum> baeume = kataster.getKataster();
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        String attribut = new String();
        
        for (Baum baum : baeume.values())
        {
            if (fragenIndex == HAEUFIGSTE_GATTUNG_ZAEHLEN)
            {
                attribut = baum.getTaxonomie().getGattungBot();
            }
            else if (fragenIndex == HAEUFIGSTEN_BEZIRK_ZAEHLEN)
            {
                attribut = baum.getOrt().getBezirk();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            if (attribut == Strings.UNBEKANNT)
            {
                continue;
            }
            
            
            try
            {
                hashMap.put(attribut, (hashMap.get(attribut) + Konstanten.EINS));
            }
            catch (NullPointerException e)
            {
                hashMap.put(attribut, Konstanten.EINS);
            }
        }
        
        Set<String> keySet = hashMap.keySet();
        
        for (String key : keySet)
        {
            if (attribut == Strings.UNBEKANNT)
            {
                attribut = key;
            }
            else if (hashMap.get(key) > hashMap.get(attribut))
            {
                attribut=key;
            }
        }
        
        
        return attribut;
    }
    
    
}
