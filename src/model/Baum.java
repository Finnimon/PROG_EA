package model;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;
import utility.ElementFaultyException;

import java.util.List;



public class Baum implements Comparable<Baum>
{
    
    
    //region[Konstanten]
    
    
    private static final int INDEX_BEGINN_METRIK = Konstanten.FUENF;
    private static final int INDEX_BEGINN_TAXONOMIE = Konstanten.EINS;
    private static final int INDEX_ENDE_TAXONOMIE = INDEX_BEGINN_METRIK;
    private static final int INDEX_ENDE_METRIK = Konstanten.ZEHN;
    private static final int NOTWENDIGE_ZEILENLAENGE = Konstanten.ELF;
    private static final int INDEX_BEGINN_BAUM = Konstanten.EINS;
    private static final int INDEX_ENDE_BAUM = Konstanten.ZWOELF;
    
    
    //endregion
    //region [Attribute]
    private final Ort ort;
    private final Taxonomie taxonomie;
    private Metrik metrik;
    
    //endregion
    //region[create]
    
    
    @Contract("_ -> new")
    public static @NotNull Baum create(List<String> record) throws ElementFaultyException
    {
        return new Baum(record);
    }
    
    
    @Contract("_, _, _ -> new")
    public static @NotNull Baum create(Ort ort, Taxonomie taxonomie, Metrik metrik)
    {
        return new Baum(ort, taxonomie, metrik);
    }
    
    
    public static Baum create(@NotNull CSVRecord cSVRecord)throws ElementFaultyException
    {
        return new Baum(cSVRecord.getRecord().subList(INDEX_BEGINN_BAUM, INDEX_ENDE_BAUM));
    }
    
    //endregion
    //region[Konstruktoren]
    
    
    private Baum(Ort ort, Taxonomie taxonomie, Metrik metrik)
    {
        this.ort = ort;
        this.taxonomie = taxonomie;
        setMetrik(metrik);
    }
    
    
    private Baum(List<String> record) throws ElementFaultyException
    {
        if (record.isEmpty() | record.size() != NOTWENDIGE_ZEILENLAENGE)
        {
            throw new ElementFaultyException();
        }
        //todo both empty
        this.ort = new Ort(record.getFirst(), record.getLast());
        List<String> taxonomieList = record.subList(INDEX_BEGINN_TAXONOMIE, INDEX_ENDE_TAXONOMIE);
        this.taxonomie = new Taxonomie(taxonomieList);
        List<String> metrikList = record.subList(INDEX_BEGINN_METRIK, INDEX_ENDE_METRIK);
        setMetrik(new Metrik(metrikList));
    }
    

    //endregion
    //region[GetSet]
    
    
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
    
    
    //endregion
    //region[Overrides]
    
    
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
        //todo voll scheiße
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
        
        if (comparator > 0)
        {
            return Konstanten.EINS;
        }
        else if (comparator < 0)
        {
            return Konstanten.MINUSEINS;
        }
        
        return 0;
    }
    
    
    //endregion
    
    
}
