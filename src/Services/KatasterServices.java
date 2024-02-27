package Services;

import logic.TreeContainedCarbonCalculator;
import model.Tree;
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
    
    
    public static String frageAntwortErmitteln(BaumKataster robusterBaumKataster, BaumKataster unverfaelschterBaumKataster, int fragenIndex)
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
            stringBuilder.append(String.format("%.2f", kohlenStoffSpeicherungInsgesamtBerechnen(robusterBaumKataster)));
        }
        else if (fragenIndex == INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK | fragenIndex == INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG)
        {
            stringBuilder.append(kohlenStoffSpeicherungStaerksteBezirkOderGattungFinden(robusterBaumKataster,  fragenIndex));
        }
        
        return stringBuilder.toString();
    }
    
    
    private static int baumArtenGattungenZaehlen(@NotNull BaumKataster baumKataster, int attribut)
    {
        HashSet<String> set = new HashSet<>();
        
        HashMap<Integer, Tree> baumBaum = baumKataster.getBaumHashMap();
        String string;
        
        if (attribut == INDEX_BAUMARTEN_ZAEHLEN)
        {
            for (Tree tree : baumBaum.values())
            {
                string = tree.getTaxonomie().getSpeciesBotanical();
                set.add(string);
            }
        }
        else if (attribut == INDEX_GATTUNGEN_ZAEHLEN)
        {
            for (Tree tree : baumBaum.values())
            {
                string = tree.getTaxonomie().getGenusBotanical();
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
    private static String haeufigsteGattungBezirkZaehlen(BaumKataster baumKataster, int fragenIndex)
    {
        HashMap<Integer, Tree> baeume = baumKataster.getBaumHashMap();
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        String attribut = new String();
        
        for (Tree tree : baeume.values())
        {
            if (fragenIndex == INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN)
            {
                attribut = tree.getTaxonomie().getGenusBotanical();
            }
            else if (fragenIndex == INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN)
            {
                attribut = tree.getOrt().getBezirk();
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
        
        for (Tree tree : baumKataster.getBaumHashMap().values())
        {
            String bezirk = tree.getOrt().getBezirk();
            
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
        for (Tree tree : baumKataster.getBaumHashMap().values())
        {
            String gattung = tree.getTaxonomie().getGenusBotanical();
            
            float wert;
            int counter = 0;
            
            Metrik metrik = tree.getMetrik();
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
            
            if (wert == baumKataster.getUnknown())
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
    
    
    private static Float kohlenStoffSpeicherungInsgesamtBerechnen(BaumKataster baumKataster)
    {
        TreeContainedCarbonCalculator treeContainedCarbonCalculator =new TreeContainedCarbonCalculator();
        float kohlenStoffSpeicherungInsgesamt = 0;
        for (Tree tree : baumKataster.getBaumHashMap().values())
        {
            kohlenStoffSpeicherungInsgesamt+= treeContainedCarbonCalculator.speicherungInKiloGrammFuerEinzelBaumBerechnen(tree);
        }
        
        
        return kohlenStoffSpeicherungInsgesamt;
    }
    
    
    private static String kohlenStoffSpeicherungStaerksteBezirkOderGattungFinden(BaumKataster robusterBaumKataster, int fragenIndex)
    {
        
        TreeContainedCarbonCalculator treeContainedCarbonCalculator =new TreeContainedCarbonCalculator();
        HashMap<String, Float> kohlenStoffSpeicherungNachBezirkOderGattung = new HashMap<>();
        for (Tree tree : robusterBaumKataster.getBaumHashMap().values())
        {
            String key;
            if (fragenIndex == INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK)
            {
                key = tree.getOrt().getBezirk();
            }
            else if (fragenIndex == INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG)
            {
                key = tree.getTaxonomie().getGenusBotanical();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            float wert = treeContainedCarbonCalculator.speicherungInKiloGrammFuerEinzelBaumBerechnen(tree);
            
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
