package resources;

import model.LinearFunction;


public interface BaumKohlenStoffSpeicherungFunktionen
{
    
    
    float KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID=1f/3.67f;
    
    
    String BOTANICAL_GENUS_SPRUCE ="PICEA";
    
    
    LinearFunction SPRUCE =new LinearFunction(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*5000f/(60*60*42),0f);
   
    
    String BOTANICAL_GENUS_PINE ="PINUS";
    
    
    LinearFunction PINE =new LinearFunction(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*5700f/(60*60*42),0f);
   
    
    String BOTANICAL_GENUS_BEECH ="FAGUS";
    
    
    LinearFunction BEECH =new LinearFunction(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*8000f/(60*60*42),0f);
   
    
    String BOTANICAL_GENUS_OAK ="QUERCUS";
    
    
    LinearFunction OAK =new LinearFunction(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*7300f/(60*60*42),0f);
    
    
}
