package resources;

import model.LineareFunktion;

public interface BaumKohlenSpeicherungFunktionen
{
    
    
    String BOTANISCHE_GATTUNG_FICHTE="PICEA";
    
    LineareFunktion FICHTE =new LineareFunktion(5000f/(60*60*42),0f);
   
    
    String BOTANISCHE_GATTUNG_KIEFER="PINUS";
    
    LineareFunktion KIEFER =new LineareFunktion(5700f/(60*60*42),0f);
   
    
    String BOTANISCHE_GATTUNG_BUCHE="FAGUS";
    
    LineareFunktion BUCHE =new LineareFunktion(8000f/(60*60*42),0f);
   
    
    String BOTANISCHE_GATTUNG_EICHE="QUERCUS";
    LineareFunktion EICHE =new LineareFunktion(7300f/(60*60*42),0f);
}
