package services;

import control.TreeCadastreQueryController;
import logic.TreeCarbonRetentionCalculator;
import model.Metric;
import model.Tree;
import model.TreeCadastre;
import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Strings;
import java.util.*;


public class KatasterServices
{
    
    
    public static int baumArtenGattungenZaehlen(@NotNull TreeCadastre treeCadastre, int attribut)
    {
        HashSet<String> set = new HashSet<>();
        
        HashMap<Integer, Tree> treeHashMap = treeCadastre.getTreeHashMap();
        String string;
        
        if (attribut == TreeCadastreQueryController.INDEX_BAUMARTEN_ZAEHLEN)
        {
            for (Tree tree : treeHashMap.values())
            {
                string = tree.getTaxonomy().getSpeciesBotanical();
                set.add(string);
            }
        }
        else if (attribut == TreeCadastreQueryController.INDEX_GATTUNGEN_ZAEHLEN)
        {
            for (Tree tree : treeHashMap.values())
            {
                string = tree.getTaxonomy().getGenusBotanical();
                set.add(string);
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }
        for (Tree tree : treeHashMap.values())
        {
        
        }
        
        set.remove(Strings.UNBEKANNT);
        
        
        return set.size();
    }
    
    
    //todo yikes
    public static String haeufigsteGattungBezirkZaehlen(TreeCadastre treeCadastre, int fragenIndex)
    {
        HashMap<Integer, Tree> baeume = treeCadastre.getTreeHashMap();
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        String attribut = new String();
        
        for (Tree tree : baeume.values())
        {
            if (fragenIndex == TreeCadastreQueryController.INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN)
            {
                attribut = tree.getTaxonomy().getGenusBotanical();
            }
            else if (fragenIndex == TreeCadastreQueryController.INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN)
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
                hashMap.put(attribut, (hashMap.get(attribut) + Constants.EINS));
            }
            catch (NullPointerException e)
            {
                hashMap.put(attribut, Constants.EINS);
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
    
    
    public static String bezirkMitMeistenBaumArtenFinden(TreeCadastre treeCadastre)
    {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        
        for (Tree tree : treeCadastre.getTreeHashMap().values())
        {
            String bezirk = tree.getOrt().getBezirk();
            
            HashSet<String> baumArten = hashMap.get(bezirk);
            
            if (baumArten == null)
            {
                baumArten = new HashSet<>();
            }
            
            baumArten.add(tree.getTaxonomy().getSpeciesGerman());
            hashMap.put(bezirk, baumArten);
        }
        
        
        return Collections.max(hashMap.entrySet(), Comparator.comparingInt(entry -> entry.getValue().size())).getKey();
    }
    
    
    public static String extremsteDurchschnittlicheGattungFinden(TreeCadastre treeCadastre, int attribut)
    {
        HashMap<String, Float> averages = new HashMap<>();
        HashMap<String, Integer> counters = new HashMap<>();
        for (Tree tree : treeCadastre.getTreeHashMap().values())
        {
            String gattung = tree.getTaxonomy().getGenusBotanical();
            
            float wert;
            int counter = 0;
            
            Metric metric = tree.getMetric();
            if (attribut == TreeCadastreQueryController.INDEX_WELCHE_GATTUNG_GROESZTER_UMFANG)
            {
                wert = metric.getCircumferenceCentimeters();
            }
            else if (attribut == TreeCadastreQueryController.INDEX_WELCHE_GATTUNG_WAECHST_AM_HOECHSTEN)
            {
                wert = metric.getHeightMeters();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            if (wert == treeCadastre.getUnknown())
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
    
    
    public static Float kohlenStoffSpeicherungInKiloGrammInsgesamtBerechnen(TreeCadastre treeCadastre)
    {
        TreeCarbonRetentionCalculator treeCarbonRetentionCalculator = new TreeCarbonRetentionCalculator();
        float kohlenStoffSpeicherungInsgesamt = 0;
        
        for (Tree tree : treeCadastre.getTreeHashMap().values())
        {
            kohlenStoffSpeicherungInsgesamt += treeCarbonRetentionCalculator.calculateContainment(tree);
        }
        
        
        return kohlenStoffSpeicherungInsgesamt;
    }
    
    
    public static String kohlenStoffSpeicherungStaerksteBezirkOderGattungFinden(TreeCadastre robusterTreeCadastre, int fragenIndex)
    {
        
        TreeCarbonRetentionCalculator treeCarbonRetentionCalculator = new TreeCarbonRetentionCalculator();
        HashMap<String, Float> kohlenStoffSpeicherungNachBezirkOderGattung = new HashMap<>();
        for (Tree tree : robusterTreeCadastre.getTreeHashMap().values())
        {
            String key;
            if (fragenIndex == TreeCadastreQueryController.INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK)
            {
                key = tree.getOrt().getBezirk();
            }
            else if (fragenIndex == TreeCadastreQueryController.INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG)
            {
                key = tree.getTaxonomy().getGenusBotanical();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            float wert = treeCarbonRetentionCalculator.calculateContainment(tree);
            
            if (kohlenStoffSpeicherungNachBezirkOderGattung.containsKey(key))
            {
                wert += kohlenStoffSpeicherungNachBezirkOderGattung.get(key);
            }
            
            kohlenStoffSpeicherungNachBezirkOderGattung.put(key, wert);
        }
        
        kohlenStoffSpeicherungNachBezirkOderGattung.remove(Strings.UNBEKANNT);
        
        
        return Collections.max(kohlenStoffSpeicherungNachBezirkOderGattung.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    
    public static String findCorrespondingGermanGenus(String botanicalGenus, TreeCadastre treeCadastre)
    {
        String germanGenus=Strings.UNBEKANNT;
        for (Tree tree : treeCadastre.getTreeHashMap().values())
        {
            if (tree.getTaxonomy().getGenusBotanical().equals(botanicalGenus))
            {
                 germanGenus= tree.getTaxonomy().getGenusGerman();
                 if (germanGenus != null&!germanGenus.equals( Strings.UNBEKANNT))
                 {
                     return germanGenus;
                 }
            }
        }
        
        
        return germanGenus;
    }
    
    
}
