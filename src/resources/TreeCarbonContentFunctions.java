package resources;

import model.LinearFunction;


/**
 * @Summary: This interface contains the {@link LinearFunction}s and their mapping that can be used to calculate the carbon content of a {@link model.Tree} based on its {@link model.Metric}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public interface TreeCarbonContentFunctions
{
    
    
    float CARBON_PER_CARBONDIOXIDE =1f/3.67f;
    
    
    String BOTANICAL_GENUS_SPRUCE ="PICEA";
    
    
    LinearFunction SPRUCE =new LinearFunction(CARBON_PER_CARBONDIOXIDE *5000f/(60*60*42),0f);
   
    
    String BOTANICAL_GENUS_PINE ="PINUS";
    
    
    LinearFunction PINE =new LinearFunction(CARBON_PER_CARBONDIOXIDE *5700f/(60*60*42),0f);
   
    
    String BOTANICAL_GENUS_BEECH ="FAGUS";
    
    
    LinearFunction BEECH =new LinearFunction(CARBON_PER_CARBONDIOXIDE *8000f/(60*60*42),0f);
   
    
    String BOTANICAL_GENUS_OAK ="QUERCUS";
    
    
    LinearFunction OAK =new LinearFunction(CARBON_PER_CARBONDIOXIDE *7300f/(60*60*42),0f);
    
    
}
