package utility;

import model.CSVRecord;
import resources.Strings;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CSVParser extends FileParser//todo https://datatracker.ietf.org/doc/html/rfc4180 quelle
{
    
    
    private final String delimiter;
    
    
    private final int recordLength;
    
    
    
    public CSVParser(String filePath,String delimiter, int recordLength) throws FileNotFoundException
    {
        super(filePath);
        this.delimiter = delimiter;
        this.recordLength = recordLength;
    }
    
    
    public String getDelimiter()
    {
        return delimiter;
    }
    
    
    public int getRecordLength()
    {
        return recordLength;
    }
    
    
    
    public ArrayList<CSVRecord> parse(boolean removeHeader)
    {
        ArrayList<String> cSV = super.parse();
        
        if(removeHeader) cSV.removeFirst();
        
        ArrayList<CSVRecord> cSVRecords = new ArrayList<>();
        for (int i = 0; i < cSV.size(); i++)
        {
            CSVRecord cSVRecord;
            String line = cSV.get(i);
            
            
            try
            {
                cSVRecord = new CSVRecord(line, getDelimiter(), getRecordLength());
            }
            catch (RecordShortException e)
            {
                StringBuilder stringBuilder = new StringBuilder(line);
                stringBuilder.append(Strings.CRLF);
                try
                {
                    cSVRecord = new CSVRecord((stringBuilder.append(cSV.get(i+1)).toString()), getDelimiter(), getRecordLength());
                    cSV.remove(i+1);
                }
                catch (Exception ex)
                {
                    continue;
                } //todo
                
                
            }
            
            
            cSVRecords.add(cSVRecord);
        }
        
        
        return cSVRecords;
    }
    
    
}
