package control;

import model.TreeCadastre;
import model.CSVRecord;
import org.jetbrains.annotations.NotNull;
import resources.Constants;
import resources.Messages;
import resources.Strings;
import utility.CSVParser;
import utility.MyIO;

import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * @Summary: Controller for all the program's tasks containing only a constructor and instance methods. All of these methods express the main functionality of the program and keep the user informed about their progress via {@link MyIO}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class TaskController
{
    
    
    /**
     * @Precondition: {@link Main#ARGUMENTS} is not null. {@link Main#ARGUMENTS#size()} == 5:
     * @Postcondition: The program is initialized correctly.
     * @Summary: This constructor instantiates this class after the program has been initialized.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    protected TaskController()
    {
        MyIO.printLine(Messages.AUSGABE_PROGRAMM_INITIALISIERT, true);
    }
    
    
    /**
     * @return {@link TreeCadastre} with the data from {@link Strings#BAUMKATASTER_FILEPATH}.
     * @Precondition: {@link Strings#BAUMKATASTER_FILEPATH} is correct.
     * @Postcondition: {@link TreeCadastre} is created from {@link Strings#BAUMKATASTER_FILEPATH} and returned.
     * @Summary: This method reads the data from {@link Strings#BAUMKATASTER_FILEPATH} and creates a {@link TreeCadastre} from it.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    protected TreeCadastre parseCsvIntoBaumKataster()
    {
        MyIO.printLine(Messages.AUSGABE_LESE_AUS_DATEI + Strings.BAUMKATASTER_FILEPATH, false);
        CSVParser cSVParser;
        
        try
        {
            cSVParser = new CSVParser(Strings.BAUMKATASTER_FILEPATH, Strings.DELIMITER, Constants.CSV_RECORD_LENGTH);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        ArrayList<CSVRecord> cSVRecords = cSVParser.parse(false);
        
        MyIO.printLine(Messages.AUSGABE_ANZAHL_ERFOLGREICH_EINGELESENE_ZEILEN + cSVRecords.size(), true);
        
        TreeCadastre treeCadastre = new TreeCadastre(cSVRecords, Main.ARGUMENTS);
        MyIO.printLine(Messages.AUSGABE_ANZAHL_ERZEUGTER_BAUM_INSTANZEN + treeCadastre.getTreeHashMap().keySet().size(), true);
        
        
        return treeCadastre;
    }
    
    
    /**
     * @param treeCadastre is passed by heap reference and therefore {@link StatisticalDataRepairCenter#deepRepair()}ed in this method.
     * @return a deepCopy of the {@link TreeCadastre} parameter after it has been {@link StatisticalDataRepairCenter#shallowRepair()}ed.
     * @Precondition: The Precondition of {@link #parseCsvIntoBaumKataster()} is met.
     * @Postcondition: The {@link TreeCadastre} parameter has been {@link StatisticalDataRepairCenter#shallowRepair()}ed deep-copied and returned. The {@link TreeCadastre} parameter is modified by {@link StatisticalDataRepairCenter#deepRepair()}.
     * @Summary: The parameter is repaired by {@link StatisticalDataRepairCenter#deepRepair()} via heap reference and then an only {@link StatisticalDataRepairCenter#shallowRepair()}ed deep-copy is returned.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    protected TreeCadastre dataRepair(@NotNull TreeCadastre treeCadastre)
    {
        StatisticalDataRepairCenter statisticalDataRepairCenter = new StatisticalDataRepairCenter(treeCadastre);
        
        
        TreeCadastre shallowRepairedTreeCadastre = (TreeCadastre) statisticalDataRepairCenter.getShallowRepairedStatistic().clone();
        
        MyIO.printLine(Messages.AUSGABE_ANZAHL_GELOESCHTE_DATENSAETZE + treeCadastre.getDeletedDataSetKeys().size(), true);
        
        statisticalDataRepairCenter.deepRepair();
        
        MyIO.printLine(Messages.AUSGABE_ANZAHL_BEARBEITETE_DATENSAETZE + treeCadastre.getEditedDataSetKeys().size(), true);
        
        
        return shallowRepairedTreeCadastre;
    }
    
    
    /**
     * @param shallowRepairedTreeCadastre a {@link TreeCadastre} that has been only {@link StatisticalDataRepairCenter#shallowRepair()}ed.
     * @param deepRepairedTreeCadastre a {@link TreeCadastre} that has been {@link StatisticalDataRepairCenter#deepRepair()}ed.
     * @Precondition: The parameters are passed correctly.
     * @Postcondition: The queries will be offered and answered correctly in the console.
     * @Summary: Offers and answers 14 different queries as well as the option to end the program.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    protected void offerAndAnswerQueries(@NotNull TreeCadastre shallowRepairedTreeCadastre,@NotNull TreeCadastre deepRepairedTreeCadastre)
    {
        TreeCadastreQueryController treeCadastreQueryController =new TreeCadastreQueryController(deepRepairedTreeCadastre,shallowRepairedTreeCadastre);
        treeCadastreQueryController.offerAndAnswerQueriesThroughConsole();
    }
    
    
}
