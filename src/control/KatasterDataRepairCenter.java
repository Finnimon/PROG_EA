package control;

import model.Baum;
import model.Kataster;

import java.util.HashMap;

public class KatasterDataRepairCenter
{
    
    
    //region [Attribut]
    
    
    private HashMap<String, HashMap<Integer, Baum>> sortierterKataster;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public KatasterDataRepairCenter(Kataster kataster)
    {
        setSortierterKataster(katasterSortieren(kataster));
    }
    
    
    //endregion
    //region [Initialisierung]
    
    
    private HashMap<String, HashMap<Integer, Baum>> katasterSortieren(Kataster kataster)
    {
        HashMap<String, HashMap<Integer, Baum>> sortierterKataster = new HashMap<>();
        HashMap<Integer, Baum> baeume = kataster.getKataster();
        
        for (int key : baeume.keySet())
        {
            HashMap<Integer, Baum> spezifischeBaeume;
            
            Baum baum = baeume.get(key);
            
            String artBotanisch = baum.getTaxonomie().getArtBotanisch();
            try
            {
                spezifischeBaeume = sortierterKataster.get(artBotanisch);
            }
            catch (NullPointerException e)
            {
                spezifischeBaeume = new HashMap<>();
            }
            spezifischeBaeume.put(key, baum);
            
            sortierterKataster.put(artBotanisch, spezifischeBaeume);
        }
        return sortierterKataster;
    }
    
    
    //endregion
    //region[GetSet]
    
    
    public HashMap<String, HashMap<Integer, Baum>> getSortierterKataster()
    {
        return sortierterKataster;
    }
    
    
    private void setSortierterKataster(HashMap<String, HashMap<Integer, Baum>> sortierterKataster)
    {
        this.sortierterKataster = sortierterKataster;
    }

    
    //endregion
    

}
