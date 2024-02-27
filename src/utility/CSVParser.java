package utility;

import model.CSVRecord;
import resources.Strings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParser extends FileParser//todo https://datatracker.ietf.org/doc/html/rfc4180 quelle
{
    
    
    private final String delimiter;
    
    
    private final int recordLength;
    
    
    public CSVParser(String filePath, String delimiter, int recordLength) throws FileNotFoundException
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
        ArrayList<String> csvFile = super.parse();
        ArrayList<CSVRecord> cSVRecords = new ArrayList<>();
        
        if (removeHeader)
        {
            csvFile.removeFirst();
        }
        
        for (int i = 0; i < csvFile.size(); i++)
        {
            String line = csvFile.get(i);
            CSVRecord csvRecord;
            try
            {
                csvRecord = new CSVRecord(line, delimiter, recordLength);
            }
            catch (RecordShortException e)
            {
                StringBuilder stringBuilder = new StringBuilder(line);
                stringBuilder.append(Strings.CRLF);
                stringBuilder.append(csvFile.remove(i + 1));
                
                csvFile.set(i, (stringBuilder.toString()));
                i--;
                
                continue;
            }
            catch (ElementFaultyException e)
            {
                csvFile.remove(i);
                i--;
                
                continue;
            }
            
            cSVRecords.add(csvRecord);
        }
        
        
        return cSVRecords;
    }
    
    
    /**
     * @param filePath The path of the file to be written to.
     * @param content The content to be written to filePath.
     * @param <T> The type of the content to be written to filePath.
     * @throws IOException if filePath cannot be written to.
     * @Precondition: Params are not null and filePath exists and canWrite.
     * @Postcondition: content is written to filePath.
     * @Summary: Writes content to filePath.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public <T> void save(String filePath, Iterable<T> content) throws IOException
    {
        super.save(filePath, content);
    }
}
