package control;

import logic.StatisticalDataRepairLogic;
import utility.iRepairableStatistic;

public class StatisticalDataRepairCenter
{
    
    
    private final int INDEX_REGRESSION_BASIS = 3;
    
    
    private iRepairableStatistic repairableStatistic;
    
    
    private boolean isShallowRepaired;
    
    
    private boolean isDeepRepaired;
    
    
    public StatisticalDataRepairCenter(iRepairableStatistic repairableStatistic)
    {
        this.repairableStatistic = repairableStatistic;
        initialisieren(false);
    }
    
    
    public StatisticalDataRepairCenter(iRepairableStatistic repairableStatistic, boolean isShallowRepaired)
    {
        this.repairableStatistic = repairableStatistic;
        initialisieren(isShallowRepaired);
    }
    
    
    private void initialisieren(boolean isShallowRepaired)
    {
        setIsShallowRepaired(isShallowRepaired);
        setIsDeepRepaired(false);
    }
    
    
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
        
        
        return getRepairableStatistic().clone();
    }
    
    
    public iRepairableStatistic getDeepRepairedStatistic()
    {
        if (!getIsDeepRepaired())
        {
            deepRepair();
        }
        
        
        return getRepairableStatistic().clone();
    }
    
    
    private int getINDEX_REGRESSION_BASIS()
    {
        return INDEX_REGRESSION_BASIS;
    }
    
    
    private iRepairableStatistic getRepairableStatistic()
    {
        return repairableStatistic;
    }
    
    
    private void setRepairableStatistic(iRepairableStatistic repairableStatistic)
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
    
    
    private boolean getIsDeepRepaired()
    {
        return isDeepRepaired;
    }
    
    
    private void setIsDeepRepaired(boolean repaired)
    {
        isDeepRepaired = repaired;
    }
    
    
    private boolean getIsShallowRepaired()
    {
        return isShallowRepaired;
    }
    
    
    private void setIsShallowRepaired(boolean shallowRepaired)
    {
        isShallowRepaired = shallowRepaired;
    }
    
    
    private void deleteDeletableDataSets()
    {
        iRepairableStatistic repairableStatistic =getRepairableStatistic();
        repairableStatistic.deleteDataSetsOfKeySet(repairableStatistic.getDeletableDataSetKeys());
    }
    
    
    public void shallowRepair()
    {
        
        if (getIsShallowRepaired())
        {
            return;
        }
        
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        
        repairableStatistic.deleteDataSetsOfKeySet(repairableStatistic.getDeletableDataSetKeys());
        
        repairableStatistic = StatisticalDataRepairLogic.repairUpperExtremes(repairableStatistic);
        
        setRepairableStatistic(repairableStatistic);
    }
    
    
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
        repairableStatistic = StatisticalDataRepairLogic.setEntirelyUnknownsToAverages(repairableStatistic, StatisticalDataRepairLogic.findAvarages(repairableStatistic));
        repairableStatistic = StatisticalDataRepairLogic.ascribeValuesToUnknownsUsingRegressions(repairableStatistic, getINDEX_REGRESSION_BASIS());
        //it might regress crazy values out of the true extreme for example the thickest tree if it hast no known height might become incredibly high
        repairableStatistic = StatisticalDataRepairLogic.repairUpperExtremes(repairableStatistic);
        
        setRepairableStatistic(repairableStatistic);
    }
    
    
}