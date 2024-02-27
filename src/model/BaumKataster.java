package model;

import resources.Constants;
import resources.Messages;
import resources.Strings;
import utility.iRepairableStatistic;

import java.util.*;

public class BaumKataster implements iRepairableStatistic
{
    
    
    private final ArrayList<Float> permissableMaxima;

    
    private HashMap<Integer, Tree> baumHashMap;
    
    
    private HashSet<Integer> deletedDataSetKeys = new HashSet<>();
    
    
    private HashSet<Integer> editedDataSetKeys = new HashSet<>();
    
    
    public BaumKataster(ArrayList<CSVRecord> cSV, ArrayList<Float> permissableMaxima)
    {
        this.permissableMaxima = permissableMaxima;
        
        
        HashMap<Integer, Tree> baeume = new HashMap<>();
        
        for (CSVRecord cSVRecord : cSV)
        {
            ArrayList<String> record = cSVRecord.getRecord();
            try
            {
                //keep first remove next
                baeume.putIfAbsent(Integer.parseInt(record.getFirst()), Tree.create(record.subList(Constants.EINS, Constants.ZWOELF)));
            }
            catch (Exception e)
            {
            }
        }
        
        
        setBaumHashMap(baeume);
    }
  
    
    
    public BaumKataster(List<Map.Entry<Integer, Tree>> list, ArrayList<Float> permissableMaxima)
    {
        this.permissableMaxima = permissableMaxima;
        
        
        HashMap<Integer, Tree> baumHashMap = new HashMap<>();
        
        for (Map.Entry<Integer, Tree> entry : list)
        {
            baumHashMap.put(entry.getKey(), entry.getValue());
        }
        
        
        setBaumHashMap(baumHashMap);
    }
    
    
    public BaumKataster(HashMap<Integer, Tree> baeume, ArrayList<Float> permissableMaxima)
    {
        setBaumHashMap(baeume);
        this.permissableMaxima = permissableMaxima;
    }
    
    
    
    public HashMap<Integer, Tree> getBaumHashMap()
    {
        return this.baumHashMap;
    }
    
    
    private void setBaumHashMap(HashMap<Integer, Tree> baeumeMap)
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
        
        HashMap<Integer, Tree> baumMap = getBaumHashMap();
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
        HashMap<Integer, Tree> baumKataster = getBaumHashMap();
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
        HashMap<Integer, Tree> baumMap = getBaumHashMap();
        HashSet<Integer> editedDataSetKeys = getEditedDataSetKeys();
        
        for (Integer key : repairedFloats.keySet())
        {
            Tree tree = baumMap.get(key);
            ArrayList<Float> repairables = repairedFloats.get(key);
            
            if(repairables.equals(tree.getRepairables())) continue;
            
            editedDataSetKeys.add(key);
            tree.setRepairables(repairedFloats.get(key));
            baumMap.put(key, tree);
        }
        
        setEditedDataSetKeys(editedDataSetKeys);
        setBaumHashMap(baumMap);
    }
    
    
    @Override
    public HashSet<Integer> getDeletableDataSetKeys()
    {
        HashSet<Integer> deletableDataSets = new HashSet<>();
        HashMap<Integer, Tree> baumMap = getBaumHashMap();
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
        HashMap<Integer, Tree> dataSets = getBaumHashMap();
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
    public float getUnknown()
    {
        return iRepairableStatistic.super.getUnknown();
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
        HashMap<Integer, Tree> newBaumHashMap = new HashMap<>();
        HashMap<Integer, Tree> baumHashMap =getBaumHashMap();
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
