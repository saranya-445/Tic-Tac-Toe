package com.McMaster.SE2OP32025;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * File: UpdatedBoard.java
 * Author: Lakshmi Saranya Alamanda
 * Description: Represents an updated Tic Tac Toe board of size N x N
 * with M in a row required to win. Extends the original Board class.
 */
public class UpdatedBoard extends Board {
    private int N;  // board size (N x N)
    private int M;  // number in a row required to win
    private String[][] grid;
    private Random random = new Random();

    /**
     * Constructor to initialize N x N board with M in a row to win
     * 
     * @param N
     * @param M
     */
    public UpdatedBoard(int N, int M) {
        this.N = N;
        this.M = M;
        this.grid = new String[N][N];
        reset();
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Get the size of the board (N for N x N Tic Tac Toe)
     * @return
     */
    @Override
    public int getSize(){
        return N;
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Clears the Board, fills with empty string
     */
    @Override
    public void reset(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = " ";
            }
        }
    } 

    /**
     * Overrides / replaces 3x3 board behaviour
     * Displays the current board state to the console
     */
    @Override
    public void displayBoard() {
        System.out.print("   ");
        for (int c = 1; c <= N; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();

        // print each row
        for (int r = 0; r < N; r++) {
            System.out.printf("%2d ", (r+1));

            // print row contents
            for (int c = 0; c < N; c++) {
                System.out.print(grid[r][c]);
                
                if (c < N-1)
                    System.out.print("|  ");
            }
            System.out.println();

            if (r < N-1) {
                System.out.print(" ");

                // print separator line
                for (int k = 0; k < N*2-1; k++)
                    System.out.print("--");
                System.out.println();
            }
        }
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Places a symbol at given location from user.
     * Returns true if able to place, false otherwise
     * @param row
     * @param col
     * @param symbol
     * @return
     */
    @Override
    public boolean placeSymbol(int row, int col, String symbol) {
        if (!isInBounds(row, col))
            return false;

        if (!grid[row][col].equals(" "))
            return false;

        grid[row][col] = symbol;
        return true;
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Get the content of a cell at (row, col)
     * @param row
     * @param col
     * @return
     */
    @Override
    public String getCell(int row, int col) {
        if (!isInBounds(row, col))
            return null;
        return grid[row][col];
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Checks if the board is full (no empty cells) 
     * @param row
     * @param col
     * @return
     */
    @Override
    public boolean isFull() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c].equals(" "))
                    return false;
            }
        }
        return true;
    }

    /**
     * Helper method to check if (r,c) is within board bounds
     * 
     * @param r
     * @param c
     * @return
     */
    private boolean isInBounds(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Returns a list of available moves as int[]{row,col}
     * Used by the computer player for the method: getRandomEmptyCell.
     * @return
     */
    @Override
    public List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int r = 0; r < getSize(); r++) {
            for (int c = 0; c < getSize(); c++) {
                if (grid[r][c].equals(" "))
                    moves.add(new int[]{r,c});
            }
        }
        return moves;
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Returns a random empty cell and {row, col}, or null if no empty cells
     * Used for a random move from the computer player
     * @return
     */
    @Override
    public int[] getRandomEmptyCell() {
        List<int[]> empty = getAvailableMoves();

        if (empty.isEmpty())
            return null;
        return empty.get(random.nextInt(empty.size()));
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Checks if the given symbol has M in a row (horiz, vert, diag
     * @param symbol
     * @return
     */
    @Override
    public boolean checkWin(String symbol) {
        int[][] directionVector = { {0, 1}, {1, 0}, {1, 1}, {1, -1} };

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                
                // skip cells that do not match the player symbol
                if (!grid[r][c].equals(symbol))
                    continue;

                // check all 4 directions from this cell
                for (int[] direction : directionVector) {

                    // how row and column changes each step
                    int rowStep = direction[0];
                    int colStep = direction[1];

                    int count = 1;

                    for (int step = 1; step < M; step++) {
                        int currRow = r + step*rowStep;
                        int currCol = c + step*colStep;

                        // check bounds before accessing grid[r][c]
                        if (!isInBounds(currRow, currCol)) {
                            // walked off the board — can't get M in a row in this direction
                            break;
                        }

                        if (grid[currRow][currCol].equals(symbol)) {
                            count++;
                            if (count >= M) {
                                return true; // found M in a row
                            }
                        } else {
                            break; // sequence broken
                        }
                        
                    }
                }
            }
        }

        return false;
    }
}
