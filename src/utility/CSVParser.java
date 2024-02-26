package utility;

import model.CSVRecord;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser extends FileParser//todo https://datatracker.ietf.org/doc/html/rfc4180 quelle
{
    
    
    private final String SEPERATOR;
    
    
    private final int ZEILENLAENGE;
    
    
    
    public CSVParser(String filePath,String SEPERATOR, int ZEILENLAENGE) throws FileNotFoundException
    {
        super(filePath);
        this.SEPERATOR = SEPERATOR;
        this.ZEILENLAENGE = ZEILENLAENGE;
    }
    
    
    public String getSEPERATOR()
    {
        return SEPERATOR;
    }
    
    
    public int getZEILENLAENGE()
    {
        return ZEILENLAENGE;
    }
    
    
    
    public ArrayList<CSVRecord> parse(boolean removeHeader)
    {
        ArrayList<String> cSV = super.parse();
        
        if(removeHeader) cSV.removeFirst();
        
        ArrayList<CSVRecord> cSVRecords = new ArrayList<>();
        for (int i = 0; i < cSV.size(); i++)
        {
            CSVRecord cSVRecord;
            String zeile = cSV.get(i);
            
            
            try
            {
                cSVRecord = new CSVRecord(zeile, getSEPERATOR(), getZEILENLAENGE());
            }
            catch (RecordShortException e)
            {
                StringBuilder stringBuilder = new StringBuilder(zeile);
                stringBuilder.append(Strings.CRLF);
                try
                {
                    cSVRecord = new CSVRecord((stringBuilder.append(cSV.get(i+1)).toString()),getSEPERATOR(),getZEILENLAENGE());
                    cSV.remove(i+1);
                }
                catch (Exception ex)
                {
                    continue;
                }
                
                
            }
            
            
            cSVRecords.add(cSVRecord);
        }
        
        
        return cSVRecords;
    }
    
    
}
