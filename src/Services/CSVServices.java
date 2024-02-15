package Services;

import model.CSVRecord;

import java.util.ArrayList;

public class CSVServices
{
    
    
    //region[Methoden]
    public static ArrayList<Integer> recordIndizeOhneGenugWerteFinden(ArrayList<CSVRecord> cSV, int erlaubteAnzahlLeereWerte)
    {
        ArrayList<Integer> leereRecordsIndize=new ArrayList<>();
        for (int i = 0; i < cSV.size(); i++)
        {
            if(CSVRecordServices.blankeWerteZaehlen(cSV.get(i))>erlaubteAnzahlLeereWerte) leereRecordsIndize.add(i);
        }
        return leereRecordsIndize;
    }
    
    
    public static ArrayList<Integer> blankeRecordIndizeFinden(ArrayList<CSVRecord> cSV)
    {
        return recordIndizeOhneGenugWerteFinden(cSV,0);
    }
    
    
    //endregion
    
    
}
