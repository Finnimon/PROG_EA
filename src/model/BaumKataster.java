package model;

import resources.Konstanten;
import utility.iRepairableStatistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BaumKataster implements iRepairableStatistic
{
    
    
    //region[Attribut]
    
    
    private HashMap<Integer, Baum> kataster;
    
    
    //endregion
    //region[Konstruktoren]
    
    
    public BaumKataster(ArrayList<CSVRecord> cSV)
    {
        HashMap<Integer, Baum> baeume = new HashMap<>();
        for (CSVRecord cSVRecord : cSV)
        {
            ArrayList<String> record=cSVRecord.getRecord();
            try
            {
                //keep first remove next
                baeume.putIfAbsent(Integer.parseInt(record.getFirst()), Baum.create(record.subList(Konstanten.EINS, Konstanten.ZWOELF)));
            }
            catch (Exception e)
            {
            }
        }
        
        
        setBaumKataster(baeume);
    }
    
    
    public BaumKataster(HashMap<Integer,Baum> baeume)
    {
        setBaumKataster(baeume);
    }
    
    
    //endregion
    //region [GetSet]
    
    
    public HashMap<Integer, Baum> getBaumKataster()
    {
        return this.kataster;
    }
    
    
    private void setBaumKataster(HashMap<Integer, Baum> baeumeMap)
    {
        this.kataster = baeumeMap;
    }
    
    
    //endregion
    
    
    
}
