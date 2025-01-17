import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing the validity of the file. If you need, clear the DavidMineSweeperOutput.txt to see difference and rerun.
 */
public class TestDylonMineSweeperValidity {

    private static final Path THE_BIG_ONE_RAW = Path.of(".data/minesweeper_input.txt");

    private static final Path THE_BIG_ONE_SOLVED = Path.of(".data/minesweeper_output.txt");

    private final Path myBigAllocation = Path.of(".data/DylonMineSweeperOutput.txt");

    @Test
    public void bigFileTest() throws IOException {
        System.setOut(new PrintStream(String.valueOf(myBigAllocation)));
        DylonMineSweeper.readInput(new Scanner(new File(String.valueOf(THE_BIG_ONE_RAW))));

        assertEquals(readAllLines(myBigAllocation), readAllLines(THE_BIG_ONE_SOLVED),
                "There exists some mismatch, good luck!");
    }
}
