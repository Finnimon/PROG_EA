package utility;

import java.util.ArrayList;
import java.util.HashMap;

public interface iRepairableStatistic
{
    public HashMap<Integer, ArrayList<Float>> getRepairableFloats();
    
    
    public void put(Integer key, iRepairable repairableObject);
    
    
    public ArrayList<Float> getPermissableMaxima();
    
    
    public void setRepairables(HashMap<Integer, ArrayList<Float>> reparierte);
    
}
