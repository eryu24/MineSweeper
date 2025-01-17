import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *  Demonstrating the hint aspect of the game MineSweeper
 *  by taking in either keyboard input from user or
 *  file (i.e. txt files) input from the command prompt
 *  and displaying each inputted minefield in the form of
 *  showing the mines with the star symbol "*", and all
 *  hints for each square on the board.
 *
 * @author Dylon Bernal
 */
public final class DylonMineSweeper {
    private static final int N = 1;
    private static final int M = 1;
    private static final String END_OF_INPUT_SEQUENCE = "0 0";
    private static final String FIELD_NUMBER = "Field #";
    private static final StringBuilder myMineFieldOutput = new StringBuilder();

    private static char[][] myMineField = new char[N][M];
    private static int myFieldCounter = 1;
    private static int myRowCount = 0;
    private static int myRow;
    private static int myColumn;

    private DylonMineSweeper() {
        super();
    }

    public static void main(final String [] args) throws FileNotFoundException {
        //final boolean argumentFromCMD = args.length == 1;
        //Scanner input = new Scanner(System.in);
        Scanner input = new Scanner(
                new File(".data/minesweeper_input.txt"));

        if (args.length == 1) {
            //assuming if there is 1 argument on the command line
            // it is the name of a file to use for testing
            //input = new Scanner(new File(args[0]));
            input = new Scanner(new File(args[0]));
        }

        readInput(input);
    }



    public static void readInput(final Scanner theInput) {
        int fieldSeparateAfterFirst = 0;
        boolean inputContainsNoZero = true;
        String consoleInput;

        do {
            if (theInput.hasNextInt()) {
                consoleInput = theInput.nextLine();
                if (consoleInput.contains(END_OF_INPUT_SEQUENCE)) {
                    inputContainsNoZero = false;
                } else {
                    myMineFieldOutput
                            .append(fieldSeparateAfterFirst == 1 ?
                                    "\n" + FIELD_NUMBER : FIELD_NUMBER)
                            .append(myFieldCounter)
                            .append(':').append('\n');
                    fieldSeparateAfterFirst = 1;
                }
                myFieldCounter++;
            } else {
                consoleInput = theInput.nextLine();
            }

            if (inputContainsNoZero) {
                developHints(consoleInput);
            }
        } while (inputContainsNoZero);

        System.out.println(myMineFieldOutput);
    }

    private static void developHints(final String theConsoleInput) {
        final Scanner theInput = new Scanner(theConsoleInput);
        String mineFieldInput;

        if (theInput.hasNextInt()) {
            myRow = theInput.nextInt();
            myColumn = theInput.nextInt();
            myMineField = new char[myRow][myColumn];
            myRowCount = 0;
        } else {
            mineFieldInput = theConsoleInput;
            for (int m = 0; m < myColumn; m++) {
                if (mineFieldInput.charAt(m) != '*') {
                    myMineField[myRowCount][m] = (char) (mineFieldInput.charAt(m) + 2);
                } else {
                    myMineField[myRowCount][m] = mineFieldInput.charAt(m);
                }
            }
            myRowCount++;
            if (myRowCount == myRow) {
                findMinesOfSquares();
            }
        }
    }

    private static void findMinesOfSquares() {
        for (int i = 0; i < myRow; i++) {
            for (int j = 0; j < myColumn; j++) {
                if (myMineField[i][j] == '*') {
                    myMineFieldOutput.append(myMineField[i][j]);
                } else {
                    findAdjacentSquares(i, j);
                    myMineFieldOutput.append(myMineField[i][j]);
                }
            }
            myMineFieldOutput.append('\n');
        }
    }

    private static void findAdjacentSquares(final int theRow,
                                            final int theColumn) {
        findHorizontalSquares(theRow, theColumn,
                theColumn - 1, theColumn + 1);
        findVerticalSquares(theRow, theColumn,
                theRow - 1, theRow + 1);
        findUpLeftSquare(theRow, theColumn,
                theRow - 1, theColumn - 1);
        findDownLeftSquare(theRow, theColumn,
                theRow + 1, theColumn - 1);
        findUpRightSquare(theRow, theColumn,
                theRow - 1, theColumn + 1);
        findDownRightSquare(theRow, theColumn,
                theRow + 1, theColumn + 1);
    }

    private static void findHorizontalSquares(final int theRow,
                                              final int theColumn,
                                              final int theLeftSquare,
                                              final int theRightSquare) {
        boolean mineExists = false;
        int countMines = 0;
        if (theLeftSquare >= 0 && theRightSquare < myColumn) {
            if (myMineField[theRow][theLeftSquare] == '*') {
                mineExists = true;
                countMines++;
            }
            if (myMineField[theRow][theRightSquare] == '*') {
                mineExists = true;
                countMines++;
            }
        } else if (theLeftSquare < 0 && theRightSquare < myColumn) {
            if (myMineField[theRow][theRightSquare] == '*') {
                mineExists = true;
                countMines++;
            }
        } else if (theLeftSquare >= 0) {
            if (myMineField[theRow][theLeftSquare] == '*') {
                mineExists = true;
                countMines++;
            }
        }
        if (mineExists) {
            myMineField[theRow][theColumn] = (char) (myMineField[theRow][theColumn] + countMines);
        }
    }

    private static void findVerticalSquares(final int theRow,
                                            final int theColumn,
                                            final int theUpSquare,
                                            final int theDownSquare) {
        boolean mineExists = false;
        int countMines = 0;
        if (theUpSquare >= 0 && theDownSquare < myRow) {
            if (myMineField[theUpSquare][theColumn] == '*') {
                mineExists = true;
                countMines++;
            }
            if (myMineField[theDownSquare][theColumn] == '*') {
                mineExists = true;
                countMines++;
            }
        } else if (theUpSquare < 0 && theDownSquare < myRow) {
            if (myMineField[theDownSquare][theColumn] == '*') {
                mineExists = true;
                countMines++;
            }
        } else if (theUpSquare >= 0) {
            if (myMineField[theUpSquare][theColumn] == '*') {
                mineExists = true;
                countMines++;
            }
        }
        if (mineExists) {
            myMineField[theRow][theColumn] = (char) (myMineField[theRow][theColumn] + countMines);
        }
    }

    private static void findUpLeftSquare(final int theRow,
                                         final int theColumn,
                                         final int theShiftedRow,
                                         final int theShiftedColumn) {
        boolean mineExists = false;
        if (theShiftedRow >= 0 && theShiftedColumn >= 0) {
            if (myMineField[theShiftedRow][theShiftedColumn] == '*') {
                mineExists = true;
            }
        }
        if (mineExists) {
            myMineField[theRow][theColumn] = (char) (myMineField[theRow][theColumn] + 1);
        }
    }

    private static void findDownLeftSquare(final int theRow,
                                           final int theColumn,
                                           final int theShiftedRow,
                                           final int theShiftedColumn) {
        boolean mineExists = false;
        if (theShiftedRow < myRow && theShiftedColumn >= 0) {
            if (myMineField[theShiftedRow][theShiftedColumn] == '*') {
                mineExists = true;
            }
        }
        if (mineExists) {
            myMineField[theRow][theColumn] = (char) (myMineField[theRow][theColumn] + 1);
        }
    }

    private static void findUpRightSquare(final int theRow,
                                          final int theColumn,
                                          final int theShiftedRow,
                                          final int theShiftedColumn) {
        boolean mineExists = false;
        if (theShiftedRow >= 0 && theShiftedColumn < myColumn) {
            if (myMineField[theShiftedRow][theShiftedColumn] == '*') {
                mineExists = true;
            }
        }
        if (mineExists) {
            myMineField[theRow][theColumn] = (char) (myMineField[theRow][theColumn] + 1);
        }
    }


    private static void findDownRightSquare(final int theRow,
                                            final int theColumn,
                                            final int theShiftedRow,
                                            final int theShiftedColumn) {
        if (theShiftedRow < myRow && theShiftedColumn < myColumn) {
            if (myMineField[theShiftedRow][theShiftedColumn] == '*') {
                myMineField[theRow][theColumn]
                        = (char) (myMineField[theRow][theColumn] + 1);
            }
        }
    }
}

