package model;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;
import utility.Core;
import utility.ElementFaultyException;
import utility.iRepairable;

import java.util.ArrayList;
import java.util.List;


public class Tree implements Comparable<Tree>, iRepairable
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
    private final Taxonomy taxonomy;
    private Metrik metrik;
    
    //endregion
    //region[create]
    
    
    private Tree(Ort ort, Taxonomy taxonomy, Metrik metrik)
    {
        this.ort = ort;
        this.taxonomy = taxonomy;
        setMetrik(metrik);
    }
    
    
    private Tree(List<String> record) throws ElementFaultyException
    {
        if (record.isEmpty() | record.size() != NOTWENDIGE_ZEILENLAENGE)
        {
            throw new ElementFaultyException();
        }
        //todo both empty
        this.ort = new Ort(record.getFirst(), record.getLast());
        List<String> taxonomieList = record.subList(INDEX_BEGINN_TAXONOMIE, INDEX_ENDE_TAXONOMIE);
        this.taxonomy = new Taxonomy(taxonomieList);
        List<String> metrikList = record.subList(INDEX_BEGINN_METRIK, INDEX_ENDE_METRIK);
        setMetrik(new Metrik(metrikList));
    }
    
    
    @Contract("_ -> new")
    public static @NotNull Tree create(List<String> record) throws ElementFaultyException
    {
        return new Tree(record);
    }
    
    //endregion
    //region[Konstruktoren]
    
    
    @Contract("_, _, _ -> new")
    public static @NotNull Tree create(Ort ort, Taxonomy taxonomy, Metrik metrik)
    {
        return new Tree(ort, taxonomy, metrik);
    }
    
    
    public static Tree create(@NotNull CSVRecord cSVRecord) throws ElementFaultyException
    {
        return new Tree(cSVRecord.getRecord().subList(INDEX_BEGINN_BAUM, INDEX_ENDE_BAUM));
    }
    
    
    //endregion
    //region[GetSet]
    
    
    public Taxonomy getTaxonomie()
    {
        return this.taxonomy;
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
    public int compareTo(@NotNull Tree o)
    {
        //todo voll scheiße
        return 0;
    }
    
    
    @Override
    public ArrayList<Float> getRepairables()
    {
        return getMetrik().getRepairables();
    }
    
    
    @Override
    public void setRepairables(ArrayList<Float> reparierte)
    {
        Metrik metrik;
        (metrik = getMetrik()).setRepairables(reparierte);
        setMetrik(metrik);
    }
    
    
    @Override
    public boolean isEmpty()
    {
        return getMetrik().isEmpty() & Core.areAllValuesInCollectionEqualToSpecificValue(getTaxonomie().getAll(), Strings.UNBEKANNT);
    }
    
    
    @Override
    public Tree clone()
    {
        try
        {
            Tree clone = (Tree) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
        }
        
        
        return new Tree(getOrt().clone(), getTaxonomie().clone(), getMetrik().clone());
    }
    
    
    //endregion
    
    
}
