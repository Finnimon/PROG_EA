package Services;

import model.Baum;
import model.BaumKataster;
import model.Metrik;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Messages;
import resources.Strings;
import utility.BaumKatasterEntryComparator;

import java.util.*;

public class KatasterServices
{
    
    
    public static final int INDEX_BEZIRK_MIT_GROESZTEM_BAUM = 1;
    public static final int INDEX_UMFANG_ZENTIMETER_VERGLEICHEN = 2;
    public static final int INDEX_KRONE_METER_VERGLEICHEN = 3;
    public static final int INDEX_ALTER_VERGLEICHEN = 4;
    public static final int INDEX_BAUMARTEN_ZAEHLEN = 5;
    public static final int INDEX_GATTUNGEN_ZAEHLEN = 6;
    public static final int INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN = 7;
    public static final int INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN = 8;
    public static final int INDEX_BEZIRK_MIT_MEISTEN_ARTEN = 9;
    public static final int INDEX_WELCHE_GATTUNG_WAECHST_AM_HOECHSTEN = 10;
    public static final int INDEX_WELCHE_GATTUNG_GROESZTER_UMFANG = 11;
    public static final int INDEX_KOHLENSTOFF_SPEICHERUNG_BERLIN_INSGESAMT = 12;
    public static final int INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK = 13;
    public static final int INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG = 14;
    public static final int JAHR_DER_ERHEBUNG = Konstanten.ZWEITAUSEND_UND_DREI_UND_ZWANZIG;
    
    
    //todo find largest value in hashmap and comparator
    
    
    public static String frageAntwortErmitteln(BaumKataster robusterBaumKataster, BaumKataster unverfaelschterBaumKataster, HashMap<Integer, Float> kohlenStoffSpeicherungNachBaumIndex, int fragenIndex)
    {//todo alle fragen richtig gelesen?
        //todo messages
        int key;
        
        StringBuilder stringBuilder = new StringBuilder(Messages.ANTWORTEN[fragenIndex]);
        
        if (INDEX_BEZIRK_MIT_GROESZTEM_BAUM <= fragenIndex && fragenIndex <= INDEX_ALTER_VERGLEICHEN)
        {
            key = Collections.max(unverfaelschterBaumKataster.getBaumHashMap().entrySet(), new BaumKatasterEntryComparator(fragenIndex)).getKey();
            if (fragenIndex == INDEX_BEZIRK_MIT_GROESZTEM_BAUM)
            {
                stringBuilder.append(unverfaelschterBaumKataster.getBaumHashMap().get(key).getOrt().getBezirk());
            }
            else
            {
                stringBuilder.append(unverfaelschterBaumKataster.entryToString(key));
            }
        }
        else if (INDEX_BAUMARTEN_ZAEHLEN <= fragenIndex && fragenIndex <= INDEX_GATTUNGEN_ZAEHLEN)
        {
            stringBuilder.append(baumArtenGattungenZaehlen(robusterBaumKataster, fragenIndex));
        }
        else if (INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN <= fragenIndex && fragenIndex <= INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN)
        {
            stringBuilder.append(haeufigsteGattungBezirkZaehlen(robusterBaumKataster, fragenIndex));
        }
        else if (INDEX_BEZIRK_MIT_MEISTEN_ARTEN == fragenIndex)
        {
            
            stringBuilder.append(bezirkMitMeistenBaumArtenFinden(robusterBaumKataster));
        }
        else if (INDEX_WELCHE_GATTUNG_WAECHST_AM_HOECHSTEN == fragenIndex | fragenIndex == INDEX_WELCHE_GATTUNG_GROESZTER_UMFANG)
        {
            stringBuilder.append(extremsteDurchschnittlicheGattungFinden(unverfaelschterBaumKataster, fragenIndex));
        }
        else if (fragenIndex == INDEX_KOHLENSTOFF_SPEICHERUNG_BERLIN_INSGESAMT)
        {
            //todo format
            stringBuilder.append(String.format("%.2f", kohlenStoffSpeicherungInsgesamtBerechnen(kohlenStoffSpeicherungNachBaumIndex)));
        }
        else if (fragenIndex==INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK|fragenIndex==INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG)
        {
            stringBuilder.append(String.format("%.2f", kohlenStoffSpeicherungStaerksteBezirkOderGattungFinden(robusterBaumKataster, kohlenStoffSpeicherungNachBaumIndex, fragenIndex)));
        }
        
        return stringBuilder.toString();
    }
    
    
    public static int baumArtenGattungenZaehlen(@NotNull BaumKataster baumKataster, int attribut)
    {
        HashSet<String> set = new HashSet<>();
        
        HashMap<Integer, Baum> baumBaum = baumKataster.getBaumHashMap();
        String string;
        
        if (attribut == INDEX_BAUMARTEN_ZAEHLEN)
        {
            for (Baum baum : baumBaum.values())
            {
                string = baum.getTaxonomie().getArtBotanisch();
                set.add(string);
            }
        }
        else if (attribut == INDEX_GATTUNGEN_ZAEHLEN)
        {
            for (Baum baum : baumBaum.values())
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
        HashMap<Integer, Baum> baeume = baumKataster.getBaumHashMap();
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
        
        
        return Collections.max(hashMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    
    private static String bezirkMitMeistenBaumArtenFinden(BaumKataster baumKataster)
    {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        
        for (Baum baum : baumKataster.getBaumHashMap().values())
        {
            String bezirk = baum.getOrt().getBezirk();
            
            HashSet<String> bezirke = hashMap.get(bezirk);
            
            if (bezirke == null)
            {
                bezirke = new HashSet<>();
            }
            
            bezirke.add(bezirk);
            hashMap.put(bezirk, bezirke);
        }
        
        
        return Collections.max(hashMap.entrySet(), Comparator.comparingInt(entry -> entry.getValue().size())).getKey();
    }
    
    
    private static String extremsteDurchschnittlicheGattungFinden(BaumKataster baumKataster, int attribut)
    {
        HashMap<String, Float> averages = new HashMap<>();
        HashMap<String, Integer> counters = new HashMap<>();
        for (Baum baum : baumKataster.getBaumHashMap().values())
        {
            String gattung = baum.getTaxonomie().getGattungBotanisch();
            
            float wert;
            int counter = 0;
            
            Metrik metrik = baum.getMetrik();
            if (attribut == INDEX_WELCHE_GATTUNG_GROESZTER_UMFANG)
            {
                wert = metrik.getUmfangZentimeter();
            }
            else if (attribut == INDEX_WELCHE_GATTUNG_WAECHST_AM_HOECHSTEN)
            {
                wert = metrik.getHoeheMeter();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            if (wert == baumKataster.getUNKNOWN())
            {
                continue;
            }
            
            counter++;
            try
            {
                counter += counters.get(gattung);
                wert += averages.get(gattung);
            }
            catch (NullPointerException e)
            {
            }
            
            averages.put(gattung, wert);
            counters.put(gattung, counter);
        }
        
        for (String key : averages.keySet())
        {
            averages.put(key, averages.get(key) / counters.get(key));
        }
        
        averages.remove(Strings.UNBEKANNT);
        
        
        return Collections.max(averages.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    
    private static Float kohlenStoffSpeicherungInsgesamtBerechnen(HashMap<Integer, Float> kohlenStoffSpeicherungNachBaumIndex)
    {
        float kohlenStoffSpeicherungInsgesamt = 0f;
        for (Float wert : kohlenStoffSpeicherungNachBaumIndex.values())
        {
            kohlenStoffSpeicherungInsgesamt += wert;
        }
        
        
        return kohlenStoffSpeicherungInsgesamt;
    }
    
    
    private static String kohlenStoffSpeicherungStaerksteBezirkOderGattungFinden(BaumKataster robusterBaumKataster, HashMap<Integer, Float> kohlenStoffSpeicherungNachBaumIndex, int fragenIndex)
    {
        HashMap<String, Float> kohlenStoffSpeicherungNachBezirkOderGattung = new HashMap<>();
        HashMap<Integer, Baum> baumHashMap = robusterBaumKataster.getBaumHashMap();
        for (Integer baumKey : baumHashMap.keySet())
        {
            Baum baum = baumHashMap.get(baumKey);
            
            String key;
            if (fragenIndex == INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK)
            {
                key = baum.getOrt().getBezirk();
            }
            else if (fragenIndex == INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG)
            {
                key = baum.getTaxonomie().getGattungBotanisch();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            float wert = kohlenStoffSpeicherungNachBaumIndex.get(key);
            
            if (kohlenStoffSpeicherungNachBezirkOderGattung.containsKey(key))
            {
                wert += kohlenStoffSpeicherungNachBezirkOderGattung.get(key);
            }
            
            kohlenStoffSpeicherungNachBezirkOderGattung.put(key, wert);
        }
        
        kohlenStoffSpeicherungNachBezirkOderGattung.remove(Strings.UNBEKANNT);
        return Collections.max(kohlenStoffSpeicherungNachBezirkOderGattung.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
}
