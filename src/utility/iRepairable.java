package utility;

import java.util.ArrayList;

public interface iRepairable extends Cloneable
{
    
    
    public void setRepairables(ArrayList<Float> reparierte);
    
    
    public ArrayList<Float> getRepairables();
    
    
    public boolean isEmpty();
    
    
}
