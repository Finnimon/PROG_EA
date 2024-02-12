package model;

import control.BaumController;
import resources.Konstanten;
import resources.Strings;
import utility.Parser;

import java.util.List;

public class Metrik
{
    //
    private int pflanzJahr;
    
    private int standalter;
    
    private float kroneMeter;
    
    private float umfangZentimeter;
    
    private float hoeheMeter;
    
    
    public Metrik(List<String> werte)
    {
        //todo Konstanten.MetrikIndexUmfang=... in Metrik
        this.pflanzJahr = Parser.parseInt(werte.get(0));
        this.standalter = Parser.parseInt(werte.get(Konstanten.EINS));
        this.kroneMeter = Parser.parseFloat(werte.get(Konstanten.ZWEI));
        this.umfangZentimeter = Parser.parseFloat(werte.get(Konstanten.DREI));
        this.hoeheMeter = Parser.parseFloat(werte.get(Konstanten.VIER));
    }
    
    
    public int getPflanzJahr()
    {
        return pflanzJahr;
    }
    
    
    public void setPflanzJahr(int pflanzJahr)
    {
        this.pflanzJahr = pflanzJahr;
    }
    
    
    public int getStandalter()
    {
        return standalter;
    }
    
    
    public void setStandalter(int standalter)
    {
        this.standalter = standalter;
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
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Strings.PFLANZJAHR_STANDALTER);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(getPflanzJahr()));
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(getStandalter()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Strings.DURCHMESSER_DER_KRONE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(getKroneMeter()));
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Strings.STAMMUMFANG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(getUmfangZentimeter()));
        stringBuilder.append(Strings.CRLF);
        
        
        stringBuilder.append(Strings.BAUMHOEHE);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(getHoeheMeter()));
        
        return stringBuilder.toString();
    }
    
    
}
