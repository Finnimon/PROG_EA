package utility;

import model.CSVRecord;
import resources.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class CSVParser extends FileParser//todo https://datatracker.ietf.org/doc/html/rfc4180 quelle
{
    
    /**
     * @Summary: The default delimiter.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final String DEFAULT_DELIMITER_COMMA = ",";
    
    
    /**
     * @Summary: The delimiter to be used whenever parsing.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final String delimiter;
    
    
    /**
     * @Summary: The record length to be used whenever parsing.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int recordLength;
    
    
    /**
     * @param filePath     The path of the file to be parsed.
     * @param delimiter    The delimiter used by the CSV under filePath.
     * @param recordLength The record length used by the CSV under filePath.
     * @throws FileNotFoundException if filePath cannot be found or read.
     * @Precondition: Params are not null and filePath exists and canRead. The file under filePath is RFC4180 compliant.
     * @Postcondition: All methods of this {@link CSVParser} will be functional.
     * @Summary: Constructs a new {@link CSVParser} from filePath, delimiter and recordLength.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public CSVParser(String filePath, String delimiter, int recordLength) throws FileNotFoundException
    {
        super(filePath);
        this.delimiter = delimiter;
        this.recordLength = recordLength;
    }
    
    
    /**
     * @param filePath The path of the file to be parsed.
     * @throws FileNotFoundException if filePath cannot be found or read.
     * @Precondition: Params are not null and filePath exists and canRead. The file under filePath is RFC4180 compliant. The delimiter used by the file is the {@link #DEFAULT_DELIMITER_COMMA}.
     * @Postcondition: content is written to filePath.
     * @Summary: Writes content to filePath.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public CSVParser(String filePath, int recordLength) throws FileNotFoundException
    {
        super(filePath);
        this.delimiter = DEFAULT_DELIMITER_COMMA;
        this.recordLength = recordLength;
    }
    
    
    /**
     * @return {@link #delimiter}
     * @Precondition: The Precondition of {@link CSVParser#CSVParser(String, String, int)} is met.
     * @Postcondition: The return is the delimiter used by the RFC4180 compliant file under super.{@link #getFile()}.
     * @Summary: Returns the delimiter used by this {@link CSVParser} and specified by {@link CSVParser#CSVParser(String, String, int)}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public String getDelimiter()
    {
        return delimiter;
    }
    
    
    /**
     * @return {@link #recordLength}
     * @Precondition: The Precondition of {@link CSVParser#CSVParser(String, String, int)} is met.
     * @Postcondition: The return is the record length used by the RFC4180 compliant file under super.{@link #getFile()}.
     * @Summary: Returns the record length used by this {@link CSVParser} and specified by {@link CSVParser#CSVParser(String, String, int)}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public int getRecordLength()
    {
        return recordLength;
    }
    
    
    /**
     * @param removeHeader if true, the first line will be removed. See RFC4180 for more information.
     * @return The parsed {@link CSVRecord}s.
     * @Precondition: The Precondition of {@link CSVParser#CSVParser(String, String, int)} is met.
     * @Postcondition: The return is an ArrayList of {@link CSVRecord}s, which are parsed RFC4180 compliant from the file under super.{@link #getFile()}.
     * @Summary: Returns the parsed {@link CSVRecord}s from the file specified by {@link CSVParser#CSVParser(String, String, int)}.
     */
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
                csvRecord = new CSVRecord(line, getDelimiter(), getRecordLength());
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
     * @param filePath The path to save this {@link CSVParser#parse()} to.
     * @throws IOException if filePath cannot be written to.
     * @Precondition: filePath is not null and filePath's parent directory exists and canWrite. {@link CSVParser#parse()} is not null.
     * @Postcondition: The CSV as passed by {@link CSVParser#parse()} is written to filePath. Unless a file already exists under filePath.
     * @Summary: Writes the contents of this {@link CSVParser#parse()} to filePath. This creates an RFC4180 compliant file using The standard comma delimiter of {@link CSVRecord}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void save(String filePath) throws IOException
    {
        File file = new File(filePath);
        if(!file.createNewFile()) throw new IOException();
        for (CSVRecord csvRe : parse(false))
        {
            Files.writeString(file.toPath(), csvRe.toString());
            Files.writeString(file.toPath(), Strings.CRLF);
        }
    }
}
