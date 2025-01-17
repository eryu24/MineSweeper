import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class reads in multiple Minesweeper fields (until 0 0 is read),
 * processes each field to count adjacent mines, and prints out the
 * formatted results.
 *
 * @author Eric Ryu
 * @author Dylon Bernal
 * @author David Miller
 * @version 1.1
 */
public final class OfficialTeamSolution {
    /** Character representing a mine. */
    private static final char MINE = '*';

    /** Number of padding rows/columns around the board. */
    private static final int PADDING = 2;

    /** Zero used as a termination condition. */
    private static final int TERMINATOR = 0;

    /** Accumulates program output across all fields. */
    private static final StringBuilder OUTPUT = new StringBuilder();

    /** Accumulates the input read in, for reference or debugging. */
    private static final StringBuilder INPUT = new StringBuilder();

    /**
     * Private constructor to prevent instantiation.
     */
    private OfficialTeamSolution(){
        // Prevents Instantiation
    }

    /**
     * Main entry point. Reads the Minesweeper fields from either a file
     * (if a filename is given in args) or from standard input, processes
     * them, and prints the results.
     */
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

    /**
     * Reads a Minesweeper field from the given Scanner.
     * Each field is stored in a 2D character array with padding around the edges.
     *
     * @param theScanner the Scanner to read the field from
     * @param theRows number of rows in the field (not including padding)
     * @param theCols number of columns in the field (not including padding)
     * @return a 2D char array representing the Minesweeper field,
     *         padded with one row/column on each side
     */
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

    /**
     * Processes a single Minesweeper field by counting adjacent mines
     * for each cell, and appending the results to the global OUTPUT.
     *
     * @param theField the 2D char array representing the field (with padding)
     * @param theRows the number of rows in the core field (not including padding)
     * @param theCols the number of columns in the core field (not including padding)
     * @param theFieldNumber the sequential number of this field (e.g. 1, 2, 3, ...)
     */
    private static void processField(final char[][] theField, final int theRows,
                                     final int theCols, final int theFieldNumber) {
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

    /**
     * Counts the number of mines in the adjacent cells (including diagonals)
     * of the specified row and column in the field.
     *
     * @param theField the 2D char array with padding
     * @param theRow the row of the cell to check
     * @param theCol the column of the cell to check
     * @return the number of adjacent mines around the given cell
     */
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

    /**
     * Returns the complete output of all processed fields as a single String.
     *
     * @return the accumulated output
     */
    public static String outputString() {
        return OUTPUT.toString();
    }

    /**
     * Returns the complete input that has been read and stored, as a single String.
     *
     * @return the accumulated input
     */
    public static String inputString() {
        return INPUT.toString();
    }
}
