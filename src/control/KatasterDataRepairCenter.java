package control;

import model.Baum;
import model.Kataster;

import java.util.HashMap;

public class KatasterDataRepairCenter
{
    
    
    //region [Attribut]
    
    
    private HashMap<String, HashMap<Integer, Baum>> sortierteBaeume;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public KatasterDataRepairCenter(Kataster kataster)
    {
        setSortierteBaeume(katasterSortieren(kataster));
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
    //region [Methoden]
    
    
    //endregion
    //region[GetSet]
    
    
    public HashMap<String, HashMap<Integer, Baum>> getSortierteBaeume()
    {
        return sortierteBaeume;
    }
    
    
    private void setSortierteBaeume(HashMap<String, HashMap<Integer, Baum>> sortierteBaeume)
    {
        this.sortierteBaeume = sortierteBaeume;
    }
    
    
    public Kataster getReparierterKataster()
    {
        HashMap<Integer, Baum> reparierteBaume = new HashMap<>();
        
        for (HashMap<Integer, Baum> spezifischeBaeume : getSortierteBaeume().values())
        {
            reparierteBaume.putAll(spezifischeBaeume);
        }
        
        
        return new Kataster(reparierteBaume);
    }
    
    
    //endregion
    
    
}
