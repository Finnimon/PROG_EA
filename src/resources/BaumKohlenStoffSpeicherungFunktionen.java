package resources;

import model.LineareFunktion;


public interface BaumKohlenStoffSpeicherungFunktionen
{
    
    
    float KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID=1f/3.67f;
    
    
    String BOTANISCHE_GATTUNG_FICHTE="PICEA";
    
    
    LineareFunktion FICHTE =new LineareFunktion(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*5000f/(60*60*42),0f);
   
    
    String BOTANISCHE_GATTUNG_KIEFER="PINUS";
    
    
    LineareFunktion KIEFER =new LineareFunktion(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*5700f/(60*60*42),0f);
   
    
    String BOTANISCHE_GATTUNG_BUCHE="FAGUS";
    
    
    LineareFunktion BUCHE =new LineareFunktion(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*8000f/(60*60*42),0f);
   
    
    String BOTANISCHE_GATTUNG_EICHE="QUERCUS";
    
    
    LineareFunktion EICHE =new LineareFunktion(KOHLENSTOFF_SPEICHERUNG_PRO_KOHLENSTOFFDIOXID*7300f/(60*60*42),0f);
    
    
}
