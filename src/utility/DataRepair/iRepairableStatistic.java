package utility.DataRepair;

import control.Main;
import resources.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * @Summary: The {@link iRepairableStatistic} interface offers methods for the interactions with a {@link iRepairableStatistic}. Implementing this interface will make it possible to repair a {@link iRepairableStatistic} in a shallow {@link StatisticalDataRepairCenter#shallowRepair()} and deep way {@link StatisticalDataRepairCenter#deepRepair()}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public interface iRepairableStatistic extends Cloneable
{
    
    /**
     * @return {@link Constants#UNBEKANNT}
     * @Summary: Returns the placeholder value used to indicate unknown values.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    default float UNKNOWN()
    {
        return Constants.UNBEKANNT;
    }
    
    /**
     * @return All the repairable values within a {@link iRepairableStatistic} as mapped in the {@link iRepairableStatistic} itself.
     * @Summary: The data in this {@link iRepairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    HashMap<Integer, ArrayList<Float>> getRepairableFloats();
    
    
    /**
     * @return the permissable maxima of the {@link iRepairableStatistic}.
     * @Summary: returns the permissable maxima of the {@link iRepairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    default ArrayList<Float> getPermissibleMaxima()
    {
        return Main.ARGUMENTS;
    };
    
    
    /**
     * @param repairedFloats the repaired values as mapped in the {@link iRepairableStatistic} itself.
     * @Summary: Setter the repairable values within a {@link iRepairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    void setRepairableFloats(HashMap<Integer, ArrayList<Float>> repairedFloats);
    
    
    /**
     * @return all the keys of the {@link iRepairableStatistic} that can be deleted.
     * @Summary: Returns all the keys of the {@link iRepairableStatistic} that can be deleted without a relevant loss of data.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    HashSet<Integer> getDeletableDataSetKeys();
    
    
    /**
     * @param deletableKeySet all the keys of the {@link iRepairableStatistic} that can be deleted.
     * @Summary: Deletes all the datasets in the {@link iRepairableStatistic} that are mapped to the keys in the param {@link HashSet}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    void deleteDataSetsOfKeySet(HashSet<Integer> deletableKeySet);
    
    
    /**
     * @return all the keys of the {@link iRepairableStatistic} that were edited.
     * @Summary: Returns all the keys whose values were edited in the {@link iRepairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    HashSet<Integer> getEditedDataSetKeys();
    
    
    /**
     * @return all the keys of the {@link iRepairableStatistic} that were deleted.
     * @Summary: Returns all the keys whose values were deleted in the {@link iRepairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    HashSet<Integer> getDeletedDataSetKeys();
    
    
    /**
     * @return a deep copy of this {@link iRepairableStatistic}.
     * @Summary: Returns a deep copy of this {@link iRepairableStatistic}. This may be useful when the {@link iRepairableStatistic} is in multiple different states of repairedness.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    iRepairableStatistic clone();
    
    
}