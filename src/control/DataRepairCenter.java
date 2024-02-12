package control;

import model.Baum;
import model.Kataster;

import java.util.HashMap;

public class DataRepairCenter
{
    private HashMap<String, HashMap<Integer, Baum>> sortierterKataster;
    
    
    public DataRepairCenter(Kataster kataster)
    {
        HashMap<String, HashMap<Integer, Baum>> sortierterKataster = new HashMap<>();
        HashMap<Integer, Baum> baeume = kataster.getKataster();
        
        for (int key : baeume.keySet())
        {
            HashMap<Integer, Baum> spezifischeBaeume;
            
            Baum baum = baeume.get(key);
            
            String artBotanisch=baum.getTaxonomie().getSpeziesBot();
            try
            {
                spezifischeBaeume= sortierterKataster.get(artBotanisch);
            }
            catch (NullPointerException e)
            {
                spezifischeBaeume=new HashMap<>();
            }
            spezifischeBaeume.put(key,baum);
            
            sortierterKataster.put(artBotanisch,spezifischeBaeume);
        }
        
        setSortierterKataster(sortierterKataster);
    }
    
    
    public HashMap<String, HashMap<Integer, Baum>> getSortierterKataster()
    {
        return sortierterKataster;
    }
    
    
    public void setSortierterKataster(HashMap<String, HashMap<Integer, Baum>> sortierterKataster)
    {
        this.sortierterKataster = sortierterKataster;
    }
}
