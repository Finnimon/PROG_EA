package logic;

import model.LineareFunktion;
import utility.Core;
import utility.LinearerRegressor;
import utility.iRepairableStatistic;

import java.util.ArrayList;
import java.util.HashMap;

public record StatisticalDataRepairLogic()
{
    public static iRepairableStatistic lowerUpperExtremesUnderAssumptionOfUnitMistakes(iRepairableStatistic repairableStatistic)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        
        for (Integer key : repairables.keySet())
        {
            ArrayList<Float> repairable = repairables.get(key);
            ArrayList<Float> repaireds = divideRepairableByTenUntilAllPermissable(repairable, repairableStatistic.getPermissableMaxima());
            if (repaireds.equals(repairable))
            {
                continue;
            }
            
            
            repairables.put(key, repaireds);
        }
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
    
    
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
                
                if (wert == repairableStatistic.getUNKNOWN())
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
            if (Core.areAllValuesInCollectionEqualToSpecificValue(repairables.get(key), repairableStatistic.getUNKNOWN()))
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
        float unknown = repairableStatistic.getUNKNOWN();
        
        ArrayList<LineareFunktion> lineareRegressionen = new LinearerRegressor(indexRegressionBasis).alleRegressierenZurBasis(new ArrayList<>(repairables.values()));
        
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
                int ersterBekannterWertIndex = findIndexOfFirstKnownValue(repairableFloats, repairableStatistic.getUNKNOWN());
                
                x = lineareRegressionen.get(ersterBekannterWertIndex).inverseFVonY(repairableFloats.get(ersterBekannterWertIndex));
            }
            else
            {
                x = repairableFloats.get(indexRegressionBasis);
            }
            
            
            for (int i = 0; i < repairableFloats.size(); i++)
            {
                repairedFloats.add(lineareRegressionen.get(i).fVonX(x));
            }
            
            
            repairables.put(key, setUnknownValuesToRepaired(repairableFloats, repairedFloats, unknown));
        }
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
    
    
    public static iRepairableStatistic repairUpperExtremes(iRepairableStatistic repairableStatistic)
    {
        HashMap<Integer, ArrayList<Float>> repairables = repairableStatistic.getRepairableFloats();
        
        for (Integer key : repairables.keySet())
        {
            repairables.put(key,divideRepairableByTenUntilAllPermissable(repairables.get(key), repairableStatistic.getPermissableMaxima()));
        }
        
        repairableStatistic.setRepairableFloats(repairables);
        
        
        return repairableStatistic;
    }
}
