package control;

import logic.StatisticalDataRepairLogic;
import org.jetbrains.annotations.NotNull;
import utility.iRepairableStatistic;
import java.util.ArrayList;


/**
 * @Summary: This class offers instance methods to repair a {@link iRepairableStatistic} in a shallow {@link #shallowRepair()} and deep way {@link #deepRepair()}.
 * The logic is based on {@link StatisticalDataRepairLogic}.
 * If a {@link iRepairableStatistic} has too few entries it will likely yield bad results.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class StatisticalDataRepairCenter
{
    
    
    /**
     * @Summary: The index of the regression basis.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int INDEX_REGRESSION_BASIS = 3;
    
    
    /**
     * @Summary: The {@link iRepairableStatistic} to repair.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private iRepairableStatistic repairableStatistic;
    
    
    /**
     * @Summary: Whether the method {@link #shallowRepair()} has been called regarding this {@link StatisticalDataRepairCenter}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private boolean isShallowRepaired;
    
    
    /**
     * @Summary: Whether the method {@link #deepRepair()} has been called regarding this {@link StatisticalDataRepairCenter}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private boolean isDeepRepaired;
    
    
    /**
     * @param repairableStatistic The {@link iRepairableStatistic} to repair.
     * @Summary: Constructs and initializes this {@link StatisticalDataRepairCenter}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public StatisticalDataRepairCenter(@NotNull iRepairableStatistic repairableStatistic)
    {
        this.repairableStatistic = repairableStatistic;
        initialize();
    }
    
    
    /**
     * @Precondition: {@link #shallowRepair()} and {@link #deepRepair()} have not been called yet. This method is not used outside the constructor.
     * @Postcondition: Insures proper functionality of this {@link StatisticalDataRepairCenter} and it's methods.
     * @Summary: Initializes this {@link StatisticalDataRepairCenter} to the state of {@link #shallowRepair()} and {@link #deepRepair()} not having been called yet.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void initialize()
    {
        setIsShallowRepaired(false);
        setIsDeepRepaired(false);
    }
    
    
    /**
     * @return {@link iRepairableStatistic} that has been repaired using only {@link #shallowRepair()}.
     * @Precondition: {@link #deepRepair()} has not been called yet.
     * @Postcondition: Will return a {@link iRepairableStatistic} that has only been repaired using {@link #shallowRepair()}.
     * @Summary: If {@link #getIsShallowRepaired()} is false, {@link #shallowRepair()} will be called.  Then {@link #getRepairableStatistic()} is returned. If {@link #getIsDeepRepaired()} is true, a RuntimeException will be thrown.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public iRepairableStatistic getShallowRepairedStatistic()
    {
        if (!getIsShallowRepaired())
        {
            shallowRepair();
        }
        else if (getIsDeepRepaired())
        {
            throw new RuntimeException();//todo message
        }
        
        
        return getRepairableStatistic();
    }
    
    
    /**
     * @return {@link iRepairableStatistic} that has been repaired using only {@link #shallowRepair()}.
     * @Summary: If {@link #getIsShallowRepaired()} is false, {@link #shallowRepair()} will be called. If {@link #getIsDeepRepaired()} is false, {@link #deepRepair()} will be called. Then {@link #getRepairableStatistic()} is returned.
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * @Postcondition: Will return a {@link iRepairableStatistic} that has been repaired using {@link #shallowRepair()} and then {@link #deepRepair()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public iRepairableStatistic getDeepRepairedStatistic()
    {
        if (!getIsDeepRepaired())
        {
            deepRepair();
        }
        
        
        return getRepairableStatistic();
    }
    
    
    /**
     * @Summary: Getter for {@link #INDEX_REGRESSION_BASIS}.
     * @return {@link #INDEX_REGRESSION_BASIS}
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * @Postcondition: {@link #getINDEX_REGRESSION_BASIS()}=={@link #INDEX_REGRESSION_BASIS}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private int getINDEX_REGRESSION_BASIS()
    {
        return INDEX_REGRESSION_BASIS;
    }
    
    
    /**
     * @Summary: Getter for {@link #repairableStatistic}.
     * @return {@link #repairableStatistic}
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * @Postcondition: The attribute {@link #repairableStatistic} is returned.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private iRepairableStatistic getRepairableStatistic()
    {
        return repairableStatistic;
    }
    
    
    /**
     * @Summary: Setter for {@link #repairableStatistic}.
     * @Precondition: This method is only called by {@link #shallowRepair()} and {@link #deepRepair()}. This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * The Preconditions of {@link #setIsShallowRepaired(boolean)} and {@link #setIsDeepRepaired(boolean)} are met.
     * @Postcondition: The attribute {@link #repairableStatistic} is set and {@link #getIsShallowRepaired()} and {@link #getIsDeepRepaired()} are always reflecting the true state of {@link #repairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     * @param repairableStatistic The {@link iRepairableStatistic} that has experienced a change in {@link StatisticalDataRepairCenter}.
     */
    private void setRepairableStatistic(@NotNull iRepairableStatistic repairableStatistic)
    {
        if (!getIsShallowRepaired())
        {
            setIsShallowRepaired(true);
        }
        else
        {
            setIsDeepRepaired(true);
        }
        
        this.repairableStatistic = repairableStatistic;
    }
    
    
    /**
     * @return {@link #isDeepRepaired}
     * @Summary: Getter for {@link #isDeepRepaired}.
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * The Preconditions of {@link #setRepairableStatistic(iRepairableStatistic)} are met.
     * @Postcondition: Reflects weither {@link #repairableStatistic} has been repaired using {@link #deepRepair()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private boolean getIsDeepRepaired()
    {
        return isDeepRepaired;
    }
    
    
    /**
     * @Summary: Getter for {@link #isDeepRepaired}.
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * This Method is only called by {@link #setRepairableStatistic(iRepairableStatistic)} and {@link #initialize()}.
     * @Postcondition: {@link #getIsDeepRepaired()} reflects weither {@link #repairableStatistic} has been repaired using  {@link #deepRepair()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     * @param repaired Weither {@link #repairableStatistic} has been repaired using {@link #deepRepair()}.
     */
    private void setIsDeepRepaired(boolean repaired)
    {
        isDeepRepaired = repaired;
    }
    
    
    /**
     * @return {@link #isShallowRepaired}
     * @Summary: Getter for {@link #isShallowRepaired}.
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * The Preconditions of {@link #setRepairableStatistic(iRepairableStatistic)} are met.
     * @Postcondition: Reflects weither {@link #repairableStatistic} has been repaired using {@link #shallowRepair()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private boolean getIsShallowRepaired()
    {
        return isShallowRepaired;
    }
    
    
    /**
     * @Summary: Getter for {@link #isShallowRepaired}.
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly.
     * This Method is only called by {@link #setRepairableStatistic(iRepairableStatistic)} and {@link #initialize()}.
     * @Postcondition: {@link #getIsShallowRepaired()} reflects weither {@link #repairableStatistic} has been repaired using {@link #shallowRepair()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     * @param shallowRepaired Weither {@link #repairableStatistic} has been repaired using {@link #shallowRepair()}.
     */
    private void setIsShallowRepaired(boolean shallowRepaired)
    {
        isShallowRepaired = shallowRepaired;
    }
    
    
    /**
     * @Summary: Deletes all DataSets in the KeySet of {@link iRepairableStatistic#getDeletableDataSetKeys()}.
     * @Precondition: The Interface {@link iRepairableStatistic} has been implemented correctly for this {@link #repairableStatistic}.
     * @Postcondition: All DataSets in the KeySet of {@link iRepairableStatistic#getDeletableDataSetKeys()} have been deleted from {@link iRepairableStatistic}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void deleteDeletableDataSets()
    {
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        repairableStatistic.deleteDataSetsOfKeySet(repairableStatistic.getDeletableDataSetKeys());
    }
    
    
    /**
     * @Summary: Deletes all DataSets in the KeySet of {@link iRepairableStatistic#getDeletableDataSetKeys()}. Applies {@link StatisticalDataRepairLogic#repairUpperExtremes(iRepairableStatistic)} to {@link #repairableStatistic}.
     * This results in {@link #repairableStatistic} being presentable and suitable for further queries regarding extremes, whilst not being particularly statistically robust.
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly. The Preconditions of {@link #setRepairableStatistic(iRepairableStatistic)} are met.
     * @Postcondition: Deletable DataSets have been deleted from {@link #repairableStatistic} and none of its values exceed {@link iRepairableStatistic#getPermissibleMaxima()}. Unknown values are left untouched.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void shallowRepair()
    {
        if (getIsShallowRepaired())
        {
            return;
        }
        
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        
        deleteDeletableDataSets();
        
        StatisticalDataRepairLogic.repairUpperExtremes(repairableStatistic);
        
        setRepairableStatistic(repairableStatistic);
    }
    
    
    /**
     * @Summary: Insures that {@link #shallowRepair()} has been called before it applies {@link StatisticalDataRepairLogic#setEntirelyUnknownsToAverages(iRepairableStatistic, ArrayList)} and {@link StatisticalDataRepairLogic#ascribeValuesToUnknownsUsingRegressions(iRepairableStatistic, int)} to {@link #repairableStatistic}.
     * This results in {@link #repairableStatistic} being unpresentable and unsuitable for further queries regarding extremes, whilst being particularly statistically robust and having no remaining unknown values.
     * @Precondition: This {@link StatisticalDataRepairCenter} has been initialized correctly. The Preconditions of {@link #setRepairableStatistic(iRepairableStatistic)} are met.
     * @Postcondition: The {@link #repairableStatistic} has been repaired with statistical usage in mind. None of its values exceed {@link iRepairableStatistic#getPermissibleMaxima()}. None of its values are {@link iRepairableStatistic#getUnknown()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void deepRepair()
    {
        if (!getIsShallowRepaired())
        {
            shallowRepair();
        }
        else if (getIsDeepRepaired())
        {
            return;
        }
        
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        StatisticalDataRepairLogic.setEntirelyUnknownsToAverages(repairableStatistic, StatisticalDataRepairLogic.findAvarages(repairableStatistic));
        StatisticalDataRepairLogic.ascribeValuesToUnknownsUsingRegressions(repairableStatistic, getINDEX_REGRESSION_BASIS());
        
        
        setRepairableStatistic(repairableStatistic);
    }
    
    
}