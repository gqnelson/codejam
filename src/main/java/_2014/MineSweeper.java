package _2014;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class MineSweeper {
    public static char MINE = '*', PLAYER = 'c', EMPTY = '.';

    static public void doubleClick(int r, int c, int m) {
        boolean done = false;
        char[][] board = new char[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                board[i][j] = EMPTY;
        if (m == r * c - 1) {
            for (char[] x : board) Arrays.fill(x, MINE);
            board[0][0] = PLAYER;
            done = true;
        } else if (r == 1 || c == 1) {
            for (int i = r - 1; i >= 0; i--)
                for (int j = c - 1; j >= 0; j--)
                    if (m-- > 0)
                        board[i][j] = MINE;
            board[0][0] = PLAYER;
            done = true;
        } else {
            for (int filledRows = 0; !done && filledRows <= r - 2; filledRows++) {
                for (int filledCols = 0; !done && filledCols <= c - 2; filledCols++) {
                    int left = m - filledRows * c - filledCols * r + filledRows * filledCols;
                    if (left < 0) continue;
                    int leftRows = r - filledRows, leftCols = c - filledCols;
                    int playable = (leftRows - 2) * (leftCols - 2);
                    if (playable < left) continue;

                    board[0][0] = PLAYER;
                    for (int i = 0; i < r; i++)
                        for (int j = 0; j < c; j++)
                            if (i >= leftRows || j >= leftCols)
                                board[i][j] = MINE;
                    for (int i = leftRows - 1; i >= 2; i--)
                        for (int j = leftCols - 1; j >= 2; j--)
                            if (left-- > 0)
                                board[i][j] = MINE;
                    done = true;
                }
            }
        }
        if (done) for (char[] x : board) System.out.println(new String(x));
        else System.out.println("Impossible");
    }

    static public void oneClick(int r, int c, int m) {
        boolean done = false;
        char[][] board = new char[r][c];

        //Fill the board with empty
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                board[i][j] = MineSweeper.EMPTY;
        if (m == r * c - 1) {
            //There is only one open spot on the board
            //Fill board with mines and set 0,0 to c
            for (char[] x : board) Arrays.fill(x, MineSweeper.MINE);
            board[0][0] = MineSweeper.PLAYER;
            done = true;
        } else if (r == 1 || c == 1) {
            //Either a single row or a single column
            //Fill in the from the back of the row or column with mines
            //Set 0,0 to c
            for (int i = r - 1; i >= 0; i--) //Last row
                for (int j = c - 1; j >= 0; j--) //Last column
                    if (m-- > 0) //Fill with mines until there are no more
                        board[i][j] = MINE;
            board[0][0] = PLAYER;
            done = true;
        } else if (((r * c) - 4) >= m) {
            //There needs to be at least 4 open spots
            board[0][0] = MineSweeper.PLAYER;
            //Fill in rows from the botton
            for (int rowNum = r - 1; m > 0 && rowNum > 1; rowNum--) {
                for (int colNum = c - 1; m > 0 && colNum > -1; colNum--) {
                    board[rowNum][colNum] = MineSweeper.MINE;
                    m--;
                }
            }

            //Fill in columns from the left
            for (int rowNum = 1; m > 0 && rowNum > -1; rowNum--) {
                for (int colNum = c - 1; m > 0 && colNum > 1; colNum--) {
                    board[rowNum][colNum] = MineSweeper.MINE;
                    m--;
                }
            }

            done = true;
        }

        if (done) for (char[] x : board) System.out.println(new String(x));
        else System.out.println("Impossible");
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("minesweeper.input"));
        int T = in.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            int r = in.nextInt();
            int c = in.nextInt();
            int m = in.nextInt();
            System.out.printf("Case #%d:\n", testCase);
            MineSweeper.oneClick(r, c, m);
            System.out.println("");
            MineSweeper.doubleClick(r, c, m);
            r = 5;
        }
    }
}
