package model;

import resources.Strings;

import java.util.ArrayList;
import java.util.Arrays;

public class CSVRecord
{
    //    private final char delimiter;
    //    private final int zeilenlaenge;
    private static final String REGEX_SKIP = "\"";
    private ArrayList<String> record;
    
    
    public CSVRecord(String zeile, String seperator, int zeilenlaenge) throws RecordShortException
    {
        //        this.seperator=seperator;
        //        this.zeilenlaenge=zeilenlaenge;
        
        ArrayList<String> record = new ArrayList<>(Arrays.asList(zeile.split(seperator)));
        if (record.size() == zeilenlaenge)
        {
            setRecord(record);
            
            return;
        }
        
        if (record.size() > zeilenlaenge)
        {
            StringBuilder stringBuilder = new StringBuilder();
            String wert;
            for (int i = 0; i < record.size(); i++)
            {
                if ((wert = record.get(i)).startsWith(REGEX_SKIP))
                {
                    stringBuilder.append(wert);
                    
                    stringBuilder.append(seperator);
                    stringBuilder.append(record.get(i + 1));
                    wert = stringBuilder.toString();
                    
                    record.remove(i + 1);
                    if (wert.endsWith(REGEX_SKIP))
                    {
                        wert.replace(REGEX_SKIP, Strings.EMPTY);
                        
                        continue;
                    }
                    else
                    {
                        i--;
                    }
                    record.set(i, wert);
                }
            }
        }
        
        if (record.size() != zeilenlaenge)
        {
            throw new RecordShortException();
        }
        
        
        setRecord(record);
    }
    
    
    public ArrayList<String> getRecord()
    {
        return record;
    }
    
    
    public void setRecord(ArrayList<String> record)
    {
        this.record = record;
    }
    
    
    
    //
    //    public char getDelimiter()
    //    {
    //        return delimiter;
    //    }
    //
    //
    //    public int getZeilenlaenge()
    //    {
    //        return zeilenlaenge;
    //    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String wert : getRecord())
        {
            stringBuilder.append(wert);
            stringBuilder.append(Strings.SEMIKOLON);
        }
       
        return  stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }
}
