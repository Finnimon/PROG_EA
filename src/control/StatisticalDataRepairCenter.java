package control;

import utility.iRepairableStatistic;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticalDataRepairCenter
{
    
    
    //region [Attribute]
    
    
    private iRepairableStatistic repairableStatistic;
    
    
    private HashMap<Integer, ArrayList<Float>> repairables;
    
    
    private boolean isRepaired;
    
    
    private ArrayList<Float> averages;
    
    
    private final float UNBEKANNT;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public StatisticalDataRepairCenter(iRepairableStatistic repairableStatistic, float UNBEKANNT)
    {
        setRepairableStatistic(repairableStatistic);
        setRepairables(getRepairableStatistic().getRepairables());
        initialisieren();
        this.UNBEKANNT = UNBEKANNT;
    }
    
    
    //endregion
    //region [GetSet]
    
    
    public iRepairableStatistic getRepairableStatistic()
    {
        return repairableStatistic;
    }
    
    
    public void setRepairableStatistic(iRepairableStatistic repairableStatistic)
    {
        this.repairableStatistic = repairableStatistic;
    }
    
    
    public HashMap<Integer, ArrayList<Float>> getRepairables()
    {
        return repairables;
    }
    
    
    public void setRepairables(HashMap<Integer, ArrayList<Float>> repairables)
    {
        this.repairables = repairables;
    }
    
    
    public boolean isRepaired()
    {
        return isRepaired;
    }
    
    
    public void setIsRepaired(boolean repaired)
    {
        isRepaired = repaired;
    }
    
    
    public ArrayList<Float> getAverages()
    {
        return averages;
    }
    
    
    public void setAverages(ArrayList<Float> averages)
    {
        this.averages = averages;
    }
    
    
    //endregion
    //region [Methoden]
    
    
    private void initialisieren()
    {
        setIsRepaired(false);
        setAverages(findAvarages());
    }
    
    
    private ArrayList<Float> findAvarages()
    {
        ArrayList<Integer>counters=new ArrayList<>();
        ArrayList<Float> averages=new ArrayList<>();
        for (ArrayList<Float> werte : getRepairables().values())
        {
            for (int i = 0; i < werte.size(); i++)
            {
                float wert= werte.get(i);
                
                if (wert== UNBEKANNT) continue;
                
                int counter;
                
                try
                {
                    counter =counters.get(i);
                }
                catch (NullPointerException e)
                {
                    counter=0;
                }
                
                counter++;
                counters.set(i,counter);
                
                averages.set(i, averages.get(i)+wert);
            }
            
        }
        
        for (int i=0;i<averages.size();i++)
        {
            averages.set(i, averages.get(i)/counters.get(i));
        }
        
        
        return averages;
    }
    
    
    public void setUnknownValuesToAverages()
    {
        HashMap<Integer,ArrayList<Float>> repairables=getRepairables();
        
        for (Integer key:repairables.keySet())
        {
            ArrayList<Float> werte=repairables.get(key);
            for (int i = 0; i < werte.size(); i++)
            {
                if(werte.get(i)!=UNBEKANNT) continue;
                werte.set(i, getAverages().get(i));
            }
            
            repairables.put(key,werte);
        }
        
        
        setRepairables(repairables);
    }
    
    
    public iRepairableStatistic repair()
    {
        setUnknownValuesToAverages();
        iRepairableStatistic repairedStatistic;
        (repairedStatistic= getRepairableStatistic()).setRepairables(getRepairables());
        setRepairableStatistic(repairedStatistic);
        setIsRepaired(true);
        
        return repairedStatistic;
    }
    
    
    //endregion
    
    
}
