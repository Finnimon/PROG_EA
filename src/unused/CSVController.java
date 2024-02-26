//package control;
//
//import Services.CSVServices;
//import model.CSVRecord;
//import utility.CSVParser;
//
//import java.util.ArrayList;
//
//public class CSVController
//{
//    public static CSVParser recordsOhneGenugWerteEntfernen(CSVParser cSVParser, int erlaubteAnzahlLeereWerte)
//    {
//        ArrayList<CSVRecord> cSVRecords = cSVParser.getCSVRecords();
//        ArrayList<Integer> unzulaenglicheRecordIndize = CSVServices.recordIndizeOhneGenugWerteFinden(cSVRecords, erlaubteAnzahlLeereWerte);
//
//        for (Integer index : unzulaenglicheRecordIndize.reversed())
//        {
//            cSVRecords.remove(index.intValue());
//        }
//
//        cSVParser.setCSVRecords(cSVRecords);
//
//
//        return cSVParser;
//
//    }
//
//    //    public static void leereZeileErkennen(@NotNull ArrayList<String> zeile) throws ElementFaultyException
//    //    {
//    //        int i=0;
//    //        for (String wert : zeile)
//    //        {
//    //            if(wert.isBlank())
//    //            {
//    //                i++;
//    //            }
//    //        }
//    //        if (i>9)
//    //        {
//    //            for (String wert:zeile
//    //                 )
//    //            {
//    //                System.out.print(wert+",");
//    //            }
//    //            System.out.println();
//    //            throw new ElementFaultyException();
//    //        }
//    //    }
//
//}
