package utility;

import java.util.ArrayList;

public interface iRepairable
{
    
    
    public void setRepairables(ArrayList<Float> reparierte);
    
    
    public ArrayList<Float> getRepairables();
    
    
    //todo divide by 10 until it is correct
    public ArrayList<Float> getPermissableMaxima();
    
    
}
