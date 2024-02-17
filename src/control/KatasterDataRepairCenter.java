package control;

import model.Baum;
import model.BaumKataster;

import java.util.HashMap;

public class KatasterDataRepairCenter
{
    
    
    //region [Attribut]
    
    
    private HashMap<String, HashMap<Integer, Baum>> sortierteBaeume;
    private boolean istRepariert;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public KatasterDataRepairCenter(BaumKataster baumKataster)
    {
        setSortierteBaeume(katasterSortieren(baumKataster));
    }
    
    
    //endregion
    //region [Initialisierung]
    
    
    //todo init
    private void initialisieren()
    {
    
    }
    
    
    private HashMap<String, HashMap<Integer, Baum>> katasterSortieren(BaumKataster baumKataster)
    {
        HashMap<String, HashMap<Integer, Baum>> sortierterKataster = new HashMap<>();
        HashMap<Integer, Baum> baeume = baumKataster.getBaumKataster();
        
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
    
    
    
    private boolean getIstRepariert()
    {
        return this.istRepariert;
    }
    
    
    private void setIstRepariert(boolean istRepariert)
    {
        this.istRepariert=istRepariert;
    }
    
    
    private HashMap<String, HashMap<Integer, Baum>> getSortierteBaeume()
    {
        return sortierteBaeume;
    }
    
    
    private void setSortierteBaeume(HashMap<String, HashMap<Integer, Baum>> sortierteBaeume)
    {
        this.sortierteBaeume = sortierteBaeume;
    }
    
    
    public BaumKataster getReparierterKataster()throws Exception //todo custom
    {
        if(!getIstRepariert()) throw new Exception();
        
        HashMap<Integer, Baum> reparierteBaume = new HashMap<>();
        
        for (HashMap<Integer, Baum> spezifischeBaeume : getSortierteBaeume().values())
        {
            reparierteBaume.putAll(spezifischeBaeume);
        }
        
        
        return new BaumKataster(reparierteBaume);
    }
    
    
    //endregion
    
    
}
