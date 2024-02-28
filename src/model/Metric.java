package model;

import services.BaumServices;
import control.Main;
import control.TreeCadastreQueryController;
import resources.Constants;
import resources.Messages;
import resources.Strings;
import utility.BaumComparator;
import utility.Core;
import utility.Parser;
import utility.iRepairable;

import java.util.ArrayList;
import java.util.List;

public class Metric implements iRepairable
{
    //    FLOAT MAXIMUM
    //
    //    float HOECHSTER_BAUM_BERLINS_HOEHE = 43.5f;
    //
    //
    //    int AELTESTER_BAUM_BERLINS_ALTER = 917;
    //
    //
    //    float DICKSTER_BAUM_BERLINS_UMFANG_ZENTIMETER = 775f;
    //
    //
    //    //https://www.baumpflegeportal.de/aktuell/starke-baumtypen-die-linde-von-schenklengsfeld/
    //    float GROESZTER_KRONEN_DURCHMESSER_DEUTSCHLANDS=25;
    //
    //
    //    int ERLAUBTE_ANZAHL_LEERE_WERTE_IN_CSVRECORDS = 5;
    //
    
    //region [Constants]
    
    
    public static final int INDEX_PFLANZJAHR = 0;
    
    
    private static final int INDEX_STANDALTER = 1;
    
    
    private static final int INDEX_KRONEMETER = 2;
    
    
    private static final int INDEX_UMFANG_ZENTIMETER = 3;
    
    
    private static final int INDEX_HOEHE_METER = 4;
    
    
    //endregion
    //region [Attribute]
    
    
    private int plantingYear;
    
    private int ageInYears;
    
    private float treeTopDiameterMeters;
    
    private float circumferenceCentimeters;
    
    private float heightMeters;
    
    
    //endregion
    //region[Konstruktor]
    
    
    public Metric(List<String> werte)
    {
        setAge(Parser.safeParseInt(werte.get(INDEX_PFLANZJAHR)), Parser.safeParseInt(werte.get(INDEX_STANDALTER)));
        setTreeTopDiameterMeters(Parser.safeParseFloat(werte.get(INDEX_KRONEMETER)));
        setCircumferenceCentimeters(Parser.safeParseFloat(werte.get(INDEX_UMFANG_ZENTIMETER)));
        setHeightMeters(Parser.safeParseFloat(werte.get(INDEX_HOEHE_METER)));
    }
    
    
    public Metric(int plantingYear, int ageInYears, float treeTopDiameterMeters, float circumferenceCentimeters, float heightMeters)
    {
        this.plantingYear = plantingYear;
        this.ageInYears = ageInYears;
        this.treeTopDiameterMeters = treeTopDiameterMeters;
        this.circumferenceCentimeters = circumferenceCentimeters;
        this.heightMeters = heightMeters;
    }
    
    
    public float getAttributeByTreeComparatorIndex(int index)
    {
        if (index == BaumComparator.INDEX_ALTER)
        {
            return getAgeInYears();
        }
        else if (index == BaumComparator.INDEX_KRONE_METER)
        {
            return getTreeTopDiameterMeters();
        }
        else if (index == BaumComparator.INDEX_UMFANG_ZENTIMETER)
        {
            return getCircumferenceCentimeters();
        }
        else if (index == BaumComparator.INDEX_HOEHE_METER)
        {
            return getHeightMeters();
        }
        
        
        throw new IllegalArgumentException();
    }
    
    
    public int getPlantingYear()
    {
        return plantingYear;
    }
    
    
    public void setPlantingYear(int plantingYear)
    {
        this.plantingYear = plantingYear;
    }
    
    
    public int getAgeInYears()
    {
        return ageInYears;
    }
    
    
    public void setAgeInYears(int ageInYears)
    {
        this.ageInYears = ageInYears;
    }
    
    
    public float getTreeTopDiameterMeters()
    {
        return treeTopDiameterMeters;
    }
    
    
    public void setTreeTopDiameterMeters(float treeTopDiameterMeters)
    {
        this.treeTopDiameterMeters = treeTopDiameterMeters;
    }
    
    
    public float getCircumferenceCentimeters()
    {
        return circumferenceCentimeters;
    }
    
    
    public void setCircumferenceCentimeters(float circumferenceCentimeters)
    {
        this.circumferenceCentimeters = circumferenceCentimeters;
    }
    
    
    public float getHeightMeters()
    {
        return heightMeters;
    }
    
    
    public void setHeightMeters(float heightMeters)
    {
        this.heightMeters = heightMeters;
    }
    
    
    public void setAge(int plantingYear, int ageInYears)
    {//todo!!!!!!!!!!!!!!
        boolean plantingYearUnknown = plantingYear == Constants.UNBEKANNT;
        boolean ageInYearsUnknown = ageInYears == Constants.UNBEKANNT;
        if (plantingYearUnknown &!ageInYearsUnknown)
        {
            plantingYear = TreeCadastreQueryController.JAHR_DER_ERHEBUNG - ageInYears;
        }
        else if (!plantingYearUnknown && ageInYearsUnknown)
        {
            ageInYears = TreeCadastreQueryController.JAHR_DER_ERHEBUNG - plantingYear;
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
    
    
    public void setAge(int ageInYears)
    {
        setAgeInYears(ageInYears);
        int plantingYear = getPlantingYear();
        if (plantingYear == Constants.UNBEKANNT  | plantingYear > Main.ARGUMENTS.get(INDEX_PFLANZJAHR))
        {
            setPlantingYear(TreeCadastreQueryController.JAHR_DER_ERHEBUNG - ageInYears);
        }
        
    }
    
    
    @Override
    public ArrayList<Float> getRepairables()
    {
        ArrayList<Float> repairables = new ArrayList<>();
        repairables.add(INDEX_PFLANZJAHR, (float) getPlantingYear());
        repairables.add(INDEX_STANDALTER, (float) getAgeInYears());
        repairables.add(INDEX_KRONEMETER, getTreeTopDiameterMeters());
        repairables.add(INDEX_UMFANG_ZENTIMETER, getCircumferenceCentimeters());
        repairables.add(INDEX_HOEHE_METER, getHeightMeters());
        
        
        return repairables;
    }
    
    
    @Override
    public void setRepairables(ArrayList<Float> reparierte)
    {
        setAge(Math.round(reparierte.get(INDEX_STANDALTER)));
        setTreeTopDiameterMeters(reparierte.get(INDEX_KRONEMETER));
        setCircumferenceCentimeters(reparierte.get(INDEX_UMFANG_ZENTIMETER));
        setHeightMeters(reparierte.get(INDEX_HOEHE_METER));
    }
    
    
    @Override
    public boolean isEmpty()
    {
        return Core.areAllValuesInCollectionEqualToSpecificValue(getRepairables(), (float) Constants.UNBEKANNT);
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.PFLANZJAHR_STANDALTER);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getPlantingYear()));
        stringBuilder.append(Strings.FORWARD_SLASH);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getAgeInYears()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.DURCHMESSER_DER_KRONE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getTreeTopDiameterMeters()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.STAMM_UMFANG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getCircumferenceCentimeters()));
        stringBuilder.append(Strings.CRLF);
        
        
        stringBuilder.append(Messages.BAUM_HOEHE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getHeightMeters()));
        
        
        return stringBuilder.toString();
    }
    
    
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
