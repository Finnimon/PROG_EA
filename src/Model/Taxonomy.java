package Model;

import Resources.Messages;
import Resources.Strings;
import Services.TreeServices;

import java.util.ArrayList;
import java.util.List;


/**
 * @Summary: The {@link Taxonomy} class represents the taxonomy of {@link Tree} objects on the levels of species and genus. This class is also applicable to other living things. For readability purposes, the {@link Taxonomy} is defined as an attribute of {@link Tree}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class Taxonomy implements Cloneable
{
    
    /**
     * @Summary: Index used for parsing the {@link Taxonomy} from a {@link ArrayList<String>}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int INDEX_SPECIES_GERMAN = 0;
    
    
    /**
     * @Summary: Index used for parsing the {@link Taxonomy} from a {@link ArrayList<String>}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int INDEX_SPECIES_BOTANICAL = 1;
    
    
    /**
     * @Summary: Index used for parsing the {@link Taxonomy} from a {@link ArrayList<String>}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int INDEX_GENUS_GERMAN = 2;
    
    
    /**
     * @Summary: Index used for parsing the {@link Taxonomy} from a {@link ArrayList<String>}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int INDEX_GENUS_BOTANICAL = 3;
    
    
    /**
     * @Summary: The botanical species of a {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final String speciesBotanical;
    
    
    /**
     * @Summary: The German species of a {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final String speciesGerman;
    
    
    /**
     * @Summary: The botanical species of a {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final String genusBotanical;
    
    
    /**
     * @Summary: The German genus of a {@link Tree}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final String genusGerman;
    
    
    /**
     * @param speciesBotanical the value for {@link #speciesBotanical}
     * @param speciesGerman    the value for {@link #speciesGerman}
     * @param genusBotanical   the value for {@link #speciesBotanical}
     * @param genusGerman      the value for {@link #speciesBotanical}
     * @Summary: The simplest {@link Taxonomy} constructor.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public Taxonomy(String speciesBotanical, String speciesGerman, String genusBotanical, String genusGerman)
    {
        this.speciesBotanical = speciesBotanical;
        this.speciesGerman = speciesGerman;
        this.genusBotanical = genusBotanical;
        this.genusGerman = genusGerman;
    }
    
    
    /**
     * @param stringList a {@link ArrayList<String>} containing the values for all the variable fields of {@link Taxonomy}.
     * @Summary: The {@link Taxonomy} constructor.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public Taxonomy(List<String> stringList)
    {
        this.speciesGerman = TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(INDEX_SPECIES_GERMAN));
        this.speciesBotanical = TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(INDEX_SPECIES_BOTANICAL));
        this.genusGerman = TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(INDEX_GENUS_GERMAN));
        this.genusBotanical = TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(INDEX_GENUS_BOTANICAL));
    }
    
    
    /**
     * @return {@link #speciesBotanical}
     * @Summary: Getter for {@link #speciesBotanical}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public String getSpeciesBotanical()
    {
        return speciesBotanical;
    }
    
    
    /**
     * @return {@link #speciesGerman}
     * @Summary: Getter for {@link #speciesGerman}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public String getSpeciesGerman()
    {
        return speciesGerman;
    }
    
    
    /**
     * @return {@link #genusBotanical}
     * @Summary: Getter for {@link #genusBotanical}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public String getGenusBotanical()
    {
        return genusBotanical;
    }
    
    
    /**
     * @return {@link #genusGerman}
     * @Summary: Getter for {@link #genusGerman}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public String getGenusGerman()
    {
        return genusGerman;
    }
    
    
    /**
     * @return All variable fields of {@link Taxonomy} in a {@link ArrayList<String>}.
     * @Summary: Getter for all variable fields of {@link Taxonomy}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public ArrayList<String> getAll()
    {
        ArrayList<String> werte = new ArrayList<>();
        
        werte.add(getSpeciesGerman());
        werte.add(getSpeciesBotanical());
        werte.add(getGenusGerman());
        werte.add(getGenusBotanical());
        
        
        return werte;
    }
    
    
    /**
     * @return The attributes of this class in a {@link String} that is suitable for printing.
     * @Summary: @Override of {@link Object#toString()}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.BAUM_ART);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getSpeciesGerman());
        stringBuilder.append(Strings.FORWARD_SLASH);
        stringBuilder.append(getSpeciesBotanical());
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(Messages.BAUM_GATTUNG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getGenusGerman());
        stringBuilder.append(Strings.FORWARD_SLASH);
        stringBuilder.append(getGenusBotanical());
        
        
        return stringBuilder.toString();
    }
    
    /**
     * @return a deep copy of this {@link Taxonomy}.
     * @Summary: @Override for {@link Cloneable}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    protected Taxonomy clone()
    {
        try
        {
            super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        ArrayList<String> stringList = new ArrayList<>();
        for (String string: getAll()
             )
        {
            stringList.add(new String(string.getBytes()));
        }
        
        
        return new Taxonomy(stringList);
    }
}
