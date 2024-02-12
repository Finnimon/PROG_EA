package model;

import control.BaumController;
import control.KatasterController;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;

import java.util.List;

public class Baum implements Comparable<Baum>
{
    //todo METRIK INTERFACE?
    
    private final Ort ort;
    
    private final Taxonomie taxonomie;
    
    private Metrik metrik;
    
    
    public Baum(Ort ort, Taxonomie taxonomie, Metrik metrik)
    {
        this.ort = ort;
        this.taxonomie = taxonomie;
        this.metrik = metrik;
    }
    
    
    public Baum(List<String> zeile) throws ElementFaultyException
    {
        this.ort = new Ort(zeile.get(Konstanten.EINS), zeile.getLast());
        this.taxonomie = new Taxonomie(zeile.subList(Konstanten.ZWEI, Konstanten.SECHS));
        setMetrik(new Metrik(zeile.subList(Konstanten.SECHS, Konstanten.ELF)));
    }
    
    
    public Taxonomie getTaxonomie()
    {
        return this.taxonomie;
    }
    
    
    public Metrik getMetrik()
    {
        return this.metrik;
    }
    
    
    public void setMetrik(Metrik metrik)
    {
        this.metrik = metrik;
    }
    
    
    public Ort getOrt()
    {
        return ort;
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder(Strings.CRLF);
        stringBuilder.append(getOrt().toString());
        
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(getTaxonomie().toString());
        
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(getMetrik().toString());
        
        
        return stringBuilder.toString();
    }
    
    
    @Override
    public int compareTo(@NotNull Baum o)
    {
        Metrik thisMetrik;
        Metrik otherMetrik;
        float comparator;
        if ((comparator = (thisMetrik = getMetrik()).getHoeheMeter() - (otherMetrik = o.getMetrik()).getHoeheMeter()) != 0)
        {
        }
        else if ((comparator = thisMetrik.getUmfangZentimeter() - otherMetrik.getUmfangZentimeter()) != 0)
        {
        
        }
        else if ((comparator = thisMetrik.getKroneMeter() - otherMetrik.getKroneMeter()) != 0)
        {
        }
        
        if(comparator>0)
        {
            comparator=Konstanten.EINS;
        }
        else if (comparator<0)
        {
            comparator=Konstanten.MINUSEINS;
        }
        
        return (int)comparator;
    }
    
    
    public int compareTo(@NotNull Baum o, int attribut)
    {
        
        if (attribut == KatasterController.HOEHE_METER_VERGLEICHEN)
        {
            return BaumController.hoeheMeterVergleichen(this,o);
        }
        else if (attribut == KatasterController.UMFANG_ZENTIMETER_VERGLEICHEN)
        {
            return BaumController.umfangZentimeterVergleichen(this,o);
        }
        else if (attribut == KatasterController.KRONE_METER_VERGLEICHEN)
        {
            return BaumController.kroneMeterVergleichen(this,o);
        }
        else if (attribut == KatasterController.ALTER_VERGLEICHEN)
        {
            return BaumController.alterVergleichen(this,o);
        }
        
        
        throw new IllegalArgumentException();
    }
    
    
}
