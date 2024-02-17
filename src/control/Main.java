package control;

import model.BaumKataster;
import model.CSVRecord;
import resources.Konstanten;
import resources.Strings;
import utility.CSVParser;
import utility.MyIO;

import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        
        //todo init mit zeitgeberreset
        File file = null;
        
        file = MyIO.dateipfadValidieren(resources.Strings.DATEIPFAD);
        
        
        MyIO.printLn(Strings.AUSGABE_LESE_AUS_DATEI + Strings.DATEIPFAD, false);
        
        CSVParser cSVParser = new CSVParser(Strings.SEMIKOLON, Konstanten.ZWOELF);
        ArrayList<CSVRecord> cSVRecords = cSVParser.parse(file, false);
        
        
        MyIO.printLn(Strings.AUSGABE_ERFOLGREICH_DATEI_GELESEN + cSVRecords.size(), true);
        //Because ObjectID ObjectName and Bezirk are always assigned a value
        // the defacto values that make up a tree are only nine in number therefore anything with that many can be disregarded
        cSVParser = CSVController.recordsOhneGenugWerteEntfernen(cSVParser, 7);
        cSVRecords = cSVParser.getCSVRecords();
        
        BaumKataster baumKataster = new BaumKataster(cSVRecords);
        MyIO.printLn(Strings.AUSGABE_ERZEUGTER_BAUM_INSTANZEN + baumKataster.getBaumKataster().keySet().size(), true);
        
        MyIO.fragenStellenBeantworten(baumKataster);
    }
    
}
