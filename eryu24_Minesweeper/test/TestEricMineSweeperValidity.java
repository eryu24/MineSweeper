import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing the validity of the file. If you need, clear the DavidMineSweeperOutput.txt to see difference and rerun.
 */
public class TestEricMineSweeperValidity {

    private static final Path THE_BIG_ONE_RAW = Path.of(".dataMineFields/minesweeper_input.txt");

    private static final Path THE_BIG_ONE_SOLVED = Path.of(".dataMineFields/minesweeper_output.txt");

    private final Path myBigAllocation = Path.of(".dataMineFields/EricMineSweeperOutput.txt");

    @Test
    public void bigFileTest() throws IOException {
        //Do through console and check here.

        assertEquals(readAllLines(myBigAllocation), readAllLines(THE_BIG_ONE_SOLVED),
                "This one is an exception because there is space at the end of the program.");
    }
}
