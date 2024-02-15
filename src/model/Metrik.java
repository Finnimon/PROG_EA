package model;

import Services.BaumServices;
import Services.KatasterServices;
import resources.Konstanten;
import resources.Strings;
import utility.Parser;
import java.util.List;

public class Metrik
{
    
    
    //region [Konstanten]
    
    
    private final int INDEX_PFLANZJAHR=0;
    
    private final int INDEX_STANDALTER=1;
    
    private final int INDEX_KRONEMETER=2;
    
    private final int INDEX_UMFANG_ZENTIMETER=3;
    
    private final int INDEX_HOEHE_METER=4;
    
    
    //endregion
    //region [Attribute]
    
    
    private int pflanzJahr;
    
    private int standAlter;
    
    private float kroneMeter;
    
    private float umfangZentimeter;
    
    private float hoeheMeter;
    
    
    //endregion
    //region[Konstruktor]
    
    
    public Metrik(List<String> werte) throws ElementFaultyException
    {
        setAlter( Parser.parseInt(werte.get(INDEX_PFLANZJAHR)),Parser.parseInt(werte.get(INDEX_STANDALTER)));
        setKroneMeter( Parser.parseFloat(werte.get(INDEX_KRONEMETER)));
        setUmfangZentimeter(Parser.parseFloat(werte.get(INDEX_UMFANG_ZENTIMETER)));
        setHoeheMeter(Parser.parseFloat(werte.get(INDEX_HOEHE_METER)));
    }
    
    
    //endregion
    //region [GetSet]
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
    
    
    public void setAlter(int pflanzJahr, int standAlter) throws ElementFaultyException
    {//todo!!!!!!!!!!!!!!
//        if((boolean standalterBekannt=BaumServices.bekanntheitPruefen(pflanzJahr))&&BaumServices.bekanntheitPruefen(standAlter))
//        {
//            setAlter(KatasterServices.JAHR_DER_ERHEBUNG,0);
//        }
//        else if ()
//        {
//
//        }
        if(pflanzJahr>standAlter)
        {
            setPflanzJahr(pflanzJahr);
            setStandAlter(standAlter);
        }
        else
        {
        setStandAlter(pflanzJahr);
        setPflanzJahr(standAlter);
        }
//        else
//        {
//            throw new ElementFaultyException();
//        }

    }
    
    
    //endregion
    //region [Overrides]
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Strings.PFLANZJAHR_STANDALTER);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getPflanzJahr()));
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getStandAlter()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Strings.DURCHMESSER_DER_KRONE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getKroneMeter()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Strings.STAMMUMFANG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getUmfangZentimeter()));
        stringBuilder.append(Strings.CRLF);
        
        
        stringBuilder.append(Strings.BAUMHOEHE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(getHoeheMeter()));
        
        return stringBuilder.toString();
    }
    
    
    //endregion
    
    
}
