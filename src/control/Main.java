package control;

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
        
        CSVParser csvParser= new CSVParser(Strings.SEMIKOLON, Konstanten.ZWOELF);
        ArrayList<ArrayList<String>>werte =csvParser.parse(file);
        
        MyIO.printLn(Strings.AUSGABE_ERFOLGREICH_DATEI_GELESEN + werte.size(), true);

        Kataster kataster = new Kataster(werte);
        MyIO.printLn(Strings.AUSGABE_ERZEUGTER_BAUM_INSTANZEN + kataster.getKataster().keySet().size(), true);

        MyIO.fragenStellenBeantworten(kataster);
    }
    
}
