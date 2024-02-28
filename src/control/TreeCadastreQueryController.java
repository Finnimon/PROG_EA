package control;

import model.TreeCadastre;
import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Messages;
import resources.Strings;
import services.KatasterServices;
import utility.BaumKatasterEntryComparator;
import utility.MyIO;

import java.util.Collections;


/**
 * @Summary: The {@link TreeCadastreQueryController} utilizes methods from {@link KatasterServices} to answer queries regarding a {@link TreeCadastre} via the console.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class TreeCadastreQueryController
{
    
    
    public static final int INDEX_BEZIRK_MIT_GROESZTEM_BAUM = 1;
    
    
    public static final int INDEX_UMFANG_ZENTIMETER_VERGLEICHEN = 2;
    
    
    public static final int INDEX_KRONE_METER_VERGLEICHEN = 3;
    
    
    public static final int INDEX_ALTER_VERGLEICHEN = 4;
    
    
    public static final int INDEX_BAUMARTEN_ZAEHLEN = 5;
    
    
    public static final int INDEX_GATTUNGEN_ZAEHLEN = 6;
    
    
    public static final int INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN = 7;
    
    
    public static final int INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN = 8;
    
    
    public static final int INDEX_BEZIRK_MIT_MEISTEN_ARTEN = 9;
    
    
    public static final int INDEX_WELCHE_GATTUNG_WAECHST_AM_HOECHSTEN = 10;
    
    
    public static final int INDEX_WELCHE_GATTUNG_GROESZTER_UMFANG = 11;
    
    
    public static final int INDEX_KOHLENSTOFF_SPEICHERUNG_BERLIN_INSGESAMT = 12;
    
    
    public static final int INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK = 13;
    
    
    public static final int INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG = 14;
    
    
    public static final int JAHR_DER_ERHEBUNG = Constants.ZWEITAUSEND_UND_DREI_UND_ZWANZIG;
    
    
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
            MyIO.printLine(determineQueryAnswer(queryIndex));
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
            
            MyIO.resetZeitgeber();
            
            
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
     * @Summary: Determines the answer to the selected query by calling methods from {@link KatasterServices} depending on the query index.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private String determineQueryAnswer(int questionIndex)
    {
        TreeCadastre statisticallyRobustTreeCadastre = getStatisticallyRobustTreeCadastre();
        TreeCadastre intactTreeCadastre = getIntactTreeCadastre();
        
        String answer = Strings.EMPTY;
        
        StringBuilder stringBuilder = new StringBuilder(Messages.ANTWORTEN[questionIndex]);
        
        
        if (TreeCadastreQueryController.INDEX_BEZIRK_MIT_GROESZTEM_BAUM <= questionIndex && questionIndex <= TreeCadastreQueryController.INDEX_ALTER_VERGLEICHEN)
        {
            int key = Collections.max(intactTreeCadastre.getTreeHashMap().entrySet(), new BaumKatasterEntryComparator(questionIndex)).getKey();
            if (questionIndex == TreeCadastreQueryController.INDEX_BEZIRK_MIT_GROESZTEM_BAUM)
            {
                stringBuilder.append(intactTreeCadastre.getTreeHashMap().get(key).getOrt().getBezirk());
            }
            else
            {
                stringBuilder.append(intactTreeCadastre.entryToString(key));
            }
        }
        else if (TreeCadastreQueryController.INDEX_BAUMARTEN_ZAEHLEN <= questionIndex && questionIndex <= TreeCadastreQueryController.INDEX_GATTUNGEN_ZAEHLEN)
        {
            stringBuilder.append(KatasterServices.baumArtenGattungenZaehlen(intactTreeCadastre, questionIndex));
        }
        else if (TreeCadastreQueryController.INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN <= questionIndex && questionIndex <= TreeCadastreQueryController.INDEX_HAEUFIGSTEN_BEZIRK_ZAEHLEN)
        {
            answer = KatasterServices.haeufigsteGattungBezirkZaehlen(statisticallyRobustTreeCadastre, questionIndex);
        }
        else if (TreeCadastreQueryController.INDEX_BEZIRK_MIT_MEISTEN_ARTEN == questionIndex)
        {
            
            stringBuilder.append(KatasterServices.bezirkMitMeistenBaumArtenFinden(statisticallyRobustTreeCadastre));
        }
        else if (TreeCadastreQueryController.INDEX_WELCHE_GATTUNG_WAECHST_AM_HOECHSTEN == questionIndex | questionIndex == TreeCadastreQueryController.INDEX_WELCHE_GATTUNG_GROESZTER_UMFANG)
        {
            answer = KatasterServices.extremsteDurchschnittlicheGattungFinden(intactTreeCadastre, questionIndex);
        }
        else if (questionIndex == TreeCadastreQueryController.INDEX_KOHLENSTOFF_SPEICHERUNG_BERLIN_INSGESAMT)
        {
            stringBuilder.append(String.format("%.2f", KatasterServices.kohlenStoffSpeicherungInKiloGrammInsgesamtBerechnen(statisticallyRobustTreeCadastre) / UNIT_CONVERSION_KILOGRAMM_TO_TON));
        }
        else if (questionIndex == TreeCadastreQueryController.INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_BEZIRK | questionIndex == TreeCadastreQueryController.INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG)
        {
            answer = KatasterServices.kohlenStoffSpeicherungStaerksteBezirkOderGattungFinden(statisticallyRobustTreeCadastre, questionIndex);
        }
        
        stringBuilder.append(answer);
        
        if (questionIndex == TreeCadastreQueryController.INDEX_KOHLENSTOFF_SPEICHERUNG_NACH_GATTUNG | TreeCadastreQueryController.INDEX_HAEUFIGSTE_GATTUNG_ZAEHLEN == questionIndex | questionIndex == TreeCadastreQueryController.INDEX_WELCHE_GATTUNG_GROESZTER_UMFANG | questionIndex == TreeCadastreQueryController.INDEX_WELCHE_GATTUNG_WAECHST_AM_HOECHSTEN)
        {
            stringBuilder.append(Strings.FORWARD_SLASH);
            stringBuilder.append(KatasterServices.findCorrespondingGermanGenus(answer, intactTreeCadastre));
        }
        
        
        return stringBuilder.toString();
        
    }
}
