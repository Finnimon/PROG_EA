package control;

import model.Baum;
import model.BaumKataster;
import utility.Core;
import utility.MyIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Main
{
    
    
    public static void main(String[] argumente)
    {
        
        initialisieren(argumente);
        
        AufgabenController aufgabenController = new AufgabenController(ARGUMENTE);
        
        BaumKataster baumKataster=aufgabenController.aufgabeEins();
        
        BaumKataster shallowRepairedBaumKataster=aufgabenController.aufgabeZwei(baumKataster);
        //todo fragen beantworten aufgrund von deeprepair und antwort aus shallowrepair beziehen damit in ausgabe nie ausgedachte werte angezeigt werden
        aufgabenController.aufgabeDreiUndFuenf(shallowRepairedBaumKataster,baumKataster);
        
        HashSet<String>Gattungen=new HashSet<>();
        for (Baum baum  : shallowRepairedBaumKataster.getBaumHashMap().values()
             )
        {
            Gattungen.add(baum.getTaxonomie().getGattungBotanisch());
        }
        for (String gattung:Gattungen
             )
        {
            System.out.println(gattung);
        }
        
    }
    
    
    public static ArrayList<Float> ARGUMENTE;
    
    
    
    private static void initialisieren(String[] argumente)
    {
        ArrayList<Float> argumenteFloats;
        try
        {
            argumenteFloats= Core.parseStringArrayIntoFloatArrayList(argumente);
        }
        catch (NullPointerException e)
        {
            argumenteFloats=new ArrayList<>();//todo console Message and readline
        }
        catch (NumberFormatException e)
        {
            argumenteFloats=new ArrayList<>();
            //todo siehe oben
        }
        
        
        Main.ARGUMENTE =argumenteFloats;
    }
    
    
}
