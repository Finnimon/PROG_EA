package control;

import model.LineareFunktion;
import utility.Core;
import utility.LinearerRegressor;
import utility.iRepairableStatistic;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticalDataRepairCenter
{
    
    
    //region[Konstanten]
    
    
    private final int DIVIDER = 10;
    
    
    private final int INDEX_REGRESSION_BASIS = 4;
    
    
    //endregion
    //region [Attribute]
    private final float UNBEKANNT;
    private iRepairableStatistic repairableStatistic;
    private HashMap<Integer, ArrayList<Float>> repairables;
    private boolean isRepaired;
    private ArrayList<Float> averages;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public StatisticalDataRepairCenter(iRepairableStatistic repairableStatistic, float UNBEKANNT)
    {
        setRepairableStatistic(repairableStatistic);
        setRepairables(getRepairableStatistic().getRepairableFloats());
        initialisieren();
        this.UNBEKANNT = UNBEKANNT;
    }
    
    
    private void initialisieren()
    {
        setIsRepaired(false);
    }
    
    
    //endregion
    //region [GetSet]
    
    
    public int getINDEX_REGRESSION_BASIS()
    {
        return INDEX_REGRESSION_BASIS;
    }
    
    
    public int getDIVIDER()
    {
        return DIVIDER;
    }
    
    
    public iRepairableStatistic getRepairableStatistic()
    {
        return repairableStatistic;
    }
    
    
    public void setRepairableStatistic(iRepairableStatistic repairableStatistic)
    {
        this.repairableStatistic = repairableStatistic;
    }
    
    
    private HashMap<Integer, ArrayList<Float>> getRepairables()
    {
        return repairables;
    }
    
    
    private void setRepairables(HashMap<Integer, ArrayList<Float>> repairables)
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
    //region [Logik]
    
    
    private void lowerUpperExtremesUnderAssumptionOfUnitMistakes()
    {
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        
        for (Integer key : repairables.keySet())
        {
            repairables.put(key, divideRepairableByTenUntilAllPermissable(repairables.get(key)));
        }
        
        
        repairableStatistic.setRepairables(repairables);
        setRepairables(repairables);
        setRepairableStatistic(repairableStatistic);
    }
    
    
    private ArrayList<Float> divideRepairableByTenUntilAllPermissable(ArrayList<Float> repairables)
    {
        ArrayList<Float> maximumPermissables = getRepairableStatistic().getPermissableMaxima();
        
        for (int i = 0; i < repairables.size(); i++)
        {
            repairables.set(i, divideByTenUntilPermissable(repairables.get(i), maximumPermissables.get(i)));
        }
        
        return repairables;
    }
    
    
    private float divideByTenUntilPermissable(float repairableWert, float maximumPermissableWert)
    {
        if (repairableWert > maximumPermissableWert)
        {
            return divideByTenUntilPermissable((repairableWert / getDIVIDER()), maximumPermissableWert);
        }
        
        return repairableWert;
    }
    
    
    private ArrayList<Float> findAvarages()
    {
        ArrayList<Integer> counters = new ArrayList<>();
        ArrayList<Float> averages = new ArrayList<>();
        for (ArrayList<Float> werte : getRepairables().values())
        {
            for (int i = 0, size= werte.size(); i < size; i++)
            {
                float wert = werte.get(i);
                
                if (wert == UNBEKANNT)
                {
                    continue;
                }
                
                int counter;
                
                try
                {
                    counter = counters.get(i);
                    wert += averages.get(i);
                }
                catch (IndexOutOfBoundsException e)
                {
                    counters=Core.createZeroFilledIntegerArrayList(size);
                    averages=Core.createZeroFilledFloatArrayList(size);
                    counter = 0;
                }
                
                counter++;
                
                counters.set(i, counter);
                
                averages.set(i, wert);
            }
            
        }
        
        for (int i = 0; i < averages.size(); i++)
        {
            averages.set(i, averages.get(i) / counters.get(i));
        }
        
        
        return averages;
    }
    
    
    private ArrayList<Float> setUnknownValuesToRepaired(ArrayList<Float> repairables, ArrayList<Float> repaireds)
    {
        for (int i = 0; i < repairables.size(); i++)
        {
            if (repairables.get(i) != UNBEKANNT)
            {
                continue;
            }
            repairables.set(i, repaireds.get(i));
        }
        
        
        return repairables;
    }
    
    
    private int findIndexOfFirstKnownValue(ArrayList<Float> repairables)
    {
        for (int i = 0; i < repairables.size(); i++)
        {
            if (repairables.get(i) != UNBEKANNT)
            {
                return i;
            }
        }
        
        throw new RuntimeException("Die ArrayList hat keine bekannten Werte");//todo
    }
    
    
    private boolean hatNurBekannteWerte(ArrayList<Float> repairables)
    {
        for (Float repairable : repairables)
        {
            if (repairable == UNBEKANNT)
            {
                return false;
            }
        }
        return true;
    }
    
    
    //endregion
    //region [DataInteraction]
    
    
    public void repair()
    {
        repairUpperExtremes();
        setAverages(findAvarages());
        repairEntirelyUnknowns();
        repairUnknownsThroughRegression();
        setIsRepaired(true);
    }
    
    
    private void repairUpperExtremes()
    {
        HashMap<Integer, ArrayList<Float>> repairables = getRepairables();
        
        for (Integer key : repairables.keySet())
        {
            repairables.put(key, divideRepairableByTenUntilAllPermissable(repairables.get(key)));
        }
        
        setRepairables(repairables);
        
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        
        
        repairableStatistic.setRepairables(repairables);
        setRepairableStatistic(repairableStatistic);
    }
    
    
    private void repairUnknownsThroughRegression()
    {
        repairables = getRepairables();
        ArrayList<ArrayList<Float>> regressierbare = new ArrayList<>(repairables.values());
        LinearerRegressor linearerRegressor = new LinearerRegressor(regressierbare, getINDEX_REGRESSION_BASIS());
        
        ArrayList<LineareFunktion> lineareRegressionen = linearerRegressor.alleRegressierenZurBasis();
        
        for (Integer key : repairables.keySet())
        {
            ArrayList<Float> repairableFloats = repairables.get(key);
            ArrayList<Float> repairedFloats = new ArrayList<>();
            
            float x;
            
            
            if (repairableFloats.get(getINDEX_REGRESSION_BASIS()) == UNBEKANNT)
            {
                int ersterBekannterWertIndex = findIndexOfFirstKnownValue(repairableFloats);
                
                x = lineareRegressionen.get(ersterBekannterWertIndex).inverseFVonY(repairableFloats.get(ersterBekannterWertIndex));
            }
            else
            {
                x = repairableFloats.get(getINDEX_REGRESSION_BASIS());
            }
            
            
            for (int i = 0; i < repairableFloats.size(); i++)
            {
                repairedFloats.add(lineareRegressionen.get(i).fVonX(x));
            }
            
            
            repairables.put(key, setUnknownValuesToRepaired(repairableFloats, repairedFloats));
        }
        
        
        setRepairables(repairables);
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        repairableStatistic.setRepairables(repairables);
        setRepairableStatistic(repairableStatistic);
    }
    
    
    private void repairEntirelyUnknowns()
    {
        HashMap<Integer, ArrayList<Float>> repairables = getRepairables();
        
        
        for (Integer key : repairables.keySet())
        {
            if (!hatNurBekannteWerte(repairables.get(key))) repairables.put(key,getAverages());
        }
        
        
        setRepairables(repairables);
        iRepairableStatistic repairableStatistic = getRepairableStatistic();
        repairableStatistic.setRepairables(repairables);
        setRepairableStatistic(repairableStatistic);
    }
    
    
    //endregion
    //endregion
    
    
}
