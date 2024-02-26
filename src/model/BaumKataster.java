package model;

import resources.Konstanten;
import resources.Messages;
import resources.Strings;
import utility.iRepairableStatistic;

import java.util.*;

public class BaumKataster implements iRepairableStatistic
{
    
    
    private final ArrayList<Float> permissableMaxima;

    
    private HashMap<Integer, Baum> baumHashMap;
    
    
    private HashSet<Integer> deletedDataSetKeys = new HashSet<>();
    
    
    private HashSet<Integer> editedDataSetKeys = new HashSet<>();
    
    
    public BaumKataster(ArrayList<CSVRecord> cSV, ArrayList<Float> permissableMaxima)
    {
        this.permissableMaxima = permissableMaxima;
        
        
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
        
        
        setBaumHashMap(baeume);
    }
  
    
    
    public BaumKataster(List<Map.Entry<Integer, Baum>> list, ArrayList<Float> permissableMaxima)
    {
        this.permissableMaxima = permissableMaxima;
        
        
        HashMap<Integer, Baum> baumHashMap = new HashMap<>();
        
        for (Map.Entry<Integer, Baum> entry : list)
        {
            baumHashMap.put(entry.getKey(), entry.getValue());
        }
        
        
        setBaumHashMap(baumHashMap);
    }
    
    
    public BaumKataster(HashMap<Integer, Baum> baeume, ArrayList<Float> permissableMaxima)
    {
        setBaumHashMap(baeume);
        this.permissableMaxima = permissableMaxima;
    }
    
    
    
    public HashMap<Integer, Baum> getBaumHashMap()
    {
        return this.baumHashMap;
    }
    
    
    private void setBaumHashMap(HashMap<Integer, Baum> baeumeMap)
    {
        this.baumHashMap = baeumeMap;
    }
    
    
    public void setDeletedDataSetKeys(HashSet<Integer> deletedDataSetKeys)
    {
        this.deletedDataSetKeys = deletedDataSetKeys;
    }
    
    
    public void setEditedDataSetKeys(HashSet<Integer> editedDataSetKeys)
    {
        this.editedDataSetKeys = editedDataSetKeys;
    }
    
    
    public String entryToString(Integer key)
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.OBJEKT_ID);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(key);
        stringBuilder.append(getBaumHashMap().get(key).toString());
        
        
        return stringBuilder.toString();
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        
        HashMap<Integer, Baum> baumMap = getBaumHashMap();
        List<Integer> sortedKeyList=baumMap.keySet().stream().toList();
        Collections.sort(sortedKeyList);
        for (Integer key : sortedKeyList)
        {
            stringbuilder.append(entryToString(key));
        }
        
        
        return stringbuilder.toString();
    }
    
    
    @Override
    public ArrayList<Float> getPermissableMaxima()
    {
        return this.permissableMaxima;
    }
    
    
    @Override
    public HashMap<Integer, ArrayList<Float>> getRepairableFloats()
    {
        HashMap<Integer, Baum> baumKataster = getBaumHashMap();
        HashMap<Integer, ArrayList<Float>> repairables = new HashMap<>();
        
        for (Integer key : baumKataster.keySet())
        {
            repairables.put(key, baumKataster.get(key).getMetrik().getRepairables());
        }
        
        return repairables;
    }
    
    
    @Override
    public void setRepairableFloats(HashMap<Integer, ArrayList<Float>> repairedFloats)
    {
        HashMap<Integer, Baum> baumMap = getBaumHashMap();
        HashSet<Integer> editedDataSetKeys = getEditedDataSetKeys();
        
        for (Integer key : repairedFloats.keySet())
        {
            Baum baum = baumMap.get(key);
            ArrayList<Float> repairables = repairedFloats.get(key);
            
            if(repairables.equals(baum.getRepairables())) continue;
            
            editedDataSetKeys.add(key);
            baum.setRepairables(repairedFloats.get(key));
            baumMap.put(key, baum);
        }
        
        setEditedDataSetKeys(editedDataSetKeys);
        setBaumHashMap(baumMap);
    }
    
    
    @Override
    public HashSet<Integer> getDeletableDataSetKeys()
    {
        HashSet<Integer> deletableDataSets = new HashSet<>();
        HashMap<Integer, Baum> baumMap = getBaumHashMap();
        for (Integer key : baumMap.keySet())
        {
            if (baumMap.get(key).isEmpty())
            {
                deletableDataSets.add(key);
            }
        }
        
        
        return deletableDataSets;
    }
    
    
    @Override
    public void deleteDataSetsOfKeySet(HashSet<Integer> deletableKeySet)
    {
        HashMap<Integer, Baum> dataSets = getBaumHashMap();
        HashSet<Integer> deletedDataSets = getDeletedDataSetKeys();
        
        for (Integer key : deletableKeySet)
        {
            dataSets.remove(key);
            deletedDataSets.add(key);
        }
        
        
        setDeletedDataSetKeys(deletedDataSets);
        setBaumHashMap(dataSets);
    }
    
    
    @Override
    public float getUNKNOWN()
    {
        return iRepairableStatistic.super.getUNKNOWN();
    }
    
    
    @Override
    public HashSet<Integer> getEditedDataSetKeys()
    {
        return this.editedDataSetKeys;
    }
    
    
    @Override
    public HashSet<Integer> getDeletedDataSetKeys()
    {
        return this.deletedDataSetKeys;
    }
    
    
    @Override
    public BaumKataster clone()
    {
        HashMap<Integer,Baum> newBaumHashMap = new HashMap<>();
        HashMap<Integer,Baum> baumHashMap =getBaumHashMap();
        for (Integer key : baumHashMap.keySet())
        {
            newBaumHashMap.put(key, baumHashMap.get(key).clone());
        }
        
        HashSet<Integer> newDeletedDataSetKeys = new HashSet<>();
        HashSet<Integer> deletedDataSetKeys = getDeletedDataSetKeys();
        newDeletedDataSetKeys.addAll(deletedDataSetKeys);
        
        HashSet<Integer> newEditedDataSetKeys = new HashSet<>();
        HashSet<Integer> editedDataSetKeys = getEditedDataSetKeys();
        newEditedDataSetKeys.addAll(editedDataSetKeys);
        
        BaumKataster newBaumKataster = new BaumKataster(newBaumHashMap, getPermissableMaxima());
    
        newBaumKataster.setDeletedDataSetKeys(newDeletedDataSetKeys);
        newBaumKataster.setEditedDataSetKeys(newEditedDataSetKeys);
        
        return newBaumKataster;
    }
}
