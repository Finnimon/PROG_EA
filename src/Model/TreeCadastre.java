package Model;

import Resources.Messages;
import Utility.DataRepair.iRepairableStatistic;
import Utility.ElementFaultyException;
import org.jetbrains.annotations.NotNull;

import java.util.*;


/**
 * @author Finn Lindig
 * @Summary: The {@link TreeCadastre} is a collection of {@link Tree} mapped to their respective objectIds. It implements {@link iRepairableStatistic} and offers various GetSet methods as well as methods for keeping track of modifications.
 * @Since: 26.02.2024
 */
public class TreeCadastre implements iRepairableStatistic
{
    
    
    /**
     * @Summary: {@link #treeHashMap} is the {@link Tree}s mapped to their respective objectIds.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private HashMap<Integer, Tree> treeHashMap;
    
    
    /**
     * @Summary: A hashset of all Integers that belonged to the deleted {@link Tree}s as keys within {@link #treeHashMap}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private HashSet<Integer> deletedDataSetKeys;
    
    
    /**
     * @Summary: A hashset of all Integers that belong to the edited {@link Tree}s as keys within {@link #treeHashMap}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private HashSet<Integer> editedDataSetKeys;
    
    
    /**
     * @param cSV a CSV file in the RFC4180 format parsed into an {@link ArrayList} of {@link CSVRecord}s.
     * @Precondition: None.
     * @Postcondition: If a tree cannot be successfully instantiated from a {@link CSVRecord}, it will be skipped. Therefore, if the param cSV is not containing valid {@link CSVRecord}s, the {@link #treeHashMap} may be empty. All intact trees are put into {@link #treeHashMap}.
     * @Summary: Constructs a {@link TreeCadastre} from an {@link ArrayList} of {@link CSVRecord}s and initializes all variables as well as parsing all {@link CSVRecord}s in the param cSV into {@link Tree}s into {@link #treeHashMap}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public TreeCadastre(@NotNull ArrayList<CSVRecord> cSV)
    {
        HashMap<Integer, Tree> baeume = new HashMap<>();
        
        for (CSVRecord cSVRecord : cSV)
        {
            ArrayList<String> record = cSVRecord.getRecord();
            try
            {
                baeume.putIfAbsent(Integer.parseInt(record.getFirst()), Tree.create(cSVRecord));
            }
            catch (NumberFormatException | ElementFaultyException e)
            {
                continue;
            }
        }
        
        initalize();
        setTreeHashMap(baeume);
    }
    
    
    /**
     * @param treeHashMap value for {@link #treeHashMap}.
     * @Precondition: None.
     * @Postcondition: Sets {@link #treeHashMap} to the param treeHashMap. All other variables will also be initialized.
     * @Summary: Standard constructor that sets {@link #treeHashMap} to the param treeHashMap and initializes all variables.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public TreeCadastre(HashMap<Integer, Tree> treeHashMap)
    {
        initalize();
        setTreeHashMap(treeHashMap);
    }
    
    
    /**
     * @Summary: Initializes {@link #deletedDataSetKeys} and {@link #editedDataSetKeys}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void initalize()
    {
        this.deletedDataSetKeys = new HashSet<>();
        this.editedDataSetKeys = new HashSet<>();
    }
    
    
    /**
     * @return {@link #treeHashMap}.
     * @Summary: Getter for {@link #treeHashMap}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public HashMap<Integer, Tree> getTreeHashMap()
    {
        return this.treeHashMap;
    }
    
    
    /**
     * @Summary: Setter for {@link #treeHashMap}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void setTreeHashMap(HashMap<Integer, Tree> baeumeMap)
    {
        this.treeHashMap = baeumeMap;
    }
    
    
    /**
     * @param key the key of the entry in {@link #treeHashMap} to be returned.
     * @return A {@link String} representation of the entry with key and value of {@link Tree} in {@link #treeHashMap} under key.
     * @Precondition: {@link #treeHashMap}.containsKey(key) is true and key is not mapped to null.
     * @Postcondition: A string representation of the entry with key and value of {@link Tree} in {@link #treeHashMap} under key is returned without throwing any exceptions.
     * @Summary: Returns a {@link String} representation of the entry with key and value of {@link Tree} in {@link #treeHashMap} under key.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public String entryToString(Integer key)
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.OBJEKT_ID);
        stringBuilder.append(key);
        stringBuilder.append(getTreeHashMap().get(key).toString());
        
        
        return stringBuilder.toString();
    }
    
    
    /**
     * @return a string representation of this {@link TreeCadastre} and all of its entries.
     * @Precondition: The {@link #treeHashMap} does not contain any keys mapped to null.
     * @Postcondition: A string representation of this {@link TreeCadastre} and all of its entries is returned without throwing any exceptions.
     * @Summary: Returns a string representation of this {@link TreeCadastre} and all of its entries.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        
        HashMap<Integer, Tree> baumMap = getTreeHashMap();
        List<Integer> sortedKeyList = baumMap.keySet().stream().toList();
        Collections.sort(sortedKeyList);
        for (Integer key : sortedKeyList)
        {
            stringbuilder.append(entryToString(key));
        }
        
        
        return stringbuilder.toString();
    }
    
    
    /**
     * @return {@link iRepairableStatistic#getPermissibleMaxima()}.
     * @Summary: Returns {@link iRepairableStatistic#getPermissibleMaxima()}. Implementation of {@link iRepairableStatistic#getPermissibleMaxima()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public ArrayList<Float> getPermissibleMaxima()
    {
        return iRepairableStatistic.super.getPermissibleMaxima();
    }
    
    
    /**
     * @return {@link Metric#getRepairables()}. for all {@link Tree} in {@link #treeHashMap} mapped to their {@link Tree}s respective keys.
     * @Precondition: No {@link Tree} in {@link #treeHashMap} is null.
     * @Postcondition: {@link Metric#getRepairables()}. for all {@link Tree} in {@link #treeHashMap} mapped to their {@link Tree}s respective keys is returned without throwing any exceptions.
     * @Summary: Implements {@link iRepairableStatistic#getRepairableFloats()};
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
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
    
    
    /**
     * @param repairedFloats New values for {@link Tree}s in {@link #treeHashMap} mapped to their {@link Tree}s respective keys.
     * @Summary: Implements {@link iRepairableStatistic#getRepairableFloats()};
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public void setRepairableFloats(HashMap<Integer, ArrayList<Float>> repairedFloats)
    {
        HashMap<Integer, Tree> baumMap = getTreeHashMap();
        HashSet<Integer> editedDataSetKeys = getEditedDataSetKeys();
        
        for (Integer key : repairedFloats.keySet())
        {
            Tree tree = baumMap.get(key);
            ArrayList<Float> repairables = repairedFloats.get(key);
            
            if (repairables.equals(tree.getRepairables()))
            {
                continue;
            }
            
            editedDataSetKeys.add(key);
            tree.setRepairables(repairedFloats.get(key));
            baumMap.put(key, tree);
        }
        
        setEditedDataSetKeys(editedDataSetKeys);
        setTreeHashMap(baumMap);
    }
    
    
    /**
     * @return A keySet of all {@link Tree}s in {@link #treeHashMap} for which {@link Tree#isEmpty()} is true.
     * @Summary: Implements {@link iRepairableStatistic#getDeletableDataSetKeys()};
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
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
    
    
    /**
     * @param deletableKeySet A keySet of all {@link Tree}s that will be removed from {@link #treeHashMap}.
     * @Precondition: {@link #treeHashMap} is not null. All keys in param deletableKeySet are in {@link #treeHashMap}.
     * @Postcondition: All entries in {@link #treeHashMap} with keys in param deletableKeySet are removed from {@link #treeHashMap}. The {@link #deletedDataSetKeys} is updated correctly.
     * @Summary: Implements {@link iRepairableStatistic#deleteDataSetsOfKeySet(HashSet)};
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
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
    
    
    /**
     * @return {@link iRepairableStatistic#UNKNOWN}
     * @Summary: Implements {@link iRepairableStatistic#UNKNOWN}. Returns the placeholder value used for unknown variables in this {@link iRepairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public float UNKNOWN()
    {
        return iRepairableStatistic.super.UNKNOWN();
    }
    
    
    /**
     * @return {@link #editedDataSetKeys}
     * @Summary: Getter for {@link #editedDataSetKeys}. Implementation of {@link iRepairableStatistic#getEditedDataSetKeys()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public HashSet<Integer> getEditedDataSetKeys()
    {
        return this.editedDataSetKeys;
    }
    
    
    /**
     * @param editedDataSetKeys The keySet to set {@link #editedDataSetKeys} to.
     * @Summary: Setter for {@link #editedDataSetKeys}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setEditedDataSetKeys(HashSet<Integer> editedDataSetKeys)
    {
        this.editedDataSetKeys = editedDataSetKeys;
    }
    
    
    /**
     * @return {@link #deletedDataSetKeys}
     * @Summary: Getter for {@link #deletedDataSetKeys}. Implementation of {@link iRepairableStatistic#getDeletedDataSetKeys()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public HashSet<Integer> getDeletedDataSetKeys()
    {
        return this.deletedDataSetKeys;
    }
    
    /**
     * @param deletedDataSetKeys The keySet to set {@link #deletedDataSetKeys} to.
     * @Summary: Setter for {@link #deletedDataSetKeys}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setDeletedDataSetKeys(HashSet<Integer> deletedDataSetKeys)
    {
        this.deletedDataSetKeys = deletedDataSetKeys;
    }
    
    
    /**
     * @return deep copy of this {@link TreeCadastre}
     * @Precondition: None
     * @Postcondition: The returned {@link TreeCadastre} is a deep copy of this {@link TreeCadastre}.
     * @Summary: Clones this {@link TreeCadastre}. The return is a deep copy.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
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
        
        TreeCadastre newTreeCadastre = new TreeCadastre(newBaumHashMap);
        
        newTreeCadastre.setDeletedDataSetKeys(newDeletedDataSetKeys);
        newTreeCadastre.setEditedDataSetKeys(newEditedDataSetKeys);
        
        return newTreeCadastre;
    }
}
