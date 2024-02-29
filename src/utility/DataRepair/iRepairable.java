package utility.DataRepair;

import java.util.ArrayList;


/**
 * @Summary: This interface offers methods that can be used to repair a {@link iRepairable} in case it has unknown values or values that leave allowed bounds.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
 public interface iRepairable extends Cloneable
{
    
    
    /**
     * @param repaireds the new values to which the {@link iRepairable} should be set.
     * @Summary: Sets the values of the {@link iRepairable}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
     void setRepairables(ArrayList<Float> repaireds);
    
    
    /**
     * @return the repairable values of an {@link iRepairable}.
     * @Summary: Gets the repairable values of an {@link iRepairable}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
     ArrayList<Float> getRepairables();
    
    
    /**
     * @return whether the {@link iRepairable} is empty or in some circumstances by extension deletable.
     * @Summary: Checks whether the {@link iRepairable} is empty or in some circumstances by extension deletable.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
     boolean isEmpty();
    
    
}
