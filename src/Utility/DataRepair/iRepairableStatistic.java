package Utility.DataRepair;

import control.Main;
import Resources.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface iRepairableStatistic extends Cloneable
{
    
    
    
    public default float UNKNOWN()
    {
        return Constants.UNBEKANNT;
    }
    
    
    public HashMap<Integer, ArrayList<Float>> getRepairableFloats();
    
    
    public default ArrayList<Float> getPermissibleMaxima()
    {
        return Main.ARGUMENTS;
    };
    
    
    public void setRepairableFloats(HashMap<Integer, ArrayList<Float>> reparierte);
    
    
    public HashSet<Integer> getDeletableDataSetKeys();
    

    public void deleteDataSetsOfKeySet(HashSet<Integer> deletableKeySet);
    
    
    public HashSet<Integer> getEditedDataSetKeys();
    
    
    public HashSet<Integer> getDeletedDataSetKeys();
    
    
    public iRepairableStatistic clone();
    
    
}