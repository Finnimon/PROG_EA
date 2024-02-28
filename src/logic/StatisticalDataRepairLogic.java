package logic;

import control.StatisticalDataRepairCenter;
import model.LinearFunction;
import org.jetbrains.annotations.NotNull;
import utility.Core;
import utility.LinearerRegressor;
import utility.iRepairableStatistic;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @Summary: This record contains static methods that can be used to repair {@link iRepairableStatistic}. This should be seen as a helper record for {@link StatisticalDataRepairCenter} and not be used outside that context.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public record StatisticalDataRepairLogic()
{
    
    

//todo    public static iRepairableStatistic lowerUpperExtremesUnderAssumptionOfUnitMistakes(@NotNull iRepairableStatistic repairableStatistic)
//    {
//        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
//
//        for (Integer key : repairables.keySet())
//        {
//            ArrayList<Float> repairable = repairables.get(key);
//            ArrayList<Float> repaireds = divideRepairableByTenUntilAllPermissable(repairable, repairableStatistic.getPermissibleMaxima());
//            if (repaireds.equals(repairable))
//            {
//                continue;
//            }
//
//
//            repairables.put(key, repaireds);
//        }
//
//        repairableStatistic.setRepairableFloats(repairables);
//
//
//        return repairableStatistic;
//    }
    
    
    public static ArrayList<Float> divideRepairableByTenUntilAllPermissable(ArrayList<Float> repairables, ArrayList<Float> permissableMaxima)
    {
        
        for (int i = 0; i < repairables.size(); i++)
        {
            repairables.set(i, divideByTenUntilPermissable(repairables.get(i), permissableMaxima.get(i)));
        }
        
        
        return repairables;
    }
    
    
    public static float divideByTenUntilPermissable(float repairableWert, float maximumPermissableWert)
    {
        final int divider = 10;
        
        if (repairableWert > maximumPermissableWert)
        {
            return divideByTenUntilPermissable((repairableWert / divider), maximumPermissableWert);
        }
        
        
        return repairableWert;
    }
    
    
    public static ArrayList<Float> findAvarages(iRepairableStatistic repairableStatistic)
    {
        ArrayList<Integer> counters = null;
        ArrayList<Float> averages = null;
        
        for (ArrayList<Float> werte : repairableStatistic.getRepairableFloats().values())
        {
            for (int i = 0, size = werte.size(); i < size; i++)
            {
                float wert = werte.get(i);
                
                if (wert == repairableStatistic.getUnknown())
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
                    counters = Core.createZeroFilledIntegerArrayList(size);
                    averages = Core.createZeroFilledFloatArrayList(size);
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
    
    
    public static ArrayList<Float> setUnknownValuesToRepaired(ArrayList<Float> repairables, ArrayList<Float> repaireds, float UNKNOWN)
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
    
    
    public static int findIndexOfFirstKnownValue(ArrayList<Float> repairables, float UNKNOWN)
    {
        for (int i = 0; i < repairables.size(); i++)
        {
            if (repairables.get(i) != UNKNOWN)
            {
                return i;
            }
        }
        
        throw new IllegalArgumentException("The provided ArrayLis has no known values.");//todo message
    }
    
    
    public static boolean hatNurBekannteWerte(ArrayList<Float> repairables, float UNKNOWN)
    {
        for (Float repairable : repairables)
        {
            if (repairable == UNKNOWN)
            {
                return false;
            }
        }
        
        
        return true;
    }
    
    
    public static ArrayList<Integer> findKeysUnknowns(iRepairableStatistic repairableStatistic)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        ArrayList<Integer> keys = new ArrayList<>();
        
        for (Integer key : repairables.keySet())
        {
            if (Core.areAllValuesInCollectionEqualToSpecificValue(repairables.get(key), repairableStatistic.getUnknown()))
            {
                keys.add(key);
            }
        }
        
        
        return keys;
    }
    
    
    public static iRepairableStatistic setEntirelyUnknownsToAverages(iRepairableStatistic repairableStatistic, ArrayList<Float> averages)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        
        for (Integer key : findKeysUnknowns(repairableStatistic))
        {
            repairables.put(key, averages);
        }
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
    
    
    public static iRepairableStatistic ascribeValuesToUnknownsUsingRegressions(iRepairableStatistic repairableStatistic, int indexRegressionBasis)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        float unknown = repairableStatistic.getUnknown();
        
        ArrayList<LinearFunction> lineareRegressionen = new LinearerRegressor(indexRegressionBasis).alleRegressierenZurBasis(new ArrayList<>(repairables.values()));
        
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
                int ersterBekannterWertIndex = findIndexOfFirstKnownValue(repairableFloats, repairableStatistic.getUnknown());
                
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
    public static iRepairableStatistic repairUpperExtremes(iRepairableStatistic repairableStatistic)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        
        for (Integer key : repairables.keySet())
        {
            repairables.put(key,divideRepairableByTenUntilAllPermissable(repairables.get(key), repairableStatistic.getPermissibleMaxima()));
        }
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
}
