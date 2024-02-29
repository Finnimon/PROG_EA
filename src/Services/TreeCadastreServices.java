package Services;

import control.TreeCadastreQueryController;
import logic.TreeCarbonRetentionCalculator;
import Model.Metric;
import Model.Tree;
import Model.TreeCadastre;
import org.jetbrains.annotations.NotNull;
import Resources.Strings;

import java.util.*;


public class TreeCadastreServices
{
    
    
    /**
     * @param treeCadastre The {@link TreeCadastre} to count the number of different not unknown species or genera.
     * @param queryIndex The index of the {@link TreeCadastreQueryController} that should be used to determine the type of the query.
     * @return The number of different not unknown species or genera.
     * @throws IllegalArgumentException If the queryIndex is not valid.
     * @Precondition: The param {@link TreeCadastre} is not null and the queryIndex is either {@link TreeCadastreQueryController#INDEX_COUNT_SPECIES} or {@link TreeCadastreQueryController#INDEX_COUNT_GENERA}.
     * @Postcondition: The number of different not unknown species or genera is returned and no exceptions will be thrown.
     * @Summary: Counts the number of different not unknown species or genera depending on the queryIndex.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static int countTreeGenusOrSpecies(@NotNull TreeCadastre treeCadastre, int queryIndex)
    {
        HashSet<String> set = new HashSet<>();
        
        HashMap<Integer, Tree> treeHashMap = treeCadastre.getTreeHashMap();
        String string;
        
        for (Tree tree : treeHashMap.values())
        {
            if (queryIndex == TreeCadastreQueryController.INDEX_COUNT_SPECIES)
            {
                string = tree.getTaxonomy().getSpeciesBotanical();
                
            }
            else if (queryIndex == TreeCadastreQueryController.INDEX_COUNT_GENERA)
            {
                string = tree.getTaxonomy().getGenusBotanical();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            set.add(string);
        }
        
        set.remove(Strings.UNBEKANNT);
        
        
        return set.size();
    }
    
    
    /**
     * @param treeCadastre The {@link TreeCadastre} to find the most common district or genus within.
     * @param queryIndex The index of the {@link TreeCadastreQueryController} that should be used to determine the type of the query.
     * @return The most common district or genus within the {@link TreeCadastre}.
     * @throws IllegalArgumentException If the queryIndex is not valid.
     * @Precondition: The param {@link TreeCadastre} is not null and the queryIndex is either {@link TreeCadastreQueryController#INDEX_FIND_MOST_COMMON_GENUS} or {@link TreeCadastreQueryController#INDEX_FIND_MOST_COMMON_DISTRICT}.
     * @Postcondition: The most common district or genus depending on the queryIndex is returned and no exceptions will be thrown.
     * @Summary: Find and return either the most common district or genus within the {@link TreeCadastre} depending on the queryIndex.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String findMostCommonGenusOrDistrict(@NotNull TreeCadastre treeCadastre, int queryIndex)
    {
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        for (Tree tree : treeCadastre.getTreeHashMap().values())
        {
            String string;
            if (queryIndex == TreeCadastreQueryController.INDEX_FIND_MOST_COMMON_GENUS)
            {
                string = tree.getTaxonomy().getGenusBotanical();
            }
            else if (queryIndex == TreeCadastreQueryController.INDEX_FIND_MOST_COMMON_DISTRICT)
            {
                string = tree.getSite().district();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            int count;
            
            try
            {
                count=hashMap.get(string);
            }
            catch (NullPointerException e)
            {
                count=0;
            }
            count++;
            
            hashMap.put(string, count);
        }
        
        hashMap.remove(Strings.UNBEKANNT);
        
        
        return Collections.max(hashMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    
    /**
     * @param treeCadastre The {@link TreeCadastre} to perform the query on.
     * @return The {@link Site#}.
     * @throws IllegalArgumentException If the queryIndex is not valid.
     * @Precondition: The param {@link TreeCadastre} is not null and the queryIndex is either {@link TreeCadastreQueryController#INDEX_FIND_MOST_COMMON_GENUS} or {@link TreeCadastreQueryController#INDEX_FIND_MOST_COMMON_DISTRICT}.
     * @Postcondition: The most common district or genus depending on the queryIndex is returned and no exceptions will be thrown.
     * @Summary: Find and return either the most common district or genus within the {@link TreeCadastre} depending on the queryIndex.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String findDistrictWithMostDifferentiableTreeSpecies(TreeCadastre treeCadastre)
    {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        
        for (Tree tree : treeCadastre.getTreeHashMap().values())
        {
            String bezirk = tree.getSite().district();
            
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
            if (attribut == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_CIRCUMFERENCE)
            {
                wert = metric.getCircumferenceCentimeters();
            }
            else if (attribut == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_HEIGHT)
            {
                wert = metric.getHeightMeters();
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            if (wert == treeCadastre.UNKNOWN())
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
        
        averages.replaceAll((k, v) -> averages.get(k) / counters.get(k));
        
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
            if (fragenIndex == TreeCadastreQueryController.INDEX_FIND_DISTRICT_OF_GREATEST_CARBON_RETENTION)
            {
                key = tree.getSite().district();
            }
            else if (fragenIndex == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_GREATEST_CARBON_RETENTION)
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
        String germanGenus = Strings.UNBEKANNT;
        for (Tree tree : treeCadastre.getTreeHashMap().values())
        {
            if (tree.getTaxonomy().getGenusBotanical().equals(botanicalGenus))
            {
                germanGenus = tree.getTaxonomy().getGenusGerman();
                if (germanGenus != null & !germanGenus.equals(Strings.UNBEKANNT))
                {
                    return germanGenus;
                }
            }
        }
        
        
        return germanGenus;
    }
    
    
}
