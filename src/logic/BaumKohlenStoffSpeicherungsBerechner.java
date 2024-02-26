package logic;

import model.Baum;
import model.BaumKataster;
import model.LineareFunktion;
import model.Metrik;
import resources.Strings;

import java.util.HashMap;

import static resources.BaumKohlenStoffSpeicherungFunktionen.*;

public class BaumKohlenStoffSpeicherungsBerechner
{
    
    
    private final float PI = 3.1415927f;
    
    
    private final HashMap<String,LineareFunktion> funktionen;
    
    
    public BaumKohlenStoffSpeicherungsBerechner()
    {
        HashMap<String,LineareFunktion> funktionen = new HashMap<>();
        funktionen.put(BOTANISCHE_GATTUNG_FICHTE, FICHTE);
        funktionen.put(BOTANISCHE_GATTUNG_KIEFER, KIEFER);
        funktionen.put(BOTANISCHE_GATTUNG_BUCHE, BUCHE);
        funktionen.put(BOTANISCHE_GATTUNG_EICHE, EICHE);
        funktionen.put(Strings.UNBEKANNT, new LineareFunktion((FICHTE.steigung()+KIEFER.steigung()+BUCHE.steigung()+EICHE.steigung())/ funktionen.size(),0));
        
        
        this.funktionen = funktionen;
    }
    
    
    public HashMap<String, LineareFunktion> getFunktionen()
    {
        return funktionen;
    }
    
    
    public HashMap<Integer, Float> berechneSpeicherungInKiloGrammFuerBaumKataster(BaumKataster baumKataster)
    {
        HashMap<Integer, Float> gespeicherterKohlenStoffNachBaumID = new HashMap<>();
        HashMap<Integer,Baum> baumHashMap=baumKataster.getBaumHashMap();
        
        for (Integer key  :baumHashMap.keySet() )
        {
            gespeicherterKohlenStoffNachBaumID.put(key, speicherungInKiloGrammFuerEinzelBaumBerechnen(baumHashMap.get(key)));
        }
        
        
        return gespeicherterKohlenStoffNachBaumID;
    }
    
    
    
    public float speicherungInKiloGrammFuerEinzelBaumBerechnen(Baum baum)
    {
        HashMap<String,LineareFunktion> funktionen = getFunktionen();
        LineareFunktion funktion = funktionen.get(baum.getTaxonomie().getGattungBotanisch());
        if(funktion == null)
        {
            funktion = funktionen.get(Strings.UNBEKANNT);
        }
        
        Metrik metrik = baum.getMetrik();
        float durchMesser=metrik.getUmfangZentimeter()/PI;
        float hoehe=metrik.getHoeheMeter();
        
        float speicherung=funktion.fVonX(durchMesser*durchMesser*hoehe);
        
        if (0>speicherung)
        {
            return 0;
        }
        
        return speicherung;
    }
    
    
}
