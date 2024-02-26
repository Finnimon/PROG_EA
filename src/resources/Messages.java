package resources;

public interface Messages
{
    
    
    //region [Programmm Ablauf Beschreibung]
    
    
    String AUSGABE_IN_MILLISEKUNDEN = "in Millisekunden:\t";
    
    
    String AUSGABE_PROGRAMM_INITIALISIERT="Das Programm wurde initialisiert.";
    
    
    String AUSGABE_LESE_AUS_DATEI = "Lese Daten aus Datei:\t";
    
    
    String AUSGABE_ANZAHL_ERFOLGREICH_EINGELESENE_ZEILEN = "Anzahl erfolgreich eingelesener Zeilen:\t";
    
    
    String AUSGABE_ANZAHL_ERZEUGTER_BAUM_INSTANZEN = "Anzahl erzeugter Baum-Instanzen:\t";
    
    
    String AUSGABE_ANZAHL_GELOESCHTE_DATENSAETZE="Anzahl gelöschter Datensätze:\t";
    
    
    String AUSGABE_ANZAHL_BEARBEITETE_DATENSAETZE="Anzahl reparierter Datensätze:\t";
    
    
    //endregion
    //region [Fragen/Antworten]
    
    
    String AUSGABE_ANLEITUNG_FRAGENWAHL="\r\nBitte geben Sie Ihre Auswahl einer Frage in der Form ihres Indexes [Index] (Ohne Klammern) ein und drücken Sie dann [Enter]. \r\nWenn Sie keine Frage mehr haben, geben Sie [0] (Ohne Klammern) ein und drücken Sie dann [Enter].\n\r";
    
    
    String AUSGABE_FORMAL_FALSCHE_FRAGENWAHL ="Ihre Auswahl war formal falsch. Bitte versuchen Sie es erneut.";
    
    
    String[] FRAGEN = {AUSGABE_ANLEITUNG_FRAGENWAHL, "[1]\t\tIn welchem Bezirk steht der höchste Baum?", "[2]\t\tWelcher Baum hat den größten Umfang?", "[3]\t\tWelcher Baum hat die größte Krone?", "[4]\t\tWelcher Baum ist der älteste?", "[5]\t\tWie viele Baumarten gibt es?", "[6]\t\tWie viele Gattungen gibt es?", "[6]\t\tWelche Gattung kommt am häufigsten vor?", "[8]\t\tWelcher Bezirk hat die meisten Bäume?", "[9]\t\tIn welchem Bezirk stehen die meisten Baumarten?", "[10]\tWelche Gattung wächst am höchsten?", "[11]\tWelche Gattung hat den größten Umfang?"};
    
    
    
    String AUSGABE_PROGRAMM_BEENDEN="Das Programm wird beendet.\t";
    
    String AUSGABE_ANTWORT_BEZIRK_MIT_DEM_GROEZSTEN_BAUM="Der höchste Baum steht im Bezirk:\t";
    
    
    String AUSGABE_ANTWORT_DICKSTER_BAUM="Baum mit dem größten Umfang:\t";
    
    
    String AUSGABE_ANTWORT_BAUM_MIT_GROESZTER_KRONE="Baum mit der größten Krone:\t";
    
    
    String AUSGABE_ANTWORT_AELTESTER_BAUM="Älterster Baum:\t";
    
    
    String AUSGABE_ANTWORT_ANZAHL_BAUMARTEN="Anzahl Baumarten:\t";
    
    
    String AUSGABE_ANTWORT_ANZAHL_GATTUNGEN="Anzahl Gattungen:\t";
    String AUSGABE_ANTWORT_HAEUFIGSTE_GATTUNG="Häufigste Gattung:\t";
    String AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAEUMEN="Bezirk mit den meisten Bäumen:\t";
    String AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAUMARTEN="Bezirk mit den meisten Baumarten:\t";
    String AUSGABE_ANTWORT_HOECHSTE_GATTUNG="Am höchsten wachsende Gattung:\t";
    String AUSGABE_ANTWORT_DICKSTE_GATTUNG="Gattung mit dem größten Umfang:\t";
    
    String[] ANTWORTEN={AUSGABE_PROGRAMM_BEENDEN,AUSGABE_ANTWORT_BEZIRK_MIT_DEM_GROEZSTEN_BAUM,AUSGABE_ANTWORT_DICKSTER_BAUM,AUSGABE_ANTWORT_BAUM_MIT_GROESZTER_KRONE,AUSGABE_ANTWORT_AELTESTER_BAUM,AUSGABE_ANTWORT_ANZAHL_BAUMARTEN,AUSGABE_ANTWORT_ANZAHL_GATTUNGEN,AUSGABE_ANTWORT_HAEUFIGSTE_GATTUNG,AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAEUMEN,AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAUMARTEN,AUSGABE_ANTWORT_HOECHSTE_GATTUNG,AUSGABE_ANTWORT_DICKSTE_GATTUNG};
    //endregion
    //region [Baum.toString]
    
    
    String OBJEKT_ID = "ObjektID:";
    
    
    String OBJEKTNAME = "Objektname:";
    
    
    String BEZIRK = "Bezirk:";
    
    
    String BAUMART = "Art (Deutsch/Botanisch):";
    
    
    String GATTUNG = "Gattung (Deutsch/Botanisch):";
    
    
    String PFLANZJAHR_STANDALTER = "Jahr der Pflanzung/Standalter:";
    
    
    String DURCHMESSER_DER_KRONE = "Durchmesser der Krone in Metern:";
    
    
    String STAMMUMFANG = "Umfang des Stamms in Zentimetern:";
    
    
    String BAUMHOEHE = "Baumhöhe in Metern:";

    
    //endregion
    

}
