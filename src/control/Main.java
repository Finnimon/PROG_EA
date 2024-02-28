package control;

import model.TreeCadastre;
import org.jetbrains.annotations.NotNull;
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
        
        TaskController taskController = new TaskController();
        
        TreeCadastre treeCadastre = taskController.parseCsvIntoBaumKataster();
        
        TreeCadastre shallowRepairedTreeCadastre = taskController.dataRepair(treeCadastre);
        
        taskController.offerAndAnswerQueries(shallowRepairedTreeCadastre, treeCadastre);
//
//        HashSet<String>Gattungen=new HashSet<>();
//        for (Tree tree : shallowRepairedTreeCadastre.getBaumHashMap().values()
//             )
//        {
//            Gattungen.add(tree.getTaxonomie().getGenusBotanical());
//        }
//        for (String gattung:Gattungen
//             )
//        {
//            System.out.println(gattung);
//        }
//
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
        }
        catch (NullPointerException e)
        {
            argumenteFloats=new ArrayList<>();//todo console Message and readline
        }
        catch (NumberFormatException e)
        {
            argumenteFloats=new ArrayList<>();
            //todo siehe oben
        }
        
        
        Main.ARGUMENTS =argumenteFloats;
    }
    
    
}
