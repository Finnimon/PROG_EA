package model;

import Services.BaumServices;
import Services.KatasterServices;
import control.Main;
import resources.Konstanten;
import resources.Messages;
import resources.Strings;
import utility.BaumComparator;
import utility.Core;
import utility.Parser;
import utility.iRepairable;

import java.util.ArrayList;
import java.util.List;

public class Metrik implements iRepairable
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
    
    //region [Konstanten]
    
    
    public static final int INDEX_PFLANZJAHR = 0;
    
    
    private static final int INDEX_STANDALTER = 1;
    
    
    private static final int INDEX_KRONEMETER = 2;
    
    
    private static final int INDEX_UMFANG_ZENTIMETER = 3;
    
    
    private static final int INDEX_HOEHE_METER = 4;
    
    
    //endregion
    //region [Attribute]
    
    
    private int pflanzJahr;
    
    private int standAlter;
    
    private float kroneMeter;
    
    private float umfangZentimeter;
    
    private float hoeheMeter;
    
    
    //endregion
    //region[Konstruktor]
    
    
    public Metrik(List<String> werte)
    {
        setAlter(Parser.parseInt(werte.get(INDEX_PFLANZJAHR)), Parser.parseInt(werte.get(INDEX_STANDALTER)));
        setKroneMeter(Parser.parseFloat(werte.get(INDEX_KRONEMETER)));
        setUmfangZentimeter(Parser.parseFloat(werte.get(INDEX_UMFANG_ZENTIMETER)));
        setHoeheMeter(Parser.parseFloat(werte.get(INDEX_HOEHE_METER)));
    }
    
    
    public Metrik(int pflanzJahr, int standAlter, float kroneMeter, float umfangZentimeter, float hoeheMeter)
    {
        this.pflanzJahr = pflanzJahr;
        this.standAlter = standAlter;
        this.kroneMeter = kroneMeter;
        this.umfangZentimeter = umfangZentimeter;
        this.hoeheMeter = hoeheMeter;
    }
    
    
    public float getAtrributNachBaumComparatorIndex(int attributIndex)
    {
        if (attributIndex == BaumComparator.INDEX_ALTER)
        {
            return getStandAlter();
        }
        else if (attributIndex == BaumComparator.INDEX_KRONE_METER)
        {
            return getKroneMeter();
        }
        else if (attributIndex == BaumComparator.INDEX_UMFANG_ZENTIMETER)
        {
            return getUmfangZentimeter();
        }
        else if (attributIndex == BaumComparator.INDEX_HOEHE_METER)
        {
            return getHoeheMeter();
        }
        
        
        throw new IllegalArgumentException();
    }
    
    
    public void setAttributNachIndex(int attributIndex, float attribut)
    {
        if (attributIndex == INDEX_PFLANZJAHR)
        {
            setPflanzJahr(Math.round(attribut));
        }
        else if (attributIndex == INDEX_STANDALTER)
        {
            setStandAlter(Math.round(attribut));
        }
        else if (attributIndex == INDEX_KRONEMETER)
        {
            setKroneMeter(attribut);
        }
        else if (attributIndex == INDEX_UMFANG_ZENTIMETER)
        {
            setUmfangZentimeter(attribut);
        }
        else if (attributIndex == INDEX_HOEHE_METER)
        {
            setHoeheMeter(attribut);
        }
        else
        {//todo messages Exceptions
            throw new IllegalArgumentException("Illegaler attributIndex");
        }
    }
    
    
    public int getPflanzJahr()
    {
        return pflanzJahr;
    }
    
    
    public void setPflanzJahr(int pflanzJahr)
    {
        this.pflanzJahr = pflanzJahr;
    }
    
    
    public int getStandAlter()
    {
        return standAlter;
    }
    
    
    public void setStandAlter(int standAlter)
    {
        this.standAlter = standAlter;
    }
    
    
    public float getKroneMeter()
    {
        return kroneMeter;
    }
    
    
    public void setKroneMeter(float kroneMeter)
    {
        this.kroneMeter = kroneMeter;
    }
    
    
    public float getUmfangZentimeter()
    {
        return umfangZentimeter;
    }
    
    
    public void setUmfangZentimeter(float umfangZentimeter)
    {
        this.umfangZentimeter = umfangZentimeter;
    }
    
    
    public float getHoeheMeter()
    {
        return hoeheMeter;
    }
    
    
    public void setHoeheMeter(float hoeheMeter)
    {
        this.hoeheMeter = hoeheMeter;
    }
    
    
    public void setAlter(int pflanzJahr, int standAlter)
    {//todo!!!!!!!!!!!!!!
        if (!BaumServices.bekanntheitPruefen(pflanzJahr) && BaumServices.bekanntheitPruefen(standAlter))
        {
            pflanzJahr = KatasterServices.JAHR_DER_ERHEBUNG - standAlter;
        }
        else if (BaumServices.bekanntheitPruefen(pflanzJahr) && !BaumServices.bekanntheitPruefen(standAlter))
        {
            standAlter = KatasterServices.JAHR_DER_ERHEBUNG - pflanzJahr;
        }
        
        if (standAlter > pflanzJahr)
        {
            setStandAlter(pflanzJahr);
            setPflanzJahr(standAlter);
        }
        else
        {
            setPflanzJahr(pflanzJahr);
            setStandAlter(standAlter);
        }
        
        
    }
    
    
    public void setAlter(int standAlter)
    {
        setStandAlter(standAlter);
        int pflanzJahr = getPflanzJahr();
        if (pflanzJahr == Konstanten.UNBEKANNT | pflanzJahr > KatasterServices.JAHR_DER_ERHEBUNG - Main.ARGUMENTE.get(0) | pflanzJahr > KatasterServices.JAHR_DER_ERHEBUNG)
        {
            setPflanzJahr(KatasterServices.JAHR_DER_ERHEBUNG - standAlter);
        }
    }
    
    
    @Override
    public ArrayList<Float> getRepairables()
    {
        //Indize nicht notwendig nur zur verdeutlichung
        ArrayList<Float> repairables = new ArrayList<>();
        repairables.add(INDEX_PFLANZJAHR, (float) getPflanzJahr());
        repairables.add(INDEX_STANDALTER, (float) getStandAlter());
        repairables.add(INDEX_KRONEMETER, getKroneMeter());
        repairables.add(INDEX_UMFANG_ZENTIMETER, getUmfangZentimeter());
        repairables.add(INDEX_HOEHE_METER, getHoeheMeter());
        
        
        return repairables;
    }
    
    
    @Override
    public void setRepairables(ArrayList<Float> reparierte)
    {
        setAlter(Math.round(reparierte.get(INDEX_STANDALTER)));
        setKroneMeter(reparierte.get(INDEX_KRONEMETER));
        setUmfangZentimeter(reparierte.get(INDEX_UMFANG_ZENTIMETER));
        setHoeheMeter(reparierte.get(INDEX_HOEHE_METER));
    }
    
    
    @Override
    public boolean isEmpty()
    {
        return Core.areAllValuesInCollectionEqualToSpecificValue(getRepairables(), Float.valueOf(Konstanten.UNBEKANNT));
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.PFLANZJAHR_STANDALTER);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getPflanzJahr()));
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getStandAlter()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.DURCHMESSER_DER_KRONE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getKroneMeter()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.STAMMUMFANG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getUmfangZentimeter()));
        stringBuilder.append(Strings.CRLF);
        
        
        stringBuilder.append(Messages.BAUMHOEHE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getHoeheMeter()));
        
        
        return stringBuilder.toString();
    }
    
    
    @Override
    protected Metrik clone()
    {
        return new Metrik(getPflanzJahr(),getStandAlter(),getKroneMeter(),getUmfangZentimeter(),getHoeheMeter());
    }
    
    
}
