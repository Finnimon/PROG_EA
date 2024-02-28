package utility;

import resources.Messages;
import resources.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;


public class MyIO
{
    
    
    private static boolean timeStamp = true;
    
    
    private static ZonedDateTime timer = ZonedDateTime.now();
    
    
    public static void printLine(String text, boolean timeStamp)
    {
        setTimeStamp(timeStamp);
        
        if (timeStamp)
        {
            StringBuilder stringBuilder = new StringBuilder(Strings.CRLF);
            stringBuilder.append(text);
            stringBuilder.append(Messages.AUSGABE_IN_MILLISEKUNDEN);
            stringBuilder.append(getMillisecondsSinceLastTimerReset());
            System.out.println(stringBuilder);
        }
        else
        {
            System.out.println(text);
        }
        
        
        resetZeitgeber();
    }
    
    
    public static void resetZeitgeber()
    {
        timer = ZonedDateTime.now();
    }
    
    
    private static long getMillisecondsSinceLastTimerReset()
    {
        return ChronoUnit.MILLIS.between(timer, ZonedDateTime.now());
    }
    
    
    public static void printLine(String text)
    {
        MyIO.printLine(text, false);
    }
    
    
    public static void setTimeStamp(boolean timeStamp)
    {
        MyIO.timeStamp = timeStamp;
    }
    
    
    public static String readConsoleLine() throws IOException
    {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
    
    
}
