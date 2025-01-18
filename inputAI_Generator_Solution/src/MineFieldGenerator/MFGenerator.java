package MineFieldGenerator;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public final class MFGenerator {

    private MFGenerator() {
        super();
    }

    // Static StringBuilder to accumulate minefields with hints
    private static final StringBuilder accumulatedMineFields = new StringBuilder();
    // Static StringBuilder to accumulate minefields without hints
    private static final StringBuilder accumulatedFormattedMineFields = new StringBuilder();

    // Function to generate hints for a given minefield
    private static String generateHints(final char[][] field, final int rows, final int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == '*') {
                    continue;
                }
                int mineCount = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int ni = i + x;
                        int nj = j + y;
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && field[ni][nj] == '*') {
                            mineCount++;
                        }
                    }
                }
                field[i][j] = (mineCount > 0) ? (char) (mineCount + '0') : '0';
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.append(field[i][j]);
            }
            result.append('\n');
        }

        return result.toString();
    }

    // Method to accumulate formatted minefields without hints
    private static void accumulateFormattedMineFields(final char[][] field, final int rows, final int cols) {
        accumulatedFormattedMineFields.append(rows).append(' ').append(cols).append('\n');
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                accumulatedFormattedMineFields.append(field[i][j]);
            }
            accumulatedFormattedMineFields.append('\n');
        }
    }

    public static String generateMineField(final int rows, final int cols, final int minePercentage) {
        Random random = new Random();
        char[][] field = new char[rows][cols];

        // Initialize the minefield with empty squares
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = '.';
            }
        }

        int totalCells = rows * cols;
        int totalMines = (totalCells * minePercentage) / 100;

        // Place the mines
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int cell = random.nextInt(totalCells);
            int row = cell / cols;
            int col = cell % cols;
            if (field[row][col] != '*') {
                field[row][col] = '*';
                minesPlaced++;
            }
        }

        // Accumulate the minefields in the formatted StringBuilder without hints
        accumulateFormattedMineFields(field, rows, cols);

        // Generate hints for the minefield
        return generateHints(field, rows, cols);
    }

    public static String generateMineFieldWithArgs(final int rows, final int cols, final int minePercentage) {
        String mineField = generateMineField(rows, cols, minePercentage);
        StringBuilder formattedMineField = new StringBuilder();
        formattedMineField.append("Field #").append(1).append(':').append('\n');

        // Display the minefield with mines and hints
        formattedMineField.append(mineField).append('\n');

        return formattedMineField.toString();
    }

    public static void main(final String[] args) {
        Scanner scanner = null;
        int fieldNumber = 1; // Start the field numbering at 1

        if (args.length > 0) {
            // If an input file is provided, use it
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0]);
                return;
            }
        } else {
            // Otherwise, use the standard input
            scanner = new Scanner(System.in);
        }

        while (true) {
            int rows = 0, cols = 0;

            try {
                if (args.length > 0) {
                    // If reading from a file, read the parameters from the file
                    if (scanner.hasNextInt()) rows = scanner.nextInt();
                    else break;
                    if (scanner.hasNextInt()) cols = scanner.nextInt();
                    else break;
                    if (rows == 0 && cols == 0) {
                        // End of file input
                        break;
                    }

                    char[][] field = new char[rows][cols];
                    scanner.nextLine(); // Move to the next line
                    for (int i = 0; i < rows; i++) {
                        if (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            for (int j = 0; j < cols; j++) {
                                field[i][j] = line.charAt(j);
                            }
                        }
                    }

                    // Accumulate formatted minefields without hints
                    accumulateFormattedMineFields(field, rows, cols);

                    String mineFieldWithHints = generateHints(field, rows, cols);

                    // Accumulate the minefields with hints in the StringBuilder
                    accumulatedMineFields.append("Field #").append(fieldNumber).append(":\n");
                    accumulatedMineFields.append(mineFieldWithHints).append('\n');
                    fieldNumber++; // Increment the field number

                } else {
                    // If reading from the standard input, prompt the user for input
                    System.out.println("Enter the number of rows: ");
                    rows = scanner.nextInt();

                    System.out.println("Enter the number of columns: ");
                    cols = scanner.nextInt();

                    System.out.println("Enter the percentage of mines: ");
                    int minePercentage = scanner.nextInt();

                    String mineField = generateMineField(rows, cols, minePercentage);

                    // Accumulate the minefields with hints in the StringBuilder
                    accumulatedMineFields.append("Field #").append(fieldNumber).append(":\n");
                    accumulatedMineFields.append(mineField).append('\n');
                    fieldNumber++; // Increment the field number

                    // Ask the user whether to create another minefield
                    System.out.println("Would you like to create another minefield? (yes/no): ");
                    String choice = scanner.next();
                    if (!choice.equalsIgnoreCase("yes")) {
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter integers only.");
                break;
            }
        }

        // Print all accumulated formatted minefields without hints
        System.out.print(getAccumulatedFormattedMineFields());

        if (scanner != null) {
            scanner.close();
        }
    }

    // Getter method for accumulated minefields with hints
    public static String getAccumulatedMineFields() {
        return accumulatedMineFields.toString();
    }

    // Getter method for accumulated formatted minefields without hints
    public static String getAccumulatedFormattedMineFields() {
        return accumulatedFormattedMineFields.toString();
    }
}




