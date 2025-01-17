import java.util.Scanner;

/**
 * Minesweeper program
 *
 * @author Eric Ryu
 */
public final class MineSweeper {
    private static final char MINE = '*';
    private static final int PADDING = 2;
    private static final int TERMINATOR = 0;

    private MineSweeper(){
        // Prevents Instantiation
    }

    public static void main(final String[] theArgs) {
        Scanner scanner = new Scanner(System.in);
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

    }

    private static char[][] readFieldFromInput(final Scanner theScanner, final int theRows,
                                               final int theCols) {
        // Create padded field hence +2
        char[][] field = new char[theRows + PADDING][theCols + PADDING];

        // Skip to next line
        theScanner.nextLine();
        // First grab input line by line
        for (int i = 0; i < theRows; i++) {
            String line = theScanner.nextLine();

            // Then place character by character
            for (int j = 0; j < theCols; j++) {
                field[i + 1][j + 1] = line.charAt(j);
            }
        }
        return field;
    }

    private static void processField(final char[][] theField, final int theRows,
                                     final int theCols, final int theFieldNumber) {
        if (theFieldNumber > 1) {
            // Print a blank line before each new field
            System.out.println();
        }

        System.out.println("Field #" + theFieldNumber + ":");

        // Generate the output for the field
        for (int i = 1; i <= theRows; i++) {
            for (int j = 1; j <= theCols; j++) {
                if (theField[i][j] == MINE) {
                    // Print mines
                    System.out.print(MINE);
                } else {
                    // Print mine count
                    System.out.print(countAdjacentMines(theField, i, j));
                }
            }
            System.out.println();
        }
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

}