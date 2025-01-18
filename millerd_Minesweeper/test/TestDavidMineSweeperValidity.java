import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import MineFieldGenerator.MFGenerator;
import OfficialSolution.OfficialTeamSolution;
import org.junit.jupiter.api.Test;

/**
 * Testing the validity of the file. If you need, clear the DavidMineSweeperOutput.txt to see difference and rerun.
 */
public class TestDavidMineSweeperValidity {

    private static final Path THE_BIG_ONE_RAW = Path.of(".dataMineFields/minesweeper_input.txt");

    private static final Path THE_BIG_ONE_SOLVED = Path.of(".dataMineFields/minesweeper_output.txt");

    private final Path myBigAllocation = Path.of(".dataMineFields/DavidMineSweeperOutput.txt");

    @Test
    public void bigFileTest() throws IOException {
        System.setOut(new PrintStream(String.valueOf(myBigAllocation)));
        DavidMineSweeper.filepathInputMinesweeper(THE_BIG_ONE_RAW);

        assertEquals(readAllLines(myBigAllocation), readAllLines(THE_BIG_ONE_SOLVED),
                "There exists some mismatch, good luck!");
    }
}
