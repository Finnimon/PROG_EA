package utility.DataRepair;

import model.LinearFunction;
import org.jetbrains.annotations.NotNull;
import utility.Core;
import utility.LinearRegressor;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @Summary: This record contains static methods that can be used to repair {@link iRepairableStatistic}. This should be seen as a helper record for {@link StatisticalDataRepairCenter} and not be used outside that context.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
record StatisticalDataRepairLogic()
{
    
    
    static ArrayList<Float> divideRepairableByTenUntilAllPermissable(@NotNull ArrayList<Float> repairables,@NotNull ArrayList<Float> permissableMaxima)
    {
        
        for (int i = 0; i < repairables.size(); i++)
        {
            repairables.set(i, divideByTenUntilPermissable(repairables.get(i), permissableMaxima.get(i)));
        }
        
        
        return repairables;
    }
    
    
    static float divideByTenUntilPermissable(float repairableWert, float maximumPermissableWert)
    {
        final int divider = 10;
        
        if (repairableWert > maximumPermissableWert)
        {
            return divideByTenUntilPermissable((repairableWert / divider), maximumPermissableWert);
        }
        
        
        return repairableWert;
    }
    
    
    static ArrayList<Float> findAverages(@NotNull iRepairableStatistic repairableStatistic)
    {
        ArrayList<Integer> counters = null;
        ArrayList<Float> averages = null;
        
        for (ArrayList<Float> werte : repairableStatistic.getRepairableFloats().values())
        {
            for (int i = 0, size = werte.size(); i < size; i++)
            {
                float wert = werte.get(i);
                
                if (wert == repairableStatistic.UNKNOWN())
                {
                    continue;
                }
                
                int counter;
                
                try
                {
                    counter = counters.get(i);
                    wert += averages.get(i);
                }
                catch (NullPointerException e)
                {
                    counters = Core.createFilledArrayListOfSize(size,0);
                    averages = Core.createFilledArrayListOfSize(size,0f);
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
    
    
    static ArrayList<Float> setUnknownValuesToRepaired(@NotNull ArrayList<Float> repairables,@NotNull  ArrayList<Float> repaireds, float UNKNOWN)
    {
        for (int i = 0; i < repairables.size(); i++)
        {
            if (repairables.get(i) == UNKNOWN)
            {
                repairables.set(i, repaireds.get(i));
            }
        }
        
        
        return repairables;
    }
    
    
    static int findIndexOfFirstKnownValue(@NotNull ArrayList<Float> repairables, float UNKNOWN)
    {
        for (int i = 0; i < repairables.size(); i++)
        {
            if (repairables.get(i) != UNKNOWN)
            {
                return i;
            }
        }
        
        throw new IllegalArgumentException();
    }
    
    
    static ArrayList<Integer> findKeysUnknowns(@NotNull iRepairableStatistic repairableStatistic)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        ArrayList<Integer> keys = new ArrayList<>();
        
        for (Integer key : repairables.keySet())
        {
            if (Core.areAllValuesInCollectionEqualToSpecificValue(repairables.get(key), repairableStatistic.UNKNOWN()))
            {
                keys.add(key);
            }
        }
        
        
        return keys;
    }
    
    
    static iRepairableStatistic setEntirelyUnknownsToAverages(@NotNull iRepairableStatistic repairableStatistic,@NotNull  ArrayList<Float> averages)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        
        for (Integer key : findKeysUnknowns(repairableStatistic))
        {
            repairables.put(key, averages);
        }
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
    
    
    static iRepairableStatistic ascribeValuesToUnknownsUsingRegressions(@NotNull iRepairableStatistic repairableStatistic, int indexRegressionBasis)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        float unknown = repairableStatistic.UNKNOWN();
        
        ArrayList<LinearFunction> lineareRegressionen = new LinearRegressor(indexRegressionBasis).regressAllToBasisIndex(new ArrayList<>(repairables.values()));
        
        for (Integer key : repairables.keySet())
        {
            ArrayList<Float> repairableFloats = repairables.get(key);
            
            if (!repairableFloats.contains(unknown))
            {
                continue;
            }
            
            ArrayList<Float> repairedFloats = new ArrayList<>();
            
            float x;
            
            
            if (repairableFloats.get(indexRegressionBasis) == unknown)
            {
                int ersterBekannterWertIndex = findIndexOfFirstKnownValue(repairableFloats, repairableStatistic.UNKNOWN());
                
                x = lineareRegressionen.get(ersterBekannterWertIndex).inverse(repairableFloats.get(ersterBekannterWertIndex));
            }
            else
            {
                x = repairableFloats.get(indexRegressionBasis);
            }
            
            
            for (int i = 0; i < repairableFloats.size(); i++)
            {
                repairedFloats.add(lineareRegressionen.get(i).f(x));
            }
            
            
            repairables.put(key, setUnknownValuesToRepaired(repairableFloats, repairedFloats, unknown));
        }
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
    
    
    /**
     * @param repairableStatistic a {@link iRepairableStatistic} that is not null.
     * @return repairableStatistic after lowering upper extremes under assumption of unit mistakes until all values are permissible.
     * @Precondition: repairableStatistic is not null.
     * @Postcondition: No values exceed their respective {@link iRepairableStatistic#getPermissibleMaxima()}.
     * @Summary: Lowers upper extremes under the assumption of unit mistakes until all values are permissible.
     */
    static iRepairableStatistic repairUpperExtremes(@NotNull iRepairableStatistic repairableStatistic)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        
        repairables.replaceAll((k, v) -> divideRepairableByTenUntilAllPermissable(repairables.get(k), repairableStatistic.getPermissibleMaxima()));
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
}
