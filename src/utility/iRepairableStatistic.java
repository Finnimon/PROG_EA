package utility;

import java.util.ArrayList;
import java.util.HashMap;

public interface iRepairableStatistic
{
    public HashMap<Integer, ArrayList<Float>> getRepairables();
    public void setRepairables(HashMap<Integer, ArrayList<Float>> reparierte);
    
}
