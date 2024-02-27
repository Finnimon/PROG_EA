package resources;


/**
 * Das Messages Interface besteht aus Message-Strings die zur Ausgabe von Daten und Anzeige von Menüs verwendet werden.
 */
public interface Messages
{
    
    
    String AUSGABE_IN_MILLISEKUNDEN = "in Millisekunden:\t";
    
    
    String AUSGABE_PROGRAMM_INITIALISIERT="Das Programm wurde initialisiert.";
    
    
    String AUSGABE_LESE_AUS_DATEI = "Lese Daten aus Datei:\t";
    
    
    String AUSGABE_ANZAHL_ERFOLGREICH_EINGELESENE_ZEILEN = "Anzahl erfolgreich eingelesener Zeilen:\t";
    
    
    String AUSGABE_ANZAHL_ERZEUGTER_BAUM_INSTANZEN = "Anzahl erzeugter Tree-Instanzen:\t";
    
    
    String AUSGABE_ANZAHL_GELOESCHTE_DATENSAETZE="Anzahl gelöschter Datensätze:\t";
    
    
    String AUSGABE_ANZAHL_BEARBEITETE_DATENSAETZE="Anzahl reparierter Datensätze:\t";
    
    
    String AUSGABE_ANLEITUNG_FRAGENWAHL="\r\nBitte geben Sie Ihre Auswahl einer Frage in der Form ihres Indexes [Index] (Ohne Klammern) ein und drücken Sie dann [Enter]. \r\nWenn Sie keine Frage mehr haben, geben Sie [0] (Ohne Klammern) ein und drücken Sie dann [Enter], um das Programm zu beenden.\n\r";
    
    
    String AUSGABE_FORMAL_FALSCHE_FRAGENWAHL ="Ihre Auswahl war formal falsch. Bitte versuchen Sie es erneut.";
    
    
    String[] FRAGEN = {AUSGABE_ANLEITUNG_FRAGENWAHL, "[1]\t\tIn welchem Bezirk steht der höchste Tree?", "[2]\t\tWelcher Tree hat den größten Umfang?", "[3]\t\tWelcher Tree hat die größte Krone?", "[4]\t\tWelcher Tree ist der älteste?", "[5]\t\tWie viele Baumarten gibt es?", "[6]\t\tWie viele Gattungen gibt es?", "[6]\t\tWelche Gattung kommt am häufigsten vor?", "[8]\t\tWelcher Bezirk hat die meisten Bäume?", "[9]\t\tIn welchem Bezirk stehen die meisten Baumarten?", "[10]\tWelche Gattung wächst am höchsten?", "[11]\tWelche Gattung hat den größten Umfang?","[12]\tWie viel Kohlenstoff wurde von den Bäumen in Berlin gespeichert?","[13]\tIn welchem Bezirk fand die größte Kohlenstoffspeicherung statt?","[14]\tWelche Gattung hat die größte Menge an Kohlenstoff gespeichert"};
    
    
    
    String AUSGABE_PROGRAMM_BEENDEN="Das Programm wird beendet.\t";
    
    String AUSGABE_ANTWORT_BEZIRK_MIT_DEM_GROEZSTEN_BAUM="Der höchste Tree steht im Bezirk:\t";
    
    
    String AUSGABE_ANTWORT_DICKSTER_BAUM="Tree mit dem größten Umfang:\t";
    
    
    String AUSGABE_ANTWORT_BAUM_MIT_GROESZTER_KRONE="Tree mit der größten Krone:\t";
    
    
    String AUSGABE_ANTWORT_AELTESTER_BAUM="Älterster Tree:\t";
    
    
    String AUSGABE_ANTWORT_ANZAHL_BAUMARTEN="Anzahl Baumarten:\t";
    
    
    String AUSGABE_ANTWORT_ANZAHL_GATTUNGEN="Anzahl Gattungen:\t";
    String AUSGABE_ANTWORT_HAEUFIGSTE_GATTUNG="Häufigste Gattung:\t";
    String AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAEUMEN="Bezirk mit den meisten Bäumen:\t";
    String AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAUMARTEN="Bezirk mit den meisten Baumarten:\t";
    String AUSGABE_ANTWORT_HOECHSTE_GATTUNG="Am höchsten wachsende Gattung:\t";
    String AUSGABE_ANTWORT_DICKSTE_GATTUNG="Gattung mit dem größten Umfang:\t";
    String AUSGABE_ANTWORT_KOHLENSTOFF_SPEICHERUNG_BERLIN_INSGESAMT="In Berlin wurde insgesamt Kohlenstoff in Kilogramm gespeichert:\t";
    String AUSGABE_ANTWORT_KOHLENSTOFF_SPEICHERUNG_STAERKSTER_BEZIRK="Der meiste Kohlenstoff wurde gespeichert in dem Bezirk:\t";
    String AUSGABE_ANTWORT_KOHLENSTOFF_SPEICHERUNG_STAERKSTE_GATTUNG="Der meiste Kohlenstoff wurde gespeichert von der Gattung:\t";
    String[] ANTWORTEN={AUSGABE_PROGRAMM_BEENDEN,AUSGABE_ANTWORT_BEZIRK_MIT_DEM_GROEZSTEN_BAUM,AUSGABE_ANTWORT_DICKSTER_BAUM,AUSGABE_ANTWORT_BAUM_MIT_GROESZTER_KRONE,AUSGABE_ANTWORT_AELTESTER_BAUM,AUSGABE_ANTWORT_ANZAHL_BAUMARTEN,AUSGABE_ANTWORT_ANZAHL_GATTUNGEN,AUSGABE_ANTWORT_HAEUFIGSTE_GATTUNG,AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAEUMEN,AUSGABE_ANTWORT_BEZIRK_MIT_MEISTEN_BAUMARTEN,AUSGABE_ANTWORT_HOECHSTE_GATTUNG,AUSGABE_ANTWORT_DICKSTE_GATTUNG,AUSGABE_ANTWORT_KOHLENSTOFF_SPEICHERUNG_BERLIN_INSGESAMT,AUSGABE_ANTWORT_KOHLENSTOFF_SPEICHERUNG_STAERKSTER_BEZIRK,AUSGABE_ANTWORT_KOHLENSTOFF_SPEICHERUNG_STAERKSTE_GATTUNG};
    
    
    String OBJEKT_ID = "ObjektID:";
    
    
    String OBJEKTNAME = "Objektname:";
    
    
    String BEZIRK = "Bezirk:";
    
    
    String TREE_SPECIES = "Art (Deutsch/Botanisch):";
    
    
    String TREE_GENUS = "Gattung (Deutsch/Botanisch):";
    
    
    String PFLANZJAHR_STANDALTER = "Jahr der Pflanzung/Standalter:";
    
    
    String DURCHMESSER_DER_KRONE = "Durchmesser der Krone in Metern:";
    
    
    String STAMMUMFANG = "Umfang des Stamms in Zentimetern:";
    
    
    String BAUMHOEHE = "Baumhöhe in Metern:";
    

}
