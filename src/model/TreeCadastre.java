package model;

import resources.Constants;
import resources.Messages;
import resources.Strings;
import utility.iRepairableStatistic;

import java.util.*;

public class TreeCadastre implements iRepairableStatistic
{
    
    
    private final ArrayList<Float> permissibleMaxima;

    
    private HashMap<Integer, Tree> treeHashMap;
    
    
    private HashSet<Integer> deletedDataSetKeys = new HashSet<>();
    
    
    private HashSet<Integer> editedDataSetKeys = new HashSet<>();
    
    
    public TreeCadastre(ArrayList<CSVRecord> cSV, ArrayList<Float> permissibleMaxima)
    {
        this.permissibleMaxima = permissibleMaxima;
        
        
        HashMap<Integer, Tree> baeume = new HashMap<>();
        
        for (CSVRecord cSVRecord : cSV)
        {
            ArrayList<String> record = cSVRecord.getRecord();
            try
            {
                //keep first remove next
                baeume.putIfAbsent(Integer.parseInt(record.getFirst()), Tree.create(record.subList(Constants.EINS, Constants.CSV_RECORD_LENGTH)));
            }
            catch (Exception e)
            {
            }
        }
        
        
        setTreeHashMap(baeume);
    }
  
    
    
    public TreeCadastre(List<Map.Entry<Integer, Tree>> list, ArrayList<Float> permissibleMaxima)
    {
        this.permissibleMaxima = permissibleMaxima;
        
        
        HashMap<Integer, Tree> baumHashMap = new HashMap<>();
        
        for (Map.Entry<Integer, Tree> entry : list)
        {
            baumHashMap.put(entry.getKey(), entry.getValue());
        }
        
        
        setTreeHashMap(baumHashMap);
    }
    
    
    public TreeCadastre(HashMap<Integer, Tree> baeume, ArrayList<Float> permissibleMaxima)
    {
        setTreeHashMap(baeume);
        this.permissibleMaxima = permissibleMaxima;
    }
    
    
    
    public HashMap<Integer, Tree> getTreeHashMap()
    {
        return this.treeHashMap;
    }
    
    
    private void setTreeHashMap(HashMap<Integer, Tree> baeumeMap)
    {
        this.treeHashMap = baeumeMap;
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
        stringBuilder.append(getTreeHashMap().get(key).toString());
        
        
        return stringBuilder.toString();
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        
        HashMap<Integer, Tree> baumMap = getTreeHashMap();
        List<Integer> sortedKeyList=baumMap.keySet().stream().toList();
        Collections.sort(sortedKeyList);
        for (Integer key : sortedKeyList)
        {
            stringbuilder.append(entryToString(key));
        }
        
        
        return stringbuilder.toString();
    }
    
    
    @Override
    public ArrayList<Float> getPermissibleMaxima()
    {
        return this.permissibleMaxima;
    }
    
    
    @Override
    public HashMap<Integer, ArrayList<Float>> getRepairableFloats()
    {
        HashMap<Integer, Tree> baumKataster = getTreeHashMap();
        HashMap<Integer, ArrayList<Float>> repairables = new HashMap<>();
        
        for (Integer key : baumKataster.keySet())
        {
            repairables.put(key, baumKataster.get(key).getMetric().getRepairables());
        }
        
        return repairables;
    }
    
    
    @Override
    public void setRepairableFloats(HashMap<Integer, ArrayList<Float>> repairedFloats)
    {
        HashMap<Integer, Tree> baumMap = getTreeHashMap();
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
        setTreeHashMap(baumMap);
    }
    
    
    @Override
    public HashSet<Integer> getDeletableDataSetKeys()
    {
        HashSet<Integer> deletableDataSets = new HashSet<>();
        HashMap<Integer, Tree> baumMap = getTreeHashMap();
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
        HashMap<Integer, Tree> dataSets = getTreeHashMap();
        HashSet<Integer> deletedDataSets = getDeletedDataSetKeys();
        
        for (Integer key : deletableKeySet)
        {
            dataSets.remove(key);
            deletedDataSets.add(key);
        }
        
        
        setDeletedDataSetKeys(deletedDataSets);
        setTreeHashMap(dataSets);
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
    public TreeCadastre clone()
    {
        HashMap<Integer, Tree> newBaumHashMap = new HashMap<>();
        HashMap<Integer, Tree> baumHashMap = getTreeHashMap();
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
        
        TreeCadastre newTreeCadastre = new TreeCadastre(newBaumHashMap, getPermissibleMaxima());
    
        newTreeCadastre.setDeletedDataSetKeys(newDeletedDataSetKeys);
        newTreeCadastre.setEditedDataSetKeys(newEditedDataSetKeys);
        
        return newTreeCadastre;
    }
}
