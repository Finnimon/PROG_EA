package Model;

import Resources.Messages;
import Resources.Strings;


/**
 * @param objectName The name of the specific location.
 * @param district The name of the district in which the {@link #objectName} is located.
 * @Summary: The {@link Site} record represents the mapping of a site or location of a physical object within a city or anything else divided into districts to an {@link #objectName} within a {@link #district}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public record Site(String objectName, String district) implements Cloneable
{
    
    
    /**
     * @return {@link #objectName}
     * @Summary: Getter for {@link #objectName}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public String objectName()
    {
        return objectName;
    }
    
    
    /**
     * @return {@link #district}
     * @Summary: Getter for {@link #district}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public String district()
    {
        return district;
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
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.OBJEKTNAME);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(objectName());
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.BEZIRK);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(district());
        
        
        return stringBuilder.toString();
    }
    
    
    /**
     * @return A clone of this {@link Site}.
     * @Precondition: None.
     * @Postcondition: The returned {@link Site} is a deep copy of this {@link Site}.
     * @Summary: Clones this {@link Site}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public Site clone()
    {
        try
        {
            super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        String objectName = new String(objectName().getBytes());
        
        return new Site(new String(objectName().getBytes()), new String(district().getBytes()));
    }
}
