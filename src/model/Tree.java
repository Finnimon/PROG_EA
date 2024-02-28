package model;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Strings;
import utility.Core;
import utility.ElementFaultyException;
import utility.iRepairable;

import java.util.ArrayList;
import java.util.List;


public class Tree implements Comparable<Tree>, iRepairable
{
    
    
    //region[Constants]
    
    
    private static final int INDEX_BEGINN_METRIK = Constants.FUENF;
    private static final int INDEX_BEGINN_TAXONOMIE = Constants.EINS;
    private static final int INDEX_ENDE_TAXONOMIE = INDEX_BEGINN_METRIK;
    private static final int INDEX_ENDE_METRIK = Constants.ZEHN;
    private static final int NOTWENDIGE_ZEILENLAENGE = Constants.ELF;
    private static final int INDEX_BEGINN_BAUM = Constants.EINS;
    private static final int INDEX_ENDE_BAUM = Constants.CSV_RECORD_LENGTH;
    
    
    //endregion
    //region [Attribute]
    private final Ort ort;
    private final Taxonomy taxonomy;
    private Metric metric;
    
    //endregion
    //region[create]
    
    
    private Tree(Ort ort, Taxonomy taxonomy, Metric metric)
    {
        this.ort = ort;
        this.taxonomy = taxonomy;
        setMetrik(metric);
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
        setMetrik(new Metric(metrikList));
    }
    
    
    @Contract("_ -> new")
    public static @NotNull Tree create(List<String> record) throws ElementFaultyException
    {
        return new Tree(record);
    }
    
    //endregion
    //region[Konstruktoren]
    
    
    @Contract("_, _, _ -> new")
    public static @NotNull Tree create(Ort ort, Taxonomy taxonomy, Metric metric)
    {
        return new Tree(ort, taxonomy, metric);
    }
    
    
    public static Tree create(@NotNull CSVRecord cSVRecord) throws ElementFaultyException
    {
        return new Tree(cSVRecord.getRecord().subList(INDEX_BEGINN_BAUM, INDEX_ENDE_BAUM));
    }
    
    
    //endregion
    //region[GetSet]
    
    
    public Taxonomy getTaxonomy()
    {
        return this.taxonomy;
    }
    
    
    public Metric getMetric()
    {
        return this.metric;
    }
    
    
    public void setMetrik(Metric metric)
    {
        this.metric = metric;
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
        stringBuilder.append(getTaxonomy().toString());
        
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(getMetric().toString());
        
        
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
        return getMetric().getRepairables();
    }
    
    
    @Override
    public void setRepairables(ArrayList<Float> reparierte)
    {
        Metric metric;
        (metric = getMetric()).setRepairables(reparierte);
        setMetrik(metric);
    }
    
    
    @Override
    public boolean isEmpty()
    {
        return getMetric().isEmpty() & Core.areAllValuesInCollectionEqualToSpecificValue(getTaxonomy().getAll(), Strings.UNBEKANNT);
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
        
        
        return new Tree(getOrt().clone(), getTaxonomy().clone(), getMetric().clone());
    }
    
    
    //endregion
    
    
}
