package control;

import model.CSVRecord;
import model.Kataster;
import resources.Konstanten;
import resources.Strings;
import utility.CSVParser;
import utility.MyIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        
        //todo init mit zeitgeberreset
        File file = null;

        try
        {
            file = MyIO.dateipfadValidieren(resources.Strings.DATEIPFAD);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e.getCause().getMessage());
        }
        MyIO.printLn(Strings.AUSGABE_LESE_AUS_DATEI + Strings.DATEIPFAD, false);
        
        CSVParser cSVParser=new CSVParser(Strings.SEMIKOLON, Konstanten.ZWOELF);
        ArrayList<CSVRecord> cSVRecords=cSVParser.parse(file,false);
        
        
        MyIO.printLn(Strings.AUSGABE_ERFOLGREICH_DATEI_GELESEN + cSVRecords.size(), true);
        //Because ObjectID ObjectName and Bezirk are always assigned a value
        // the defacto values that both make up a tree are only nine in number therefore anything
        cSVParser=CSVController.recordsOhneGenugWerteEntfernen( cSVParser,7);
        cSVRecords=cSVParser.getCSVRecords();

        Kataster kataster = new Kataster(cSVRecords);
        MyIO.printLn(Strings.AUSGABE_ERZEUGTER_BAUM_INSTANZEN + kataster.getKataster().keySet().size(), true);

        MyIO.fragenStellenBeantworten(kataster);
    }
    
}
