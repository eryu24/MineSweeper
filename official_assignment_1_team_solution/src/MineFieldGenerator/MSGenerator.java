package MineFieldGenerator;

import java.util.Random;
import java.util.Scanner;

public final class MSGenerator {

    private MSGenerator() {
        super();
    }

    // Function to generate a minefield with a specified percentage of mines using user input
    public static String generateMineField(int rows, int cols, int minePercentage) {
        Random random = new Random();
        StringBuilder mineField = new StringBuilder();
        int totalCells = rows * cols;
        int totalMines = (totalCells * minePercentage) / 100;

        // Initialize the minefield with empty squares
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mineField.append('.');
            }
            mineField.append('\n');
        }

        // Place the mines
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int cell = random.nextInt(totalCells);
            int row = cell / cols;
            int col = cell % cols;
            if (mineField.charAt(row * (cols + 1) + col) != '*') {
                mineField.setCharAt(row * (cols + 1) + col, '*');
                minesPlaced++;
            }
        }

        return mineField.toString();
    }

    // Function to generate a minefield with specified parameters directly, including rows and columns in the output
    public static String generateMineFieldWithArgs(int rows, int cols, int minePercentage) {
        String mineField = generateMineField(rows, cols, minePercentage);
        StringBuilder formattedMineField = new StringBuilder();
        formattedMineField.append("Field #").append(1).append(':').append('\n');
        //formattedMineField.append(rows).append(" ").append(cols).append("\n");
        formattedMineField.append(mineField).append('\n');
        return formattedMineField.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder allMineFields = new StringBuilder();

        while (true) {
            System.out.println("Enter the number of rows: ");
            int rows = scanner.nextInt();

            System.out.println("Enter the number of columns: ");
            int cols = scanner.nextInt();

            System.out.println("Enter the percentage of mines: ");
            int minePercentage = scanner.nextInt();

            String mineField = generateMineField(rows, cols, minePercentage);
            allMineFields.append(rows).append(" ").append(cols).append("\n");
            allMineFields.append(mineField);

            System.out.println("Would you like to create another minefield? (yes/no): ");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        allMineFields.append("0 0\n");
        System.out.println("All Generated MineFields:\n" + allMineFields.toString());

        scanner.close();
    }
}
