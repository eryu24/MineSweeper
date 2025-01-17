package srcTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import MineFieldGenerator.MFGenerator;
import OfficialSolution.OfficialTeamSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

class OfficialTeamSolutionTest {
    private static int ROW;
    private static int COLUMN;
    private static int PERCENTAGE;

    // Messages when either main() from Official and/or MFGenerator outputted.
    private static final String OFFICIAL_SOLUTION_OUTPUT
            = "official solution outputted this" +
            " (DO NOT COUNT THIS MESSAGE OUTPUT AS PART OF THE OFFICIAL): ";
    private static final String GENERATED_SOLUTION_OUTPUT
            = "official solution outputted this" +
            " (DO NOT COUNT THIS MESSAGE OUTPUT AS PART OF THE GENERATOR OUTPUT): ";

    // relative paths to test input minefields.
    private static final String TEST_INPUT_100BY100
            = "eryu24_Minesweeper\\official_assignment_1_team_solution\\tests\\srcTests\\testResources\\input_test100By100.txt";
    private static final String MINESWEEPER_INPUT_TXT
            = "eryu24_Minesweeper\\official_assignment_1_team_solution\\tests\\srcTests\\testResources\\minesweeper_input.txt";

    // To hold the input file
    private static final String[] inputFiles = new String[1];


    @AfterEach
    void setUp() {
        ROW = 1;
        COLUMN = 1;
        PERCENTAGE = 0;
        inputFiles[0] = null;
    }

    @Test
    public void test100By100AllMines() throws FileNotFoundException {
        inputFiles[0] = TEST_INPUT_100BY100;
        ROW = 100;
        COLUMN = 100;
        PERCENTAGE = 100;
        printOfficialOutputMessage();
        mainOutput(inputFiles);
        assertEquals(OfficialTeamSolution.outputString(),
                MFGenerator.generateMineFieldWithArgs(ROW, COLUMN, PERCENTAGE),
                "minefield should be 100 by 100 ALL MINES.");
    }

    @Test
    public void testMineSweeperInputFile() throws FileNotFoundException {
        inputFiles[0] = MINESWEEPER_INPUT_TXT;
        printOfficialOutputMessage();
        mainOutput(inputFiles);
        printNewLine();
        printAIGeneratedMessage();
        MFGenerator.main(inputFiles);
        assertEquals(OfficialTeamSolution.outputString(), MFGenerator.getAccumulatedMineFields());
    }

    @Test
    public void testReadInputCorrectly() {

    }

    private static void mainOutput(final String[] theArgs) throws FileNotFoundException {
        OfficialTeamSolution.main(theArgs);
    }

    private static void printOfficialOutputMessage() {
        System.out.println(OFFICIAL_SOLUTION_OUTPUT);
    }

    private static void printAIGeneratedMessage() {
        System.out.println(GENERATED_SOLUTION_OUTPUT);
    }

    private static void printNewLine() {
        System.out.println();
    }
}