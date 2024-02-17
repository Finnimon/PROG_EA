package utility;

import Services.KatasterServices;
import model.BaumKataster;
import org.jetbrains.annotations.NotNull;
import resources.Konstanten;
import resources.Strings;

import java.io.*;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MyIO
{
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";
    private static final String PROMPT = "-> ";
    private static final int NIEDRIGSTER_FRAGEN_INDEX = Konstanten.EINS;
    private static boolean timeStamp = true;
    private static boolean verboseMode = true;
    private static ZonedDateTime startZeit = ZonedDateTime.now();
    
    
    public static void printLn(String text, boolean timeStamp)
    {
        setTimeStamp(timeStamp);
        if (verboseMode)
        {
            if (timeStamp)
            {
                StringBuilder stringBuilder = new StringBuilder(Strings.CRLF);
                stringBuilder.append(text);
                stringBuilder.append(Strings.TABULATOR);
                stringBuilder.append(Strings.AUSGABE_IN_MILLISEKUNDEN);
                stringBuilder.append(Strings.TABULATOR);
                stringBuilder.append(getVergangeneMillisekunden());
                System.out.println(stringBuilder);
            }
            else
            {
                System.out.println(text);
            }
            resetZeitgeber();
        }
    }
    
    
    private static void resetZeitgeber()
    {
        startZeit = ZonedDateTime.now();
    }
    
    
    private static long getVergangeneMillisekunden()
    {
        return ChronoUnit.MILLIS.between(startZeit, ZonedDateTime.now());
    }
    
    
    public static void printLn(String text, ConsoleColor color)
    {
        System.out.print(color);
        printLn(text, timeStamp);
        System.out.print(ConsoleColor.RESET);
    }
    
    
    public static void printLn(String text, ConsoleColor color, ConsoleColor backgroundColor)
    {
        System.out.print(color);
        System.out.print(backgroundColor);
        printLn(text, timeStamp);
        System.out.print(ConsoleColor.RESET);
    }
    
    
    public static void printLn(String text)
    {
        MyIO.printLn(text, false);
    }
    
    
    public static void setTimeStamp(boolean timeStamp)
    {
        MyIO.timeStamp = timeStamp;
    }
    
    
    public static void setVerboseMode(boolean verboseMode)
    {
        MyIO.verboseMode = verboseMode;
    }
    
    
    public static File dateipfadValidieren(String dateipfad)
    {
        //todo exception
        File file = new File(dateipfad);
        assert file.canRead();
        return file;
    }
    
    
    public static ArrayList<String> dateiZeilenweiseEinlesen(@NotNull File file)
    {
        ArrayList<String> zeilen;
        try
        {
            zeilen = (ArrayList<String>) Files.readAllLines(file.toPath());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
        
        return zeilen;
    }
    
    
    private static void fragenAnbieten()
    {
        for (String frage : Strings.FRAGEN)
        {
            MyIO.printLn(frage, false);
        }
    }
    
    
    private static int fragenWahlEinlesen() throws NumberFormatException
    {
        try
        {
            return Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        }
        catch (Exception e)
        {
            MyIO.printLn("Deine Auswahl einer Frage ist formal falsch. Deine Antwort sollte stattdessen wie beim folgenden Beispiel nur einen Integer i aus der Menge der Indize der Fragen sein:");
            return fragenWahlEinlesen();
        }
    }
    
    
    public static void fragenStellenBeantworten(BaumKataster baumKataster)
    {
        int fragenWahl=Konstanten.EINS;
        while (fragenWahl!=0)
        {
            fragenAnbieten();
            fragenWahl = fragenWahlEinlesen();
            KatasterServices.frageAntwortErmitteln(baumKataster, fragenWahl);
        }
    }
    
    
}
