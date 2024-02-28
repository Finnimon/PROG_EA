package model;

import resources.Strings;
import utility.ElementFaultyException;
import utility.RecordShortException;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @Summary: This class represents a record in an RFC4180 compliant CSV file. If it is successfully instantiated it stores the record. It is used by {@link utility.CSVParser}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class CSVRecord
{
    
    
    /**
     * @Summary: This regex is used to skip other regex in a record. This includes linebreaks and delimiters.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final String REGEX_SKIP = "\"";
    
    
    /**
     * @Summary: This regex is the standard delimiter in a RFC4180 compliant CSV file. It is used by {@link #toString()} and {@link utility.CSVParser}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static final String DEFAULT_DELIMITER = ",";
    
    
    /**
     * @Summary: This regex is the standard linebreak in a RFC4180 compliant CSV file. It is used by {@link #toString()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static final String CRLF = "\r\n";
    
    
    /**
     * @Summary: This attribute represents a line in a RFC4180 compliant CSV file. Each String is a field in the record.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private ArrayList<String> record;
    
    
    /**
     * @param line         the line in a RFC4180 compliant CSV file.
     * @param delimiter    the delimiter used by the RFC4180 compliant CSV file.
     * @param specifedRecordLength the record length used by the RFC4180 compliant CSV file and specified in {@link utility.CSVParser#CSVParser(String, String, int)}.
     * @throws RecordShortException if the record is too short. This prompts the {@link utility.CSVParser} to pass this and the next line into a new {@link CSVRecord}.
     * @Precondition: The line is a line in an RFC4180 compliant CSV file.
     * @Postcondition: If the line is shorter than specified a {@link RecordShortException} will be thrown. This prompts the {@link utility.CSVParser} to pass this and the next line into a new {@link CSVRecord}, asserting that no Short Records exist. Records that are too long will be ignored.
     * @Summary: This constructor parses a line in a RFC4180 compliant CSV file. It also handles linebreaks and delimiters. If the record is too short, an exception will be thrown.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public CSVRecord(String line,String delimiter, int specifedRecordLength) throws RecordShortException, ElementFaultyException
    {
        ArrayList<String> record =readRecord(line,delimiter);
        int recordLength=record.size();
        if(recordLength<specifedRecordLength)
        {
            throw new RecordShortException();
        }
        else if (recordLength>specifedRecordLength)
        {
            throw new ElementFaultyException();
        }
        
        
        setRecord(record);
    }
    
    
    /**
     * @param line in a RFC4180 compliant CSV file.
     * @param delimiter the delimiter used by the RFC4180 compliant CSV file and specified in {@link utility.CSVParser#CSVParser(String, String, int)}.
     * @return The record as an ArrayList of Strings.
     * @throws RecordShortException if the record ends upon a field enclosed by {@link #REGEX_SKIP} that does not end with {@link #REGEX_SKIP}.
     * @Precondition: The String line is a line in an RFC4180 compliant CSV file.
     * @Postcondition: The return will remove all {@link #REGEX_SKIP} from the fields and not split within a field enclosed by the {@link #REGEX_SKIP}.
     * @Summary: Splits a csv line into an ArrayList of Strings. This is RFC418ß compliant.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private ArrayList<String> readRecord(String line, String delimiter) throws RecordShortException
    {
        ArrayList<String> record= new ArrayList<>(Arrays.asList(line.split(delimiter)));
        
        for (int i = 0; i < record.size(); i++)
        {
            String field=record.get(i);
            StringBuilder stringBuilder = new StringBuilder();
            while (field.startsWith(REGEX_SKIP)&!field.endsWith(REGEX_SKIP))
            {
                stringBuilder.append(field);
                stringBuilder.append(delimiter);
                try
                {
                    field=stringBuilder.append(record.remove(i++)).toString();
                    i--;
                }
                catch (IndexOutOfBoundsException e)
                {
                    throw new RecordShortException();
                }
            }
            
            record.set(i, field.replace(REGEX_SKIP, Strings.EMPTY));
            
        }
        
        
        return record;
    }
    
    
    /**
     * @return {@link #record}
     * @Precondition: {@link #setRecord(ArrayList)} has been called by {@link #CSVRecord(String, String, int)}
     * @Postcondition: The return will be of the correct length.
     * @Summary: This method returns the {@link #record}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public ArrayList<String> getRecord()
    {
        return record;
    }
    
    
    /**
     * @param record the line in a RFC4180 compliant CSV file as passed by {@link #CSVRecord(String, String, int)}.
     * @Precondition: {@link #setRecord(ArrayList)} is only ever called by {@link #CSVRecord(String, String, int)}.
     * @Postcondition: {@link #record} will be of the correct length.
     * @Summary: This method sets the {@link #record}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void setRecord(ArrayList<String> record)
    {
        this.record = record;
    }
    
    
    /**
     * @return {@link #record} as an RFC4180 compliant CSV line with a linebreak.
     * @Precondition: {@link #setRecord(ArrayList)} has been called by {@link #CSVRecord(String, String, int)} and its Precondition has been met.
     * @Postcondition: The returned {@link String} will be RFC4180 compliant.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (String field : getRecord())
        {
            boolean containsRegex=(field.contains(DEFAULT_DELIMITER)||field.contains(CRLF));
            
            if (containsRegex)
            {
                stringBuilder.append(REGEX_SKIP);
                stringBuilder.append(field);
                stringBuilder.append(REGEX_SKIP);
            }
            else
            {
                stringBuilder.append(field);
            }
            
            stringBuilder.append(DEFAULT_DELIMITER);
        }
        
        
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(CRLF).toString();
    }
}
