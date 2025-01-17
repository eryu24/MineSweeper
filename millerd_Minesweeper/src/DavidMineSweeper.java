import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * There exists a method to take console output called "consoleInputMinesweeper".
 * The solution here is tested against the official input file and is verified in the test folder.
 */
public class DavidMineSweeper {

    private static final int[][] CHECK_ALL =
                    {{1,1},{1,0},{0,1},
                    {-1,0},{0,-1},{-1,1},
                    {1,-1},{-1,-1}};

    public static void main(String[] args) throws FileNotFoundException {
        consoleInputMinesweeper();
    }

    private DavidMineSweeper() {
        super(); //instantiation
    }

    public static void consoleInputMinesweeper() {
        Scanner input = new Scanner(System.in);
        int width = input.nextInt();
        int length = input.nextInt();
        int fieldNum = 1;

        while (width != 0 && length != 0) {

            char[][] someGrid = new char[width][length];

            gridFill(someGrid, width, length, input);

            placeHints(someGrid, width, length);

            displayGrid(someGrid, fieldNum);

            width = input.nextInt();
            length = input.nextInt();
            fieldNum++;
        }
    }

    public static void filepathInputMinesweeper(final Path theDirectory) throws IOException {
        Scanner input = new Scanner(theDirectory);
        int width = input.nextInt();
        int length = input.nextInt();
        int fieldNum = 1;

        while (width != 0 && length != 0) {

            char[][] someGrid = new char[width][length];

            gridFill(someGrid, width, length, input);

            placeHints(someGrid, width, length);

            displayGrid(someGrid, fieldNum);

            width = input.nextInt();
            length = input.nextInt();
            fieldNum++;
        }
    }

    private static void displayGrid(char[][] theGrid, final int theField)  {
        System.out.println("Field #" + theField + ":");
        for (char[] theInside : theGrid) {
            for (char theFurthest : theInside) {
                System.out.print(theFurthest);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    private static void gridFill(char[][] theGrid, final int theWidth, final int theLength, Scanner theInput) {
        //creates graph
        theInput.nextLine();
        String smallVal;
        for (int i = 0; i < theWidth; i++) {
            smallVal = theInput.nextLine();
            //this part maps out the start of the parsing
            for (int j = 0; j < theLength; j++) {
                if (smallVal.charAt(j) == '.') {
                    theGrid[i][j] = '0';
                } else if (smallVal.charAt(j) == '*') {
                    theGrid[i][j] = '*';
                }
            }
        }
    }


    private static void placeHints(char[][] theGrid, final int theWidth, final int theLength) {
        //this part puts the hints
        for (int i = 0; i < theWidth; i++) {
            for (int j = 0; j < theLength; j++) {
                if (theGrid[i][j] == '*') {
                    for (int[] soPot : CHECK_ALL) {
                        //checks boundary of board.
                        if (soPot[0] + i <= theWidth-1 && soPot[0] + i >= 0
                                && soPot[1] + j <= theLength-1 && soPot[1] + j >= 0) {
                            //checks if new area
                            if (theGrid[soPot[0] + i][soPot[1] + j] == '0' &&
                                    theGrid[soPot[0] + i][soPot[1] + j] != '*') {
                                theGrid[soPot[0] + i][soPot[1] + j] = '1';
                            } //adds to it if old area since mines don't go above 8.
                            else if (theGrid[soPot[0] + i][soPot[1] + j] != '0' &&
                                    theGrid[soPot[0] + i][soPot[1] + j] != '*') {
                                theGrid[soPot[0] + i][soPot[1] + j] =
                                        (char)((int) theGrid[soPot[0] + i][soPot[1] + j] + 1);
                            }
                        }
                    }
                }
            }
        }
    }
}