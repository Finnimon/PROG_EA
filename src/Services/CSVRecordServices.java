package Services;

import model.CSVRecord;

public class CSVRecordServices
{
    
    
    //region [Methode]
    
    
    public static int blankeWerteZaehlen(CSVRecord cSVRecord)
    {
        int i=0;
        for (String wert: cSVRecord.getRecord()
             )
        {
            if (wert.isBlank()) i++;
        }
     
     
        return i;
    }
    
    
    //endregion
    
    
}
