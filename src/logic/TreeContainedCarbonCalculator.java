package logic;

import model.Tree;
import model.BaumKataster;
import model.LinearFunction;
import model.Metrik;
import resources.Strings;

import java.util.HashMap;

import static resources.BaumKohlenStoffSpeicherungFunktionen.*;


/**
 * @Summary: This class offers instance methods for calculating the carbon contained by a tree. The collection of {@link LinearFunction} used for this are stored in {@link resources.BaumKohlenStoffSpeicherungFunktionen}.
 * @Author: Finn Lindig
 * @Since: Finn Lindig
 */
public class TreeContainedCarbonCalculator
{
    
    
    private final float PI = 3.1415927f;
    
    
    private final HashMap<String, LinearFunction> functions;
    
    
    public TreeContainedCarbonCalculator()
    {
        HashMap<String, LinearFunction> functions = new HashMap<>();
        functions.put(BOTANICAL_GENUS_SPRUCE, SPRUCE);
        functions.put(BOTANICAL_GENUS_PINE, PINE);
        functions.put(BOTANICAL_GENUS_BEECH, BEECH);
        functions.put(BOTANICAL_GENUS_OAK, OAK);
        functions.put(Strings.UNBEKANNT, new LinearFunction((SPRUCE.slope()+ PINE.slope()+ BEECH.slope()+ OAK.slope())/ functions.size(),0));
        
        
        this.functions = functions;
    }
    
    
    public HashMap<String, LinearFunction> getFunctions()
    {
        return functions;
    }
    
    
    public HashMap<Integer, Float> berechneSpeicherungInKiloGrammFuerBaumKataster(BaumKataster baumKataster)
    {
        HashMap<Integer, Float> gespeicherterKohlenStoffNachBaumID = new HashMap<>();
        HashMap<Integer, Tree> baumHashMap=baumKataster.getBaumHashMap();
        
        for (Integer key  :baumHashMap.keySet() )
        {
            gespeicherterKohlenStoffNachBaumID.put(key, speicherungInKiloGrammFuerEinzelBaumBerechnen(baumHashMap.get(key)));
        }
        
        
        return gespeicherterKohlenStoffNachBaumID;
    }
    
    
    
    public float speicherungInKiloGrammFuerEinzelBaumBerechnen(Tree tree)
    {
        HashMap<String, LinearFunction> funktionen = getFunctions();
        LinearFunction funktion = funktionen.get(tree.getTaxonomie().getGattungBotanisch());
        if(funktion == null)
        {
            funktion = funktionen.get(Strings.UNBEKANNT);
        }
        
        Metrik metrik = tree.getMetrik();
        float durchMesser=metrik.getUmfangZentimeter()/PI;
        float hoehe=metrik.getHoeheMeter();
        
        float speicherung=funktion.f(durchMesser*durchMesser*hoehe);
        
        if (0>speicherung)
        {
            return 0;
        }
        
        return speicherung;
    }
    
    
}
