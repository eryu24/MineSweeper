package srcTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import MineFieldGenerator.MSGenerator;
import OfficialSolution.OfficialTeamSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

class OfficialTeamSolutionTest {
    private static int ROW;
    private static int COLUMN;
    private static int PERCENTAGE;


    @AfterEach
    void setUp() {
        ROW = 1;
        COLUMN = 1;
        PERCENTAGE = 0;
    }

    @Test
    public void test100By100AllMines() throws FileNotFoundException {
        String[] s = {"tests\\srcTests\\testResources\\input_test100By100.txt"};
        ROW = 100;
        COLUMN = 100;
        PERCENTAGE = 100;
        //MSGenerator.generateMineFieldWithArgs(ROW, COLUMN, PERCENTAGE);
        OfficialTeamSolution.main(s);
        //System.out.println(OfficialTeamSolution.outputString());
        //System.out.println(MSGenerator.generateMineFieldWithArgs(ROW, COLUMN, PERCENTAGE));
        assertEquals(OfficialTeamSolution.outputString(),
                MSGenerator.generateMineFieldWithArgs(ROW, COLUMN, PERCENTAGE),
                "minefield should be 100 by 100.");
    }

}