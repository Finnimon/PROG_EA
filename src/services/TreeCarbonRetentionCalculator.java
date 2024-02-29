package services;

import model.Tree;
import model.LinearFunction;
import model.Metric;
import resources.TreeCarbonContentFunctions;
import resources.Strings;

import java.util.HashMap;

import static resources.TreeCarbonContentFunctions.*;


/**
 * @Summary: This class offers instance methods for calculating the carbon contained by a tree. The collection of {@link LinearFunction} used for this are stored in {@link TreeCarbonContentFunctions}.
 * @Author: Finn Lindig
 * @Since: Finn Lindig
 */
public class TreeCarbonRetentionCalculator
{
    
    
    /**
     * @Summary: The {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter mapped to its botanical genus.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final HashMap<String, LinearFunction> functions;
    
    
    /**
     * @Precondition: The {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter are stored in {@link TreeCarbonContentFunctions}.
     * @Postcondition: {@link #functions} is initialized with the {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter.
     * @Summary: The {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public TreeCarbonRetentionCalculator()
    {
        HashMap<String, LinearFunction> functions = new HashMap<>();
        functions.put(BOTANICAL_GENUS_SPRUCE, SPRUCE);
        functions.put(BOTANICAL_GENUS_PINE, PINE);
        functions.put(BOTANICAL_GENUS_BEECH, BEECH);
        functions.put(BOTANICAL_GENUS_OAK, OAK);
        functions.put(Strings.UNBEKANNT, new LinearFunction((SPRUCE.slope()+ PINE.slope()+ BEECH.slope()+ OAK.slope())/ functions.size(),0));
        
        
        this.functions = functions;
    }
    
    
    /**
     * @return The {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter mapped to its botanical genus.
     * @Precondition: {@link #functions} is initialized with the {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter.
     * @Postcondition: Returns the {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter.
     * @Summary: Getter for {@link #functions}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public HashMap<String, LinearFunction> getFunctions()
    {
        return functions;
    }
    
    
    /**
     *
     * @param tree The {@link Tree} for which the carbon retention should be calculated.
     * @return The approximate carbon retention of the param {@link Tree} in kilogram.
     * @Precondition: {@link #functions} is initialized with the {@link LinearFunction}s used for calculating the carbon retention of a tree proportional to height*diameter*diameter.
     * @Postcondition: The approximate carbon retention of the param tree is calculated and returned.
     * @Summary: Calculates the carbon retention of a tree in kilogram.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public float calculateRetention(Tree tree)
    {
        HashMap<String, LinearFunction> functions = getFunctions();
        LinearFunction function = functions.get(tree.getTaxonomy().getGenusBotanical());
        if(function == null)
        {
            function = functions.get(Strings.UNBEKANNT);
        }
        
        Metric metric = tree.getMetric();
        float diameter= metric.getCircumferenceCentimeters()/(float)Math.PI;
        float height= metric.getHeightMeters();
        
        float retention=function.f(diameter*diameter*height);
        
        if (0>retention)
        {
            return 0f;
        }
        
        return retention;
    }
    
    
}
