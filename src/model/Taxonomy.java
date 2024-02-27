package model;

import Services.BaumServices;
import resources.Messages;
import resources.Strings;
import utility.Core;

import java.util.ArrayList;
import java.util.List;

public class Taxonomy implements Cloneable
{
    
    
    private final int INDEX_SPECIES_GERMAN = 0;
    
    private final int INDEX_SPECIES_BOTANICAL = 1;
    
    private final int INDEX_GENUS_GERMAN = 2;
    
    private final int INDEX_GENUS_BOTANICAL = 3;
    
    
    private final String speciesBotanical;
    
    
    private final String speciesGerman;
    
    
    private final String genusBotanical;
    
    
    private final String genusGerman;
    
    
    public Taxonomy(String speciesBotanical, String speciesGerman, String genusBotanical, String genusGerman)
    {
        this.speciesBotanical = speciesBotanical;
        this.speciesGerman = speciesGerman;
        this.genusBotanical = genusBotanical;
        this.genusGerman = genusGerman;
    }
    
    
    public Taxonomy(List<String> stringList)
    {
        this.speciesGerman = formatAttributeString(stringList.get(INDEX_SPECIES_GERMAN));
        this.speciesBotanical = formatAttributeString(stringList.get(INDEX_SPECIES_BOTANICAL));
        this.genusGerman = formatAttributeString(stringList.get(INDEX_GENUS_GERMAN));
        this.genusBotanical = formatAttributeString(stringList.get(INDEX_GENUS_BOTANICAL));
    }
    
    
    
    public String getSpeciesBotanical()
    {
        return speciesBotanical;
    }
    
    
    public String getSpeciesGerman()
    {
        return speciesGerman;
    }
    
    
    public String getGenusBotanical()
    {
        return genusBotanical;
    }
    
    
    public String getGenusGerman()
    {
        return genusGerman;
    }
    
    
    public ArrayList<String> getAll()
    {
        ArrayList<String> werte = new ArrayList<>();

        werte.add(getSpeciesGerman());
        werte.add(getSpeciesBotanical());
        werte.add(getGenusGerman());
        werte.add(getGenusBotanical());
        
        
        return werte;
    }
    
    
    private String formatAttributeString(String string)
    {
        return BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(Core.capitalizeAndTrimString(string));
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.TREE_SPECIES);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getSpeciesGerman());
        stringBuilder.append(Strings.FORWARD_SLASH);
        stringBuilder.append(getSpeciesBotanical());
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(Messages.TREE_GENUS);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getGenusGerman());
        stringBuilder.append(Strings.FORWARD_SLASH);
        stringBuilder.append(getGenusBotanical());
        
        
        return stringBuilder.toString();
    }
    
    
    @Override
    protected Taxonomy clone()
    {
        return new Taxonomy(this.getAll());
    }
}
