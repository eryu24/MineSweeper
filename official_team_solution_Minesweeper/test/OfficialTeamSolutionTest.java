
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import MineFieldGenerator.MFGenerator;
import OfficialSolution.OfficialTeamSolution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

class OfficialTeamSolutionTest {
    // For temporarily sending the System.out stream to this
    // byte array so that the default behavior of either the
    // MFGenerator and OfficialTeamSolution does not end up
    // doing System.out.println behavior performing unit testing.
    private static final ByteArrayOutputStream outContent
            = new ByteArrayOutputStream();

    // resets the print stream to System.out restoring the
    // printing behaviors of MFGenerator and OfficialTeamSolution.
    private static final PrintStream regularOut = System.out;


    private static int ROW;
    private static int COLUMN;
    private static int PERCENTAGE;

    // Messages when either main() from Official and/or MFGenerator outputted.
    private static final String OFFICIAL_SOLUTION_OUTPUT
            = "official solution outputted this" +
            " (DO NOT COUNT THIS MESSAGE OUTPUT AS PART OF THE OFFICIAL): ";
    private static final String GENERATED_SOLUTION_OUTPUT
            = "generated solution outputted this" +
            " (DO NOT COUNT THIS MESSAGE OUTPUT AS PART OF INPUT GENERATOR OUTPUT): ";

    // relative paths to test input minefields.
    private static final String TEST_INPUT_100BY100
            = ".data/input_test100By100.txt";
    private static final String MINESWEEPER_INPUT_TXT
            = ".data/minesweeper_input.txt";
    private static final String READ_INPUT
            = ".data/test_read_input.txt";
    private static final String SURROUNDED_BY_MINES
            = ".data/test_input_square_surrounded_by_mines.txt";

    // To hold the input file
    private static String[] inputFile;


    @BeforeEach
    void setUp() {
        ROW = 1;
        COLUMN = 1;
        PERCENTAGE = 0;
        inputFile = new String[1];
        System.setOut(new PrintStream(outContent));
    }

    // probably do not need.
    @AfterEach
    void tearDown() {
        System.setOut(regularOut);
    }

    @Test
    public void test100By100AllMines() throws FileNotFoundException {
        inputFile[0] = TEST_INPUT_100BY100;
        ROW = 100;
        COLUMN = 100;
        PERCENTAGE = 100;
        mainOutput();
        assertEquals(OfficialTeamSolution.outputString(),
                MFGenerator.generateMineFieldWithArgs(ROW, COLUMN, PERCENTAGE),
                "minefield should be 100 by 100 ALL MINES.");
    }

    @Test
    public void testMineSweeperInputFile() throws FileNotFoundException {
        inputFile[0] = MINESWEEPER_INPUT_TXT;
        mainOutput();
        MFGenerator.main(inputFile);
        assertEquals(OfficialTeamSolution.outputString(),
                MFGenerator.getAccumulatedMineFields());
    }

    @Test
    public void testReadInputCorrectly() throws FileNotFoundException {
        inputFile[0] = READ_INPUT;
        MFGenerator.main(inputFile);
        final String inputToRead = MFGenerator.getAccumulatedFormattedMineFields();
        mainOutput();
        assertEquals(OfficialTeamSolution.inputString(),
                inputToRead, "inputs did not match");
    }

    @Test
    public void testDotSurroundedByMinesIs8() throws FileNotFoundException {
        inputFile[0] = SURROUNDED_BY_MINES;
        MFGenerator.main(inputFile);
        final String outputWithHints = MFGenerator.getAccumulatedMineFields();
        mainOutput();
        assertEquals(OfficialTeamSolution.outputString(), outputWithHints,
                "outputs did not match");
    }



    private static void mainOutput() throws FileNotFoundException {
        OfficialTeamSolution.main(OfficialTeamSolutionTest.inputFile);
    }

    private static void printOfficialOutputMessage() {
        System.out.println(OFFICIAL_SOLUTION_OUTPUT);
    }

    private static void printInputGeneratedMessage() {
        System.out.println(GENERATED_SOLUTION_OUTPUT);
    }

    private static void printNewLine() {
        System.out.println();
    }
}