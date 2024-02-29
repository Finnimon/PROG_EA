package resources;


/**
 * @Summary: The {@link Messages} interface contains all the messages used by the program to communicate via the console.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public interface Messages
{
    
    
    String AUSGABE_PROGRAMM_ARGUMENTE_FEHLERHAFT="Die Programm-Argumente sind fehlerhaft! \n\r\t\tBitte geben Sie nur Zahlen ein. \n\r\t\tBeispiel:\t\"2023 917 25 775 43.5\"\r\n\t\tHierbei muss beachtet werden, dass Gleitkommazahlen mit Punktzeichen angegeben werden und es insgesamt 5 Argumente geben muss.\r\n\t\tDiese Maxima sollten in folgender Reihenfolge sein:\t\"1.Pflanzjahr 2.Standalter 3.Kronendurchmesser in Meter 4.Stammunfang in Zentimeter 5.Höhe in Meter \"";
    
    
    String AUSGABE_KANN_DATEI_NICHT_LESEN = "Kann Datei nicht lesen: ";
    
    
    String AUSGABE_IN_MILLISEKUNDEN = "\t\tin Millisekunden:\t\t";
    
    
    String AUSGABE_PROGRAMM_INITIALISIERT = "Das Programm wurde initialisiert.";
    
    
    String AUSGABE_LESE_AUS_DATEI = "\r\nLese Daten aus Datei:\t";
    
    
    String AUSGABE_ANZAHL_ERFOLGREICH_EINGELESENE_ZEILEN = "Anzahl erfolgreich eingelesener Zeilen:\t";
    
    
    String AUSGABE_ANZAHL_ERZEUGTER_BAUM_INSTANZEN = "Anzahl erzeugter Baum-Instanzen:\t";
    
    
    String AUSGABE_ANZAHL_GELOESCHTE_DATENSAETZE = "Anzahl gelöschter Datensätze:\t";
    
    
    String AUSGABE_ANZAHL_BEARBEITETE_DATENSAETZE = "Anzahl reparierter Datensätze:\t";
    
    
    String AUSGABE_ANLEITUNG_FRAGENWAHL = "\r\nBitte geben Sie Ihre Auswahl einer Frage in der Form ihres Indexes [Index] (Ohne Klammern) ein und drücken Sie dann [Enter]. \r\nWenn Sie keine Frage mehr haben, geben Sie [0] (Ohne Klammern) ein und drücken Sie dann [Enter], um das Programm zu beenden.\n\r";
    
    
    String AUSGABE_FORMAL_FALSCHE_FRAGENWAHL = "Ihre Auswahl war formal falsch. Bitte versuchen Sie es erneut.";
    
    
    String[] FRAGEN = {
            AUSGABE_ANLEITUNG_FRAGENWAHL,
            "[1]\t\tIn welchem Bezirk steht der höchste Baum?",
            "[2]\t\tWelcher Baum hat den größten Umfang?",
            "[3]\t\tWelcher Baum hat die größte Krone?",
            "[4]\t\tWelcher Baum ist der älteste?",
            "[5]\t\tWie viele Baumarten gibt es?",
            "[6]\t\tWie viele Gattungen gibt es?",
            "[7]\t\tWelche Gattung kommt am häufigsten vor?",
            "[8]\t\tWelcher Bezirk hat die meisten Bäume?",
            "[9]\t\tIn welchem Bezirk stehen die meisten Baumarten?",
            "[10]\tWelche Gattung wächst am höchsten?",
            "[11]\tWelche Gattung hat den größten Umfang?",
            "[12]\tWie viel Kohlenstoff wurde von den Bäumen in Berlin gespeichert?",
            "[13]\tIn welchem Bezirk fand die größte Kohlenstoffspeicherung statt?",
            "[14]\tWelche Gattung hat die größte Menge an Kohlenstoff gespeichert"
    };
    
    
    String[] ANTWORTEN = {
            "Das Programm wird beendet.\t",
            "Der höchste Baum steht im Bezirk:\t",
            "Baum mit dem größten Umfang:\t",
            "Baum mit der größten Krone:\t",
            "Älterster Baum:\t",
            "Anzahl Baumarten:\t",
            "Anzahl Gattungen:\t",
            "Häufigste Gattung:\t",
            "Bezirk mit den meisten Bäumen:\t",
            "Bezirk mit den meisten Baumarten:\t",
            "Am höchsten wachsende Gattung:\t",
            "Gattung mit dem größten durchschnittlichen Umfang:\t",
            "In Berlin wurde insgesamt Kohlenstoff in metrischen Tonnen gespeichert:\t",
            "Der meiste Kohlenstoff wurde gespeichert in dem Bezirk:\t",
            "Der meiste Kohlenstoff wurde gespeichert von der Gattung:\t"
    };
    
    
    String OBJEKT_ID = "ObjektID:\t";
    
    
    String OBJEKTNAME = "Objektname:";
    
    
    String BEZIRK = "Bezirk:";
    
    
    String BAUM_ART = "Art (Deutsch/Botanisch):";
    
    
    String BAUM_GATTUNG = "Gattung (Deutsch/Botanisch):";
    
    
    String PFLANZJAHR_STANDALTER = "Jahr der Pflanzung/Standalter:";
    
    
    String DURCHMESSER_DER_KRONE = "Durchmesser der Krone in Metern:";
    
    
    String STAMM_UMFANG = "Umfang des Stamms in Zentimetern:";
    
    
    String BAUM_HOEHE = "Baumhöhe in Metern:";
    
    
}
