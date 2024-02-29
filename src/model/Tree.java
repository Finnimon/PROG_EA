package model;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Strings;
import utility.Core;
import utility.ElementFaultyException;
import utility.TreeComparator;
import utility.DataRepair.iRepairable;

import java.util.ArrayList;
import java.util.List;


/**
 * @Summary: The {@link Tree} class represents a tree (plant) with its {@link #site}, {@link #taxonomy} and {@link #metric}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class Tree implements Comparable<Tree>, iRepairable
{
    
    
    private static final int INDEX_BEGINN_METRIK = 5;
    
    
    private static final int INDEX_BEGINN_TAXONOMIE = 1;
    
    
    private static final int INDEX_ENDE_TAXONOMIE = INDEX_BEGINN_METRIK;
    
    
    private static final int INDEX_ENDE_METRIK = 10;
    
    
    private static final int NOTWENDIGE_ZEILENLAENGE = 11;
    
    
    private static final int INDEX_BEGINN_BAUM = 1;
    
    
    private static final int INDEX_ENDE_BAUM = Constants.CSV_RECORD_LENGTH;
    
    
    /**
     * @Summary: The {@link Site} of this {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final Site site;
    
    
    /**
     * @Summary: The {@link Taxonomy} of this {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final Taxonomy taxonomy;
    
    
    /**
     * @Summary: The {@link Metric} of this {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private Metric metric;
    
    
    /**
     * @param site     The {@link Site} of this {@link Tree}.
     * @param taxonomy The {@link Taxonomy} of this {@link Tree}.
     * @param metric   The {@link Metric} of this {@link Tree}.
     * @Precondition: None.
     * @Postcondition: The {@link Tree} is created. It may contain null values.
     * @Summary: Standard constructor for {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private Tree(Site site, Taxonomy taxonomy, Metric metric)
    {
        this.site = site;
        this.taxonomy = taxonomy;
        setMetric(metric);
    }
    
    
    /**
     * @param stringList the list that contains the string values of the {@link Tree}.
     * @throws ElementFaultyException if stringlist.size() != {@link #NOTWENDIGE_ZEILENLAENGE}.
     * @Precondition: The stringList contains {@link #NOTWENDIGE_ZEILENLAENGE} elements.
     * @Postcondition: The {@link Tree} is created. Its values will not be null.
     * @Summary: Constructor for {@link Tree} that parses a list of strings into a {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private Tree(List<String> stringList) throws ElementFaultyException
    {
        if (stringList.size() != NOTWENDIGE_ZEILENLAENGE)
        {
            throw new ElementFaultyException();
        }
        
        this.site = new Site(stringList.getFirst(), stringList.getLast());
        this.taxonomy = new Taxonomy( stringList.subList(INDEX_BEGINN_TAXONOMIE, INDEX_ENDE_TAXONOMIE));
        setMetric(new Metric(stringList.subList(INDEX_BEGINN_METRIK, INDEX_ENDE_METRIK)));
    }
    
    
    /**
     * @param stringList the list that contains the string values of the {@link Tree}.
     * @return a new {@link Tree} with the values of the stringList.
     * @throws ElementFaultyException if stringlist.size() != {@link #NOTWENDIGE_ZEILENLAENGE}.
     * @Precondition: The stringList contains {@link #NOTWENDIGE_ZEILENLAENGE} elements.
     * @Postcondition: The {@link Tree} is created. Its values will not be null, it will not throw an {@link ElementFaultyException}.
     * @Summary: Factory method for {@link Tree} that parses a list of strings into {@link #Tree(List)} and returns it.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Contract("_ -> new")
    public static @NotNull Tree create(@NotNull List<String> stringList) throws ElementFaultyException
    {
        return new Tree(stringList);
    }
    
    
    /**
     * @param site     The {@link Site} of the return {@link Tree}.
     * @param taxonomy The {@link Taxonomy} of the return {@link Tree}.
     * @param metric   The {@link Metric} of the return {@link Tree}.
     * @return a new {@link Tree} with the values of the stringList.
     * @Precondition: None.
     * @Postcondition: The {@link Tree} is created. Its values may be null.
     * @Summary: Factory method for {@link Tree} that passes its params into {@link #Tree(Site, Taxonomy, Metric)} and returns it.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Contract("_, _, _ -> new")
    public static @NotNull Tree create(Site site, Taxonomy taxonomy, Metric metric)
    {
        return new Tree(site, taxonomy, metric);
    }
    
    
    /**
     * @param cSVRecord the {@link CSVRecord@} that contains the string values of the {@link Tree}.
     * @return a new {@link Tree} with the values of the stringList.
     * @throws ElementFaultyException if stringlist.size() != {@link #NOTWENDIGE_ZEILENLAENGE}.
     * @Precondition: The param {@link CSVRecord} contains at least {@link #NOTWENDIGE_ZEILENLAENGE} elements.
     * @Postcondition: The {@link Tree} is created. Its values will not be null, it will not throw an exception.
     * @Summary: Factory method for {@link Tree} that parses {@link CSVRecord#getRecord()} into {@link #Tree(List)} and returns it.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static Tree create(@NotNull CSVRecord cSVRecord) throws ElementFaultyException
    {
        return new Tree(cSVRecord.getRecord().subList(INDEX_BEGINN_BAUM, INDEX_ENDE_BAUM));
    }
    
    
    /**
     * @return {@link #taxonomy}.
     * @Summary: Getter for {@link #taxonomy}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public Taxonomy getTaxonomy()
    {
        return this.taxonomy;
    }
    
    
    /**
     * @return {@link #metric}.
     * @Summary: Getter for {@link #metric}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public Metric getMetric()
    {
        return this.metric;
    }
    
    
    /**
     * @Summary: Setter for {@link #metric}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void setMetric(Metric metric)
    {
        this.metric = metric;
    }
    
    
    /**
     * @return {@link #site}.
     * @Summary: Getter for {@link #site}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public Site getSite()
    {
        return site;
    }
    
    
    /**
     * @return This {@link Site} as a String suitable for output.
     * @Summary: Returns the attributes withing this class as an easily readable String.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder(Strings.CRLF);
        stringBuilder.append(getSite().toString());
        
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(getTaxonomy().toString());
        
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(getMetric().toString());
        
        
        return stringBuilder.toString();
    }
    
    
    /**
     * @param otherTree the {@link Tree} to be compared to.
     * @return If this tree is larger than the otherTree return 1, if they are equal return 0, if this tree is smaller than the otherTree return -1.
     * @Precondition: None.
     * @Postcondition: Return 1 if this tree is larger than the otherTree, 0 if they are equal, -1 if this tree is smaller than the otherTree. If only one of the trees is null assume it as less than any not null tree. If both are null the return is 0.
     * @Summary: Implementation of {@link Comparable#compareTo(Object)}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public int compareTo(Tree otherTree)
    {
        return new TreeComparator(TreeComparator.INDEX_HEIGHT_METERS).compare(this, otherTree);
    }
    
    
    /**
     * @return the repairables of the {@link #metric}.
     * @Precondition: Metric is not null.
     * @Postcondition: Return the repairables of the {@link #metric}.
     * @Summary: Implementation of {@link iRepairable#getRepairables()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public ArrayList<Float> getRepairables()
    {
        return getMetric().getRepairables();
    }
    
    
    /**
     * @param repaireds the new repairables of the {@link #metric}.
     * @Precondition: Metric is not null.
     * @Postcondition: Calls {@link Metric#setRepairables(ArrayList)}.
     * @Summary: Implementation of {@link iRepairable#getRepairables()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public void setRepairables(ArrayList<Float> repaireds)
    {
        Metric metric = getMetric();
        metric.setRepairables(repaireds);
        setMetric(metric);
    }
    
    
    /**
     * @return Whether both the {@link #metric} and {@link #taxonomy} only contain {@link Strings#UNBEKANNT} or {@link Constants#UNBEKANNT}.
     * @Precondition: Both {@link #metric} and {@link #taxonomy} are not null.
     * @Postcondition: Return whether both the {@link #metric} and {@link #taxonomy} only contain {@link Strings#UNBEKANNT} or {@link Constants#UNBEKANNT}.
     * @Summary: Implementation of {@link iRepairable#isEmpty()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public boolean isEmpty()
    {
        return getMetric().isEmpty() & Core.areAllValuesInCollectionEqualToSpecificValue(getTaxonomy().getAll(), Strings.UNBEKANNT);
    }
    
    
    /**
     * @return deep copy of this {@link Tree}
     * @Precondition: None
     * @Postcondition: The returned {@link Tree} is a deep copy of this {@link Tree}.
     * @Summary: Clones this {@link Tree}. The return is a deep copy.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public Tree clone()
    {
        try
        {
            super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        
        
        return new Tree(getSite().clone(), getTaxonomy().clone(), getMetric().clone());
    }
    
    
}
