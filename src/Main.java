import factory.Board;
import fais.zti.oramus.gomoku.*;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws TheWinnerIsException, ResignException, WrongBoardStateException {
//        String board = ". . o o o . . . . .\n" +
//                       ". o x x x o o o . .\n" +
//                       ". x . . . . o . . .\n" +
//                       ". . x . . . x . . .\n" +
//                       ". . . x . x . . . .\n" +
//                       ". . . o . . . . . .\n" +
//                       ". . . . x . . . . .\n" +
//                       ". . . . . . . . . .\n" +
//                       ". . . . . . . . . .\n" +
//                       ". . . . . . . . . .";

/*
String board =  ". . . . . . . x . .\n" +
                ". . . . . . . . . .\n" +
                ". . o o x o o . . .\n" +
                ". . . x . x o . . o\n" +
                ". . x x o . o . x .\n" +
                ". . . x . . . x o .\n" +
                "x . x o . . . . x .\n" +
                ". . . . . . . . . o\n"+
                ". . . . . . . . . .\n" +
                ". . . . . . . . . .\n";

        String board1 =". . . . . . . . . .\n" +
                       ". . . . . . . . . .\n" +
                       ". . . . . . . . . .\n" +
                       ". . . . . o . o . .\n" +//3
                       ". . . . . o . . . .\n" +
                       ". . . . o . . . . .\n" +//5
                       ". . x . x . . . . .\n" +//6
                       ". . . . . . . . . .\n" +//7
                       ". . x . x . . . . .\n" +
                       ". . . . . . . . . .";

        String board2 = ". x . . . o . . . .\n" +
                        ". x . . o x . . . o\n" +
                        ". x . o . . o x o x\n" +
                        ". . . . . . . o . .\n" +
                        ". . . . o x . . o .\n" +
                        ". . . . . x . . . o\n" +
                        ". . . . . . x x . .\n" +//5
                        ". . . . . . . x . .\n" +
                        ". . . . . . . . o .\n" +
                        ". . . . . . . . . .";

        String board3 = ". . . . x . . . . .\n" +
                        ". . . . x . . . . .\n" +
                        ". . x x . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . o o .\n" +//5
                        ". . . . . . . o o .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .";
*/         //            0 1 2 3 4 5 6 7 8 9
        String board4 = ". . . . . . . . . .\n" +
                        ". o o . . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". x x x x . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .\n" +
                        ". . . . . . . . . .";
        Set<Move> boardState = parseBoard(board4);
        Gomoku gomoku = new Gomoku();
        gomoku.size(10);
        gomoku.firstMark(Mark.CROSS);
        try {
            System.out.println("1. X/X "+gomoku.nextMove(boardState, Mark.CROSS));
        } catch (TheWinnerIsException e) {
            System.out.println("Gracz " + e.mark + " wygrał grę!");
        } catch (ResignException e) {            System.out.println("Gracz poddał grę.");
        }catch (WrongBoardStateException e) {            System.out.println("1. X/X Stan planszy niezgodny z zasadami");
        }
        try {
            System.out.println("2. X/O "+gomoku.nextMove(boardState, Mark.NOUGHT));
        } catch (TheWinnerIsException e) {
            System.out.println("Gracz " + e.mark + " wygrał grę!");
        } catch (ResignException e) {            System.out.println("Gracz poddał grę.");
        }catch (WrongBoardStateException e) {            System.out.println("2. X/O Stan planszy niezgodny z zasadami");
        }
        gomoku.firstMark(Mark.NOUGHT);
        try {
            System.out.println("3. O/X "+gomoku.nextMove(boardState, Mark.CROSS));
        } catch (TheWinnerIsException e) {
            System.out.println("Gracz " + e.mark + " wygrał grę!");
        } catch (ResignException e) {            System.out.println("Gracz poddał grę.");
        }catch (WrongBoardStateException e) {            System.out.println("3. O/X Stan planszy niezgodny z zasadami");
        }
        try {
            System.out.println("4. O/O "+gomoku.nextMove(boardState, Mark.NOUGHT));
        } catch (TheWinnerIsException e) {
            System.out.println("Gracz " + e.mark + " wygrał grę!");
        } catch (ResignException e) {            System.out.println("Gracz poddał grę.");
        }catch (WrongBoardStateException e) {            System.out.println("4. O/O Stan planszy niezgodny z zasadami");
        }
    }

    public static Set<Move> parseBoard(String board) {
        Set<Move> boardState = new HashSet<>();
        String[] rows = board.split("\n");

        for (int row = 0; row < rows.length; row++) {
            String[] columns = rows[row].split(" ");

            for (int col = 0; col < columns.length; col++) {
                String cell = columns[col].trim();

                if (!cell.isEmpty()) {
                    Mark mark = Mark.NULL;
                    switch (cell) {
                        case "o":
                            mark = Mark.NOUGHT;
                            break;
                        case "x":
                            mark = Mark.CROSS;
                            break;
                        case ".":
                            mark = Mark.NULL;
                            break;
                    }
                    if (mark == Mark.NULL) continue;
                    boardState.add(new Move(new Position(col, row), mark));
                }
            }
        }

        return boardState;
    }

    public static String rotateBoard90Degrees(String board) {
        String[] rows = board.strip().split("\n");
        int numRows = rows.length;
        int numCols = rows[0].split(" ").length;

        String[][] original = new String[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            original[i] = rows[i].split(" ");
        }

        String[][] rotated = new String[numCols][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                rotated[j][numRows - 1 - i] = original[i][j];
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                result.append(rotated[i][j]);
                if (j < numRows - 1) {
                    result.append(" ");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

    public static void printBoard(Board board) {
        int size = board.getSize();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Position pos = new Position(x, y);
                Mark mark = board.getMarkAt(pos);
                System.out.print(mark + " ");
            }
            System.out.println();
        }
    }

}
