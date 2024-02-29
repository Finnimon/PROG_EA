package utility.IO;

import org.jetbrains.annotations.NotNull;
import resources.Messages;
import resources.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;


/**
 * @Summary: A collection of methods static for printing and reading from the console.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class MyIO
{
    
    
    /**
     * @Summary: The {@link #timer} stores the time of its last reset or the start of the program.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private static ZonedDateTime timer = ZonedDateTime.now();
    
    
    /**
     * @param text      The text to print through the console.
     * @param timeStamp If true, the time stamp will be printed to the console when using printLine().
     * @Summary: Prints the given text to the console with the time stamp if set to true.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static void printLine(@NotNull String text, boolean timeStamp)
    {
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
        
        
        resetTimer();
    }
    
    
    /**
     * @param text the text to print through the console.
     * @Summary: Is equivalent to {@link #printLine(String, boolean)} with timeStamp = false.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static void printLine(@NotNull String text)
    {
        MyIO.printLine(text, false);
    }
    
    
    /**
     * @Summary: Resets the {@link #timer} to the current time.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static void resetTimer()
    {
        timer = ZonedDateTime.now();
    }
    
    
    /**
     * @return The amount of milliseconds since the last time {@link #resetTimer()} was called and now.
     * @Summary: Returns the amount of milliseconds since the last time {@link #resetTimer()} was called and now.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private static long getMillisecondsSinceLastTimerReset()
    {
        return ChronoUnit.MILLIS.between(timer, ZonedDateTime.now());
    }
    
    
    /**
     * @return the next line from the console.
     * @throws IOException if the console cannot be read.
     * @Summary: Reads the next line from the console.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static String readConsoleLine() throws IOException
    {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
    
    
}
