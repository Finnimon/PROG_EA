package model;

import resources.Konstanten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Kataster
{
    
    
    private HashMap<Integer, Baum> kataster;
    
    
    public Kataster(ArrayList<ArrayList<String>> cSV)
    {
        HashMap<Integer, Baum> baeume = new HashMap<>();
        for (ArrayList<String> werte : cSV)
        {
            try
            {
                //keep first remove next
                baeume.putIfAbsent(Integer.parseInt(werte.getFirst()), new Baum(werte.subList(Konstanten.EINS, Konstanten.ZWOELF)));
            }
            catch (Exception e)
            {
            }
        }
        
        
        setKataster(baeume);
    }
    
    //Maybe important for regression except hash instead of arraylist of baum
    //    public Kataster(ArrayList<ArrayList<String>> werte)
    //    {
    //        HashMap<String, ArrayList<Baum>> baeume = new HashMap<>();
    //        Baum baum;
    //        ArrayList<Baum> spezifischeBaeume;
    //        String speziesBot;
    //        for (ArrayList<String> zeile : werte)
    //        {
    //            try
    //            {
    //                baum = new Baum(zeile);
    //            }
    //            catch (ElementFaultyException e)
    //            {
    //                continue;
    //            }
    //
    //            baeume.putIfAbsent((speziesBot = baum.getBotanik().getSpeziesBot()), new ArrayList<>());
    //            (spezifischeBaeume = baeume.get(speziesBot)).add(baum);
    //            baeume.put(speziesBot, spezifischeBaeume);
    //        }
    //
    //        //arraylist statt collection die sortedset implementiert wegen get(index) (für Median berechnung etc.)
    //        baeume=baeumeSortieren(baeume);
    //        setBaeume(baeume);
    //    }
    
    
    public HashMap<Integer, Baum> getKataster()
    {
        return this.kataster;
    }
    
    
    private void setKataster(HashMap<Integer, Baum> baeumeMap)
    {
        this.kataster = baeumeMap;
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
