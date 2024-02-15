package utility;

import model.CSVRecord;
import model.RecordShortException;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVParser extends Parser//todo https://datatracker.ietf.org/doc/html/rfc4180 quelle
{
    //todo mit scanner umschrieben
    
    private final String SEPERATOR;
    private final int ZEILENLAENGE;
    private ArrayList<ArrayList<String>> werte;
    
    private ArrayList<CSVRecord> cSVRecords;
    
    
    public CSVParser(String SEPERATOR, int ZEILENLAENGE)
    {
        this.SEPERATOR = SEPERATOR;
        this.ZEILENLAENGE = ZEILENLAENGE;
    }
    
    
    public ArrayList<CSVRecord> parse(File file,boolean removeHeader)
    {
        ArrayList<String> cSV = MyIO.dateiZeilenweiseEinlesen(file);
        if(removeHeader) cSV.removeFirst();
        
        ArrayList<CSVRecord> cSVRecords = new ArrayList<>();
        for (int i = 0; i < cSV.size(); i++)
        {
            CSVRecord cSVRecord;
            String zeile = cSV.get(i);
            try
            {
                cSVRecord = new CSVRecord(zeile, SEPERATOR, ZEILENLAENGE);
            }
            catch (RecordShortException e)
            {
                StringBuilder stringBuilder = new StringBuilder(zeile);
                stringBuilder.append(Strings.CRLF);
                try
                {
                    cSVRecord = new CSVRecord((stringBuilder.append(cSV.get(i+1)).toString()),SEPERATOR,ZEILENLAENGE);
                    cSV.remove(i+1);
                }
                catch (Exception ex)
                {
                    continue;
                }
            }
            cSVRecords.add(cSVRecord);
        }
        setCSVRecords(cSVRecords);
        return getCSVRecords();
    }
    
    
    public ArrayList<CSVRecord> getCSVRecords()
    {
        return cSVRecords;
    }
    
    
    public void setCSVRecords(ArrayList<CSVRecord> cSVRecords)
    {
        this.cSVRecords = cSVRecords;
    }
    
    
    public ArrayList<ArrayList<String>> getWerte()
    {
        return werte;
    }
    
    
    public void setWerte(ArrayList<ArrayList<String>> werte)
    {
        this.werte = werte;
    }
    
    
    public String getSEPERATOR()
    {
        return SEPERATOR;
    }
    
    
    public int getZEILENLAENGE()
    {
        return ZEILENLAENGE;
    }
    
    
    public ArrayList<ArrayList<String>> parse(File file)
    {
        setWerte(cSVEinlesenUndValidieren(MyIO.dateiZeilenweiseEinlesen(file)));
        return getWerte();
    }
    
    
    private ArrayList<ArrayList<String>> cSVEinlesenUndValidieren(ArrayList<String> zeilen)
    {
        ArrayList<ArrayList<String>> werte = zeilenSpalten(zeilen);
        werte = zeilenLaengeValidieren(werte);
        
        
        return werte;
    }
    
    
    private ArrayList<ArrayList<String>> zeilenSpalten(ArrayList<String> zeilen)
    {
        ArrayList<ArrayList<String>> werte = new ArrayList<>();
        for (String zeile : zeilen)
        {
            werte.add(new ArrayList<>(List.of(zeile.split(SEPERATOR))));
        }
        
        
        return werte;
    }
    
    
    private ArrayList<ArrayList<String>> zeilenLaengeValidieren(@NotNull ArrayList<ArrayList<String>> werte)
    {
        //todo redundanz mehfaches laden der zeilen und i-- sowie continue
        //todo possible out of bounds
        //todo modularisieren!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        for (int i = 0; i < werte.size(); i++)
        {
            ArrayList<String> zeile = werte.get(i);
            int zeileSize = zeile.size();
            if (zeileSize == ZEILENLAENGE)
            {
            }
            else if (zeileSize == 0)
            {
                werte.remove(i);
                i--;
            }
            else if (zeileSize > ZEILENLAENGE)
            {
                StringBuffer stringBuffer = new StringBuffer(zeile.get(Konstanten.EINS));
                stringBuffer.append(SEPERATOR);
                stringBuffer.append(zeile.get(Konstanten.ZWEI));
                zeile.set(Konstanten.EINS, stringBuffer.toString().replace(Strings.ANFUEHRUNGSZEICHEN, Strings.EMPTY));
                zeile.remove(2);
                werte.set(i, zeile);
            }
            else if (zeileSize + werte.get(i + Konstanten.EINS).size() == Konstanten.ZWOELF)
            {
                zeile.addAll(werte.get(i + Konstanten.EINS));
                werte.set(i, zeile);
                werte.remove(i + Konstanten.EINS);
            }
            else if (zeileSize + werte.get(i + Konstanten.EINS).size() == Konstanten.DREIZEHN)
            {
                zeile.addAll(werte.get(i + Konstanten.EINS));
                werte.set(i, zeile);
                werte.remove(i + Konstanten.EINS);
                
                
                StringBuffer stringBuffer = new StringBuffer(zeile.get(Konstanten.EINS));
                stringBuffer.append(zeile.get(Konstanten.ZWEI));
                zeile.set(Konstanten.EINS, stringBuffer.toString().replace(Strings.ANFUEHRUNGSZEICHEN, Strings.EMPTY));
                zeile.remove(2);
                werte.set(i, zeile);
            }
            
            
        }
        
        
        return werte;
    }
    
    
}
