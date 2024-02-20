package model;

import resources.Konstanten;
import resources.Strings;
import utility.iRepairable;
import utility.iRepairableStatistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ArrayList<String> record = cSVRecord.getRecord();
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
    
    
    public BaumKataster(List<Map.Entry<Integer, Baum>> list)
    {
        HashMap<Integer, Baum> baeumeHashMap = new HashMap<>();
        for (Map.Entry<Integer, Baum> entry : list)
        {
            baeumeHashMap.put(entry.getKey(), entry.getValue());
        }
        setBaumKataster(baeumeHashMap);
    }
    
    
    public BaumKataster(HashMap<Integer, Baum> baeume)
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
    //region [Overrides]
    
    
    @Override
    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        HashMap<Integer, Baum> baumHashMap = getBaumKataster();
        
        for (Integer key : baumHashMap.keySet())
        {
            stringbuilder.append(Strings.OBJEKT_ID);
            stringbuilder.append(Strings.TABULATOR);
            stringbuilder.append(key);
            Baum baum = baumHashMap.get(key);
            stringbuilder.append(baum.toString());
        }
        
        return stringbuilder.toString();
    }
    
    
    @Override
    public void put(Integer key, iRepairable repairableObject)
    {
        HashMap<Integer, Baum> baeumeHashMap = getBaumKataster();
        baeumeHashMap.put(key, (Baum) repairableObject);
        setBaumKataster(baeumeHashMap);
    }
    
    
    @Override
    public ArrayList<Float> getPermissableMaxima()
    {
        for (Baum baum :getBaumKataster().values())
        {
            return baum.getPermissableMaxima();
        }
        return null;
    }
    
    
    @Override
    public HashMap<Integer, ArrayList<Float>> getRepairableFloats()
    {
        HashMap<Integer, Baum> baumKataster = getBaumKataster();
        HashMap<Integer, ArrayList<Float>> repairables = new HashMap<>();
        
        for (Integer key : baumKataster.keySet())
        {
            repairables.put(key, baumKataster.get(key).getMetrik().getRepairables());
        }
        
        return repairables;
    }
    
    
    @Override
    public void setRepairables(HashMap<Integer, ArrayList<Float>> reparierte)
    {
        HashMap<Integer, Baum> baumKataster = getBaumKataster();
        
        for (Integer key : baumKataster.keySet())
        {
            Baum baum = baumKataster.get(key);
            baum.setRepairables(reparierte.get(key));
            baumKataster.put(key, baum);
        }
        
    }
    
    
    //endregion
    
    
}
