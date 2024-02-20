package Services;

import model.Baum;
import model.BaumKataster;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;
import utility.BaumKatasterEntryComparator;
import utility.Core;
import utility.MyIO;

import java.util.*;

public class KatasterServices
{
    
    
    //region[Konstanten]
    
    
    public static final int INDEX_BEZIRK_MIT_GROESZTEM_BAUM = Konstanten.EINS;
    public static final int INDEX_UMFANG_ZENTIMETER_VERGLEICHEN = Konstanten.ZWEI;
    public static final int INDEX_KRONE_METER_VERGLEICHEN = Konstanten.DREI;
    public static final int INDEX_ALTER_VERGLEICHEN = Konstanten.VIER;
    public static final int INDEX_BAUMARTEN_ZAEHLEN = Konstanten.FUENF;
    public static final int INDEX_GATTUNGEN_ZAEHLEN = Konstanten.SECHS;
    public static final int INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN = Konstanten.SIEBEN;
    public static final int INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN = Konstanten.ACHT;
    public static final int JAHR_DER_ERHEBUNG = Konstanten.ZWEITAUSEND_UND_DREI_UND_ZWANZIG;
    
    
    //endregion
    //todo find largest value in hashmap and comparator
    
    
    //todo frage beantworten gives back just the Answer string
    public static String frageAntwortErmitteln(BaumKataster baumKataster, int fragenWahl)
    {//todo alle fragen richtig gelesen?
        //todo messages
        int key;
        StringBuilder stringBuilder = new StringBuilder();
        
        if (INDEX_BEZIRK_MIT_GROESZTEM_BAUM <= fragenWahl && fragenWahl <= INDEX_ALTER_VERGLEICHEN)
        {
            key = keyDesGroesztenValueFinden(baumKataster, fragenWahl);
            stringBuilder.append(baumKataster.getBaumKataster().get(key).toString());
        }
        else if (INDEX_BAUMARTEN_ZAEHLEN <= fragenWahl && fragenWahl <= INDEX_GATTUNGEN_ZAEHLEN)
        {
            stringBuilder.append(baumArtenGattungenZaehlen(baumKataster, fragenWahl));
        }
        else if (INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN <= fragenWahl && fragenWahl <= INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN)
        {
            stringBuilder.append(haeufigsteGattungBezirkZaehlen(baumKataster, fragenWahl));
        }
        
        MyIO.printLn(stringBuilder.toString());
        return stringBuilder.toString();
    }
    
    
    public static int baumArtenGattungenZaehlen(@NotNull BaumKataster baumKataster, int attribut)
    {
        HashSet<String> set = new HashSet<>();
        
        HashMap<Integer, Baum> baeume = baumKataster.getBaumKataster();
        String string;
        
        //if ausserhalb der for schleife da so trotz redundantem code eine wiederholte abfrage des if vermieden wird
        if (attribut == INDEX_BAUMARTEN_ZAEHLEN)
        {
            for (Baum baum : baeume.values())
            {
                string = baum.getTaxonomie().getArtBotanisch();
                set.add(string);
            }
        }
        else if (attribut == INDEX_GATTUNGEN_ZAEHLEN)
        {
            for (Baum baum : baeume.values())
            {
                string = baum.getTaxonomie().getGattungBotanisch();
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
    
    
    //todo yikes
    public static String haeufigsteGattungBezirkZaehlen(BaumKataster baumKataster, int fragenIndex)
    {
        HashMap<Integer, Baum> baeume = baumKataster.getBaumKataster();
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        String attribut = new String();
        
        for (Baum baum : baeume.values())
        {
            if (fragenIndex == INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN)
            {
                attribut = baum.getTaxonomie().getGattungBotanisch();
            }
            else if (fragenIndex == INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN)
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
                attribut = key;
            }
        }
        
        
        return Core.keyStringDesGroesztenWertIntegerInHashMapFinden(hashMap);
    }
    
    
    public static int keyDesGroesztenValueFinden(BaumKataster baumKataster, int attribut) throws IllegalArgumentException
    {
        return Collections.max(baumKataster.getBaumKataster().entrySet(), new BaumKatasterEntryComparator(attribut)).getKey();
    }
    
    
    private HashMap<String, ArrayList<Baum>> baeumeSortieren(HashMap<String, ArrayList<Baum>> baeume)
    {
        
        ArrayList<Baum> spezifischeBaeume;
        for (String key : baeume.keySet())
        {
            Collections.sort(spezifischeBaeume = baeume.get(key));
            baeume.put(key, spezifischeBaeume);
        }
        
        return baeume;
    }
    
    
}
