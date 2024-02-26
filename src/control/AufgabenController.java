package control;

import model.BaumKataster;
import model.CSVRecord;
import resources.Konstanten;
import resources.Messages;
import resources.Strings;
import utility.CSVParser;
import utility.MyIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AufgabenController
{
    
    
    private final ArrayList<Float> argumente;
    
    
    public AufgabenController(ArrayList<Float > argumente)
    {
        this.argumente = argumente;
    }
    
    
    public BaumKataster aufgabeEins()
    {
        MyIO.printLn(Messages.AUSGABE_PROGRAMM_INITIALISIERT,true);// Message String
        
        File file = MyIO.dateipfadValidieren(resources.Strings.DATEIPFAD);
        MyIO.printLn(Messages.AUSGABE_LESE_AUS_DATEI + Strings.DATEIPFAD, false);
        CSVParser cSVParser;
        
        try
        {
            cSVParser= new CSVParser(Strings.DATEIPFAD, Strings.SEMIKOLON, Konstanten.ZWOELF);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        ArrayList<CSVRecord> cSVRecords = cSVParser.parse(false);
        
        MyIO.printLn(Messages.AUSGABE_ANZAHL_ERFOLGREICH_EINGELESENE_ZEILEN + cSVRecords.size(), true);
        
        BaumKataster baumKataster = new BaumKataster(cSVRecords,Main.ARGUMENTE);
        MyIO.printLn(Messages.AUSGABE_ANZAHL_ERZEUGTER_BAUM_INSTANZEN + baumKataster.getBaumHashMap().keySet().size(), true);
        
        return baumKataster;
    }
    
    
    public BaumKataster aufgabeZwei(BaumKataster baumKataster)
    {
        StatisticalDataRepairCenter statisticalDataRepairCenter = new StatisticalDataRepairCenter(baumKataster);
        
        statisticalDataRepairCenter.shallowRepair();
        
        MyIO.printLn(Messages.AUSGABE_ANZAHL_GELOESCHTE_DATENSAETZE+baumKataster.getDeletedDataSetKeys().size(),true);
        
        BaumKataster shallowRepairedBaumKataster= (BaumKataster) statisticalDataRepairCenter.getShallowRepairedStatistic().clone();
        
        statisticalDataRepairCenter.deepRepair();
        
        MyIO.printLn(Messages.AUSGABE_ANZAHL_BEARBEITETE_DATENSAETZE+baumKataster.getEditedDataSetKeys().size(),true);
        
        
        return shallowRepairedBaumKataster;
    }
    
    
    public void aufgabeDreiUndFuenf(BaumKataster shallowRepairedBaumKataster, BaumKataster deepRepairedBaumKataster)
    {
        MyIO.fragenStellenBeantworten(shallowRepairedBaumKataster,deepRepairedBaumKataster);
    }
    
    
    
}
