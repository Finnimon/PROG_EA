package utility;

import resources.Konstanten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface iRepairableStatistic extends Cloneable
{
    
    
    public default float getUnknown()
    {
        return Konstanten.UNBEKANNT;
    }
    
    
    public HashMap<Integer, ArrayList<Float>> getRepairableFloats();
    
    
    public ArrayList<Float> getPermissableMaxima();
    
    
    public void setRepairableFloats(HashMap<Integer, ArrayList<Float>> reparierte);
    
    
    public HashSet<Integer> getDeletableDataSetKeys();
    

    public void deleteDataSetsOfKeySet(HashSet<Integer> deletableKeySet);
    
    
    public HashSet<Integer> getEditedDataSetKeys();
    
    
    public HashSet<Integer> getDeletedDataSetKeys();
    
    
    public iRepairableStatistic clone();
    
    
}