package Resources;


/**
 * @Summary: The Interface {@link Strings} contains most of the strings used by the program for largely internal purposes; Most classes have their own specific final strings to insure modular functionality.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public interface Strings
{
    
    /**
     * @Summary: The String {@link Strings#UNBEKANNT} is used to indicate an unknown value.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    String UNBEKANNT = "Unbekannt";
    
    
    String CRLF = "\r\n";
    
    
    String FORWARD_SLASH = "/";
    
    
    String TABULATOR = "\t";
    
    
    String EMPTY = "";
    
    
    /**
     * @Summary: The delimiter used in the csv under {@link #BAUMKATASTER_FILEPATH}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    String DELIMITER = ";";
    
    
    /**
     * @Summary: The path of the CSV file containing the Tree-Cadastre of Berlin.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    String BAUMKATASTER_FILEPATH = "src/resources/Baumkataster_Berlin.csv";
    
    
}
