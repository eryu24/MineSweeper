package OfficialSolutionTest;

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
    // MFGenerator and OfficialSolution.OfficialTeamSolution does not end up
    // doing System.out.println behavior performing unit testing.
    private static final ByteArrayOutputStream outContent
            = new ByteArrayOutputStream();

    // resets the print stream to System.out restoring the
    // printing behaviors of MFGenerator and OfficialSolution.OfficialTeamSolution.
    private static final PrintStream regularOut = System.out;

    // relative paths to test input minefields.
    private static final String TEST_INPUT_100BY100
            = ".dataMineFields\\input_test100By100.txt";
    private static final String MINESWEEPER_INPUT_TXT
            = ".dataMineFields\\minesweeper_input.txt";
    private static final String READ_INPUT
            = ".dataMineFields\\test_read_input.txt";
    private static final String SURROUNDED_BY_MINES
            = ".dataMineFields\\test_input_square_surrounded_by_mines.txt";
    private static final String TEAM_MINESWEEPER_INPUT
            = ".dataMineFields\\team_minesweeper_input.txt";

    // To hold the input file
    private static String[] inputFile;

    @BeforeEach
    void setUp() {
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
        MFGenerator.main(inputFile);
        final String generatorOutput = MFGenerator.getAccumulatedMineFields();
        mainOutput();
        assertEquals(OfficialTeamSolution.outputString(),
                generatorOutput,
                "minefield should be 100 by 100 ALL MINES.");
    }

    @Test
    public void testMineSweeperInputFile() throws FileNotFoundException {
        inputFile[0] = MINESWEEPER_INPUT_TXT;
        mainOutput();
        MFGenerator.main(inputFile);
        assertEquals(OfficialTeamSolution.outputString(),
                MFGenerator.getAccumulatedMineFields(),
                "minesweeper_input.txt outputted incorrectly from official solution.");
    }

    @Test
    public void testReadInputCorrectly() throws FileNotFoundException {
        inputFile[0] = READ_INPUT;
        MFGenerator.main(inputFile);
        final String inputToRead = MFGenerator.getAccumulatedFormattedMineFields();
        mainOutput();
        assertEquals(OfficialTeamSolution.inputString(),
                inputToRead, "the official solution read input incorrectly.");
    }

    @Test
    public void testDotSurroundedByMinesIs8() throws FileNotFoundException {
        inputFile[0] = SURROUNDED_BY_MINES;
        MFGenerator.main(inputFile);
        final String generatorOutput = MFGenerator.getAccumulatedMineFields();
        mainOutput();
        assertEquals(OfficialTeamSolution.outputString(), generatorOutput,
                "Middle square should be 8 surrounded mines.");
    }


    @Test
    public void testTeamInputFile() throws FileNotFoundException {
        inputFile[0] = TEAM_MINESWEEPER_INPUT;
        MFGenerator.main(inputFile);
        final String generatorOutput = MFGenerator.getAccumulatedMineFields();
        mainOutput();
        assertEquals(OfficialTeamSolution.outputString(), generatorOutput,
                "official solution outputted incorrectly based on team input file.");
    }



    private static void mainOutput() throws FileNotFoundException {
        OfficialTeamSolution.main(OfficialTeamSolutionTest.inputFile);
    }
}