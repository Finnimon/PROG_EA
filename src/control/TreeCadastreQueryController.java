package control;

import model.TreeCadastre;
import utility.DataRepair.StatisticalDataRepairCenter;
import org.jetbrains.annotations.NotNull;
import resources.Messages;
import resources.Strings;
import services.TreeCadastreServices;
import utility.TreeCadastreEntryComparator;
import utility.IO.MyIO;

import java.util.Collections;


/**
 * @Summary: The {@link TreeCadastreQueryController} utilizes methods from {@link TreeCadastreServices} to answer queries regarding a {@link TreeCadastre} via the console.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class TreeCadastreQueryController
{
    
    
    public static final int INDEX_DISTRICT_OF_HIGHEST_TREE = 1;
    
    
    public static final int INDEX_FIND_TREE_OF_GREATEST_CIRCUMFERENCE = 2;
    
    
    public static final int INDEX_FIND_TREE_OF_GREATEST_TOP = 3;
    
    
    public static final int INDEX_FIND_OLDEST_TREE = 4;
    
    
    public static final int INDEX_COUNT_SPECIES = 5;
    
    
    public static final int INDEX_COUNT_GENERA = 6;
    
    
    public static final int INDEX_FIND_MOST_COMMON_GENUS = 7;
    
    
    public static final int INDEX_FIND_MOST_COMMON_DISTRICT = 8;
    
    
    public static final int INDEX_FIND_DISTRICT_WITH_MOST_DIFFERENT_SPECIES = 9;
    
    
    public static final int INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_HEIGHT = 10;
    
    
    public static final int INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_CIRCUMFERENCE = 11;
    
    
    public static final int INDEX_FIND_BERLINS_ENTIRE_CARBON_RETENTION = 12;
    
    
    public static final int INDEX_FIND_DISTRICT_OF_GREATEST_CARBON_RETENTION = 13;
    
    
    public static final int INDEX_FIND_GENUS_OF_GREATEST_CARBON_RETENTION = 14;
    
    
    public static final int YEAR_OF_THE_SURVEY = 2023;
    
    
    public static final int UNIT_CONVERSION_KILOGRAMM_TO_TON = 1000;
    
    
    /**
     * @Summary: A {@link TreeCadastre} repaired using {@link StatisticalDataRepairCenter#deepRepair()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final TreeCadastre statisticallyRobustTreeCadastre;
    
    
    /**
     * @Summary: The same {@link TreeCadastre} as {@link #statisticallyRobustTreeCadastre} repaired using only {@link StatisticalDataRepairCenter#shallowRepair()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final TreeCadastre intactTreeCadastre;
    
    
    /**
     * @param statisticallyRobustTreeCadastre A {@link TreeCadastre} repaired using {@link StatisticalDataRepairCenter#deepRepair()}.
     * @param intactTreeCadastre              The same {@link TreeCadastre} as {@link #statisticallyRobustTreeCadastre} repaired using only {@link StatisticalDataRepairCenter#shallowRepair()}.
     * @Summary: Standard constructor, that only sets the attributes {@link #statisticallyRobustTreeCadastre} and {@link #intactTreeCadastre}.
     */
    public TreeCadastreQueryController(@NotNull TreeCadastre statisticallyRobustTreeCadastre, @NotNull TreeCadastre intactTreeCadastre)
    {
        this.statisticallyRobustTreeCadastre = statisticallyRobustTreeCadastre;
        this.intactTreeCadastre = intactTreeCadastre;
    }
    
    
    private static void offerQueries()
    {
        for (String frage : Messages.FRAGEN)
        {
            MyIO.printLine(frage, false);
        }
    }
    
    
    /**
     * @return {@link #statisticallyRobustTreeCadastre}
     * @Summary: Getter for {@link #statisticallyRobustTreeCadastre}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private TreeCadastre getStatisticallyRobustTreeCadastre()
    {
        return statisticallyRobustTreeCadastre;
    }
    
    
    /**
     * @return {@link #intactTreeCadastre}
     * @Summary: Getter for {@link #intactTreeCadastre}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private TreeCadastre getIntactTreeCadastre()
    {
        return intactTreeCadastre;
    }
    
    
    /**
     * @Precondition: The {@link TreeCadastreQueryController} has been instantiated correctly.
     * @Postcondition: The possible queries will be answered correctly and the method will not encounter any errors even if the query choice of the end user is formally incorrect.
     * @Summary: Displays the in-console menu for selecting queries using {@link #offerQueries()} and parses the selection using {@link #readQueryChoiceFromConsole()} to {@link #determineQueryAnswer(int)} and prints its return in a loop as long as the selection is not 0 to end the program.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void offerAndAnswerQueriesThroughConsole()
    {
        int queryIndex = 1;
        while (queryIndex != 0)
        {
            offerQueries();
            queryIndex = readQueryChoiceFromConsole();
            MyIO.printLine(determineQueryAnswer(queryIndex),true);
        }
    }
    
    
    /**
     * @return The query selection by query-index and only ever a possible query-index.
     * @Precondition: The console is accessible for this program.
     * @Postcondition: The return will always be in bounds of the possible queries {@link Messages#FRAGEN}.
     * @Summary: Recursively reads from the console until the parsed answer is a formally correct query choice and then returns said selection.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private int readQueryChoiceFromConsole()
    {
        try
        {
            int queryIndex = Integer.parseInt(MyIO.readConsoleLine());
            
            if (queryIndex < 0 || queryIndex >= Messages.FRAGEN.length)
            {
                throw new NumberFormatException();
            }
            
            MyIO.resetTimer();
            
            
            return queryIndex;
        }
        catch (Exception e)
        {
            MyIO.printLine(Messages.AUSGABE_FORMAL_FALSCHE_FRAGENWAHL);
            MyIO.printLine(Messages.AUSGABE_ANLEITUNG_FRAGENWAHL);
            
            
            return readQueryChoiceFromConsole();
        }
    }
    
    
    /**
     * @param questionIndex The index of the query that should be answered.
     * @return The answer {@link String} that should be printed as an answer to the selected query.
     * @Precondition: The param is in bounds for the Array {@link Messages#ANTWORTEN} and the {@link TreeCadastreQueryController} has been instantiated correctly.
     * @Postcondition: The method will return a {@link String} that answers the selected query.
     * @Summary: Determines the answer to the selected query by calling methods from {@link TreeCadastreServices} depending on the query index.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private String determineQueryAnswer(int questionIndex)
    {
        TreeCadastre statisticallyRobustTreeCadastre = getStatisticallyRobustTreeCadastre();
        TreeCadastre intactTreeCadastre = getIntactTreeCadastre();
        
        String answer = Strings.EMPTY;
        
        StringBuilder stringBuilder = new StringBuilder(Messages.ANTWORTEN[questionIndex]);
        
        
        if (TreeCadastreQueryController.INDEX_DISTRICT_OF_HIGHEST_TREE <= questionIndex && questionIndex <= TreeCadastreQueryController.INDEX_FIND_OLDEST_TREE)
        {
            int key = Collections.max(intactTreeCadastre.getTreeHashMap().entrySet(), new TreeCadastreEntryComparator(questionIndex)).getKey();
            if (questionIndex == TreeCadastreQueryController.INDEX_DISTRICT_OF_HIGHEST_TREE)
            {
                stringBuilder.append(intactTreeCadastre.getTreeHashMap().get(key).getSite().district());
            }
            else
            {
                stringBuilder.append(intactTreeCadastre.entryToString(key));
            }
        }
        else if (TreeCadastreQueryController.INDEX_COUNT_SPECIES <= questionIndex && questionIndex <= TreeCadastreQueryController.INDEX_COUNT_GENERA)
        {
            stringBuilder.append(TreeCadastreServices.countTreeGenusOrSpecies(intactTreeCadastre, questionIndex));
        }
        else if (TreeCadastreQueryController.INDEX_FIND_MOST_COMMON_GENUS <= questionIndex && questionIndex <= TreeCadastreQueryController.INDEX_FIND_MOST_COMMON_DISTRICT)
        {
            answer = TreeCadastreServices.findMostCommonGenusOrDistrict(statisticallyRobustTreeCadastre, questionIndex);
        }
        else if (TreeCadastreQueryController.INDEX_FIND_DISTRICT_WITH_MOST_DIFFERENT_SPECIES == questionIndex)
        {
            
            stringBuilder.append(TreeCadastreServices.findDistrictWithMostDifferentiableTreeSpecies(statisticallyRobustTreeCadastre));
        }
        else if (TreeCadastreQueryController.INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_HEIGHT == questionIndex | questionIndex == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_CIRCUMFERENCE)
        {
            answer = TreeCadastreServices.findMostExtremeGenus(intactTreeCadastre, questionIndex);
        }
        else if (questionIndex == TreeCadastreQueryController.INDEX_FIND_BERLINS_ENTIRE_CARBON_RETENTION)
        {
            stringBuilder.append(String.format("%.2f", TreeCadastreServices.calculateCarbonRetentionOfTreeCadastre(statisticallyRobustTreeCadastre) / UNIT_CONVERSION_KILOGRAMM_TO_TON));
        }
        else if (questionIndex == TreeCadastreQueryController.INDEX_FIND_DISTRICT_OF_GREATEST_CARBON_RETENTION | questionIndex == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_GREATEST_CARBON_RETENTION)
        {
            answer = TreeCadastreServices.findGenusOrDistrictWithGreatestCarbonRetention(statisticallyRobustTreeCadastre, questionIndex);
        }
        
        stringBuilder.append(answer);
        
        if (questionIndex == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_GREATEST_CARBON_RETENTION | TreeCadastreQueryController.INDEX_FIND_MOST_COMMON_GENUS == questionIndex | questionIndex == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_CIRCUMFERENCE | questionIndex == TreeCadastreQueryController.INDEX_FIND_GENUS_OF_HIGHEST_AVERAGE_HEIGHT)
        {
            stringBuilder.append(Strings.FORWARD_SLASH);
            stringBuilder.append(TreeCadastreServices.findCorrespondingGermanGenus(answer, intactTreeCadastre));
        }
        
        
        return stringBuilder.toString();
        
    }
}
