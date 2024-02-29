package control;

import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Messages;
import utility.Core;

import java.util.ArrayList;


public class Main
{
    
    /**
     * @Summary: These are the arguments passed to the program as parsed by {@link Core#parseStringArrayIntoFloatArrayList(String[])}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static ArrayList<Float> ARGUMENTS;
    
    
    /**
     * @param argumente the arguments passed to the program.
     * @Summary: The entry point of the program.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public static void main(String[] argumente)
    {
        initialisieren(argumente);
        
        TaskController taskController=new TaskController();
        
        taskController.run();
    }
    
    
    /**
     * @param argumente the arguments passed to the program.
     * @Precondition: The arguments are entered correctly in the run configuration.
     * @Postcondition: The {@link #ARGUMENTS} variable is initialized.
     * @Summary: This method initializes the {@link #ARGUMENTS} variable.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private static void initialisieren(@NotNull String[] argumente)
    {
        ArrayList<Float> argumenteFloats;
        try
        {
            argumenteFloats= Core.parseStringArrayIntoFloatArrayList(argumente);
            if (argumenteFloats.size()!= Constants.NEEDED_ARGUMENTS_SIZE)
            {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException | NumberFormatException e)
        {
            throw new RuntimeException(Messages.AUSGABE_PROGRAMM_ARGUMENTE_FEHLERHAFT);
        }
        
        
        Main.ARGUMENTS =argumenteFloats;
    }
    
    
}
