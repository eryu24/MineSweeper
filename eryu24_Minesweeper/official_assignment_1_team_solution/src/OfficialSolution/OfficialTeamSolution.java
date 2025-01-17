package OfficialSolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Minesweeper program
 *
 * @author Eric Ryu
 */
public final class OfficialTeamSolution {
    private static final char MINE = '*';
    private static final int PADDING = 2;
    private static final int TERMINATOR = 0;
    private static final StringBuilder OUTPUT = new StringBuilder();
    private static final StringBuilder INPUT = new StringBuilder();

    private OfficialTeamSolution(){
        // Prevents Instantiation
    }

    public static void main(final String[] theArgs) throws FileNotFoundException {
        Scanner scanner = theArgs.length == 1 ?
                new Scanner(new File(theArgs[0])) : new Scanner(System.in);

        int fieldNumber = 1;

        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            if (n == TERMINATOR && m == TERMINATOR) {
                break;
            }
            char[][] field = readFieldFromInput(scanner, n, m);

            processField(field, n, m, fieldNumber);

            fieldNumber++;
        }

        scanner.close();
        System.out.println(OfficialTeamSolution.outputString());

    }

    private static char[][] readFieldFromInput(final Scanner theScanner, final int theRows,
                                               final int theCols) {
        // Create padded field hence +2
        char[][] field = new char[theRows + PADDING][theCols + PADDING];
        INPUT.append(theRows).append(' ').append(theCols).append('\n');

        // Skip to next line
        theScanner.nextLine();
        // First grab input line by line
        for (int i = 0; i < theRows; i++) {
            String line = theScanner.nextLine();

            // Then place character by character
            for (int j = 0; j < theCols; j++) {
                field[i + 1][j + 1] = line.charAt(j);
                INPUT.append(line.charAt(j));
            }
            INPUT.append('\n');
        }

        return field;
    }

    private static void processField(final char[][] theField, final int theRows,
                                     final int theCols, final int theFieldNumber) {
        if (theFieldNumber > 1) {
            // Print a blank line before each new field
            //System.out.println();
        }

        OUTPUT.append("Field #").append(theFieldNumber).append(":").append('\n');
        //System.out.println("Field #" + theFieldNumber + ":");

        // Generate the output for the field
        for (int i = 1; i <= theRows; i++) {
            for (int j = 1; j <= theCols; j++) {
                if (theField[i][j] == MINE) {
                    // Print mines
                    OUTPUT.append(MINE);
                } else {
                    // Print mine count
                    OUTPUT.append(countAdjacentMines(theField, i, j));
                }
            }
            OUTPUT.append('\n');
        }
        OUTPUT.append('\n');
    }

    private static int countAdjacentMines(final char[][] theField, final int theRow,
                                          final int theCol) {
        int count = 0;
        for(int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(theField[theRow + i][theCol + j] == MINE) {
                    count++;
                }
            }
        }
        return count;
    }

    public static String outputString() {
        return OUTPUT.toString();
    }

    public static String inputString() {
        return INPUT.toString();
    }
}
