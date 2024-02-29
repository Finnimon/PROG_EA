package model;

import control.TreeCadastreQueryController;
import resources.Constants;
import resources.Messages;
import resources.Strings;
import services.TreeServices;
import utility.TreeComparator;
import utility.Core;
import utility.DataRepair.iRepairable;

import java.util.ArrayList;
import java.util.List;


public class Metric implements iRepairable
{
    
    public static final int INDEX_PLANTING_YEAR = 0;
    
    
    private static final int INDEX_AGE_IN_YEARS = 1;
    
    
    private static final int INDEX_TREETOP_DIAMETER_METERS = 2;
    
    
    private static final int INDEX_CIRCUMFERENCE_CENTIMETERS = 3;
    
    
    private static final int INDEX_HEIGHT_METERS = 4;
    
    
    /**
     * @Summary: The planting year of a {@link model.Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private int plantingYear;
    
    
    /**
     * @Summary: The Age of a {@link model.Tree} in years.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private int ageInYears;
    
    
    /**
     * @Summary: The Treetop diameter of a {@link model.Tree} in meters.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private float treeTopDiameterMeters;
    
    
    /**
     * @Summary: The trunk circumference of a {@link model.Tree} in centimeters.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private float circumferenceCentimeters;
    
    
    /**
     * @Summary: The height of a {@link model.Tree} in meters.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private float heightMeters;
    
    
    /**
     * @param stringList The values of the {@link model.Metric}
     * @Precondition: stringList.size() == 5 and every element in stringList is not null.
     * @Postcondition: The values of the {@link model.Metric} are set without throwing a {@link java.lang.NullPointerException}.
     * @Summary: This constructor creates a new {@link model.Metric}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public Metric(List<String> stringList)
    {
        setAge(Core.safeParseInt(stringList.get(INDEX_PLANTING_YEAR)), Core.safeParseInt(stringList.get(INDEX_AGE_IN_YEARS)));
        setTreeTopDiameterMeters(Core.safeParseFloat(stringList.get(INDEX_TREETOP_DIAMETER_METERS)));
        setCircumferenceCentimeters(Core.safeParseFloat(stringList.get(INDEX_CIRCUMFERENCE_CENTIMETERS)));
        setHeightMeters(Core.safeParseFloat(stringList.get(INDEX_HEIGHT_METERS)));
    }
    
    
    /**
     * @param plantingYear             The planting year of a {@link model.Tree}.
     * @param ageInYears               The Age of a {@link model.Tree} in years
     * @param treeTopDiameterMeters    The Treetop diameter of a {@link model.Tree} in meters
     * @param circumferenceCentimeters The trunk circumference of a {@link model.Tree} in centimeters
     * @param heightMeters             The height of a {@link model.Tree} in meters
     * @Precondition: None.
     * @Postcondition: A new {@link model.Metric} is created.
     * @Summary: This constructor creates a new {@link model.Metric}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public Metric(int plantingYear, int ageInYears, float treeTopDiameterMeters, float circumferenceCentimeters, float heightMeters)
    {
        this.plantingYear = plantingYear;
        this.ageInYears = ageInYears;
        this.treeTopDiameterMeters = treeTopDiameterMeters;
        this.circumferenceCentimeters = circumferenceCentimeters;
        this.heightMeters = heightMeters;
    }
    
    
    /**
     * @param index The index of the attribute by {@link TreeComparator}.
     * @return The attribute corresponding to the param index.
     * @throws IllegalArgumentException if the Precondition is not met.
     * @Precondition: {@link TreeComparator#ALLOWED_ATTRIBUTE_INDICES} contains the param index.
     * @Postcondition: The corresponding attribute will be returned.
     * @Summary: Getter for all of {@link Metric}s attributes used by {@link TreeComparator}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public float getAttributeByTreeComparatorIndex(int index)
    {
        if (index == TreeComparator.INDEX_AGE)
        {
            return getAgeInYears();
        }
        else if (index == TreeComparator.INDEX_TREETOP_DIAMETER_METERS)
        {
            return getTreeTopDiameterMeters();
        }
        else if (index == TreeComparator.INDEX_CIRCUMFERENCE_CENTIMETERS)
        {
            return getCircumferenceCentimeters();
        }
        else if (index == TreeComparator.INDEX_HEIGHT_METERS)
        {
            return getHeightMeters();
        }
        
        
        throw new IllegalArgumentException();
    }
    
    
    /**
     * @return {@link #plantingYear}
     * @Summary: Getter for {@link #plantingYear}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public int getPlantingYear()
    {
        return plantingYear;
    }
    
    /**
     * @param plantingYear the value to which {@link #plantingYear} should be set.
     * @Summary: Setter for {@link #plantingYear}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setPlantingYear(int plantingYear)
    {
        this.plantingYear = plantingYear;
    }
    
    /**
     * @return {@link #ageInYears}
     * @Summary: Getter for {@link #ageInYears}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public int getAgeInYears()
    {
        return ageInYears;
    }
    
    
    
    /**
     * @param ageInYears the value to which {@link #ageInYears} should be set.
     * @Summary: Setter for {@link #ageInYears}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setAgeInYears(int ageInYears)
    {
        this.ageInYears = ageInYears;
    }
    
    
    /**
     * @return {@link #treeTopDiameterMeters}
     * @Summary: Getter for {@link #treeTopDiameterMeters}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public float getTreeTopDiameterMeters()
    {
        return treeTopDiameterMeters;
    }
    
    
    /**
     * @param treeTopDiameterMeters the value to which {@link #treeTopDiameterMeters} should be set.
     * @Summary: Setter for {@link #treeTopDiameterMeters}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setTreeTopDiameterMeters(float treeTopDiameterMeters)
    {
        this.treeTopDiameterMeters = treeTopDiameterMeters;
    }
    
    
    /**
     * @return {@link #circumferenceCentimeters}
     * @Summary: Getter for {@link #circumferenceCentimeters}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public float getCircumferenceCentimeters()
    {
        return circumferenceCentimeters;
    }
    
    
    
    /**
     * @param circumferenceCentimeters the value to which {@link #circumferenceCentimeters} should be set.
     * @Summary: Setter for {@link #circumferenceCentimeters}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setCircumferenceCentimeters(float circumferenceCentimeters)
    {
        this.circumferenceCentimeters = circumferenceCentimeters;
    }
    
    
    /**
     * @return {@link #heightMeters}
     * @Summary: Getter for {@link #heightMeters}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public float getHeightMeters()
    {
        return heightMeters;
    }
    
    
    
    /**
     * @param heightMeters the value to which {@link #heightMeters} should be set.
     * @Summary: Setter for {@link #heightMeters}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setHeightMeters(float heightMeters)
    {
        this.heightMeters = heightMeters;
    }
    
    /**
     * @param plantingYear the value to which {@link #plantingYear} should be set.
     * @param ageInYears the value to which {@link #ageInYears} should be set.
     * @Precondition: At least one of the values is known.
     * @Postcondition: Both values will be correctly set to known values. If none are known both are set normally.
     * @Summary: Complex Setter for {@link #ageInYears} and {@link #plantingYear}. Since both are inherently connected one is deduced from the other if it is unknown.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void setAge(int plantingYear, int ageInYears)
    {
        boolean plantingYearUnknown = plantingYear == Constants.UNBEKANNT;
        boolean ageInYearsUnknown = ageInYears == Constants.UNBEKANNT;
        if (plantingYearUnknown & !ageInYearsUnknown)
        {
            plantingYear = TreeCadastreQueryController.YEAR_OF_THE_SURVEY - ageInYears;
        }
        else if (!plantingYearUnknown && ageInYearsUnknown)
        {
            ageInYears = TreeCadastreQueryController.YEAR_OF_THE_SURVEY - plantingYear;
        }
        
        if (ageInYears > plantingYear)
        {
            setAgeInYears(plantingYear);
            setPlantingYear(ageInYears);
        }
        else
        {
            setPlantingYear(plantingYear);
            setAgeInYears(ageInYears);
        }
    }
    
    
    /**
     * @return {@link ArrayList<Float>} of all of the attributes at their specified index.
     * @Summary: Implementation for {@link iRepairable#getRepairables()}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public ArrayList<Float> getRepairables()
    {
        ArrayList<Float> repairables = new ArrayList<>();
        repairables.add(INDEX_PLANTING_YEAR, (float) getPlantingYear());
        repairables.add(INDEX_AGE_IN_YEARS, (float) getAgeInYears());
        repairables.add(INDEX_TREETOP_DIAMETER_METERS, getTreeTopDiameterMeters());
        repairables.add(INDEX_CIRCUMFERENCE_CENTIMETERS, getCircumferenceCentimeters());
        repairables.add(INDEX_HEIGHT_METERS, getHeightMeters());
        
        
        return repairables;
    }
    
    
    /**
     * @param repaireds are the values that this {@link Metric}s attributes will be set to.
     * @Precondition: The indices are correct.
     * @Postcondition: All the values will be set to their counterpart within repaireds
     * @Summary: Implementation for {@link iRepairable#setRepairables(ArrayList)} sets all attributes by index of the values within the param.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public void setRepairables(ArrayList<Float> repaireds)
    {
        setPlantingYear(Math.round( repaireds.get(INDEX_PLANTING_YEAR)));
        setAgeInYears(Math.round( repaireds.get(INDEX_AGE_IN_YEARS)));
        setTreeTopDiameterMeters(repaireds.get(INDEX_TREETOP_DIAMETER_METERS));
        setCircumferenceCentimeters(repaireds.get(INDEX_CIRCUMFERENCE_CENTIMETERS));
        setHeightMeters(repaireds.get(INDEX_HEIGHT_METERS));
    }
    
    
    /**
     * @return {@code true} if all of the values in {@link #getRepairables()} are equal to {@link Constants#UNBEKANNT}.
     * @Summary: Implementation for {@link iRepairable#isEmpty()}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public boolean isEmpty()
    {
        return Core.areAllValuesInCollectionEqualToSpecificValue(getRepairables(), (float) Constants.UNBEKANNT);
    }
    
    
    /**
     * @return The attributes of this class in a {@link String} that is suitable for printing.
     * @Summary: @Override of {@link Object#toString()}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.PFLANZJAHR_STANDALTER);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(getPlantingYear()));
        stringBuilder.append(Strings.FORWARD_SLASH);
        stringBuilder.append(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(getAgeInYears()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.DURCHMESSER_DER_KRONE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(getTreeTopDiameterMeters()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.STAMM_UMFANG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(getCircumferenceCentimeters()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.BAUM_HOEHE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(getHeightMeters()));
        
        
        return stringBuilder.toString();
    }
    
    
    /**
     * @return a deep copy of this {@link Metric}.
     * @Summary: @Override for {@link Cloneable}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    protected Metric clone()
    {
        try
        {
            return (Metric) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
