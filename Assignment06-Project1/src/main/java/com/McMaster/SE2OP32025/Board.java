package com.McMaster.SE2OP32025;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * File: Board.java
 * Author: Lakshmi Saranya Alamanda 
 * Description: Represents a standard 3x3 Tic Tac Toe board.
 * Provides methods to place symbols, check for wins/draws and display the board.
 */
public class Board {
    private final int SIZE = 3;
    private String[][] grid;
    private final Random random = new Random();

    /**
     * Constructor initializes the 3x3 board and resets it
     */
    public Board() {
        grid = new String[SIZE][SIZE];
        reset();
    }

    /**
     * Get the size of the board (3 for standard Tic Tac Toe)
     * 
     * @return
     */
    public int getSize(){
        return SIZE;
    }

    /**
     * Clears the Board, fills with empty string
     */
    public void reset() {
        for (int r = 0; r < SIZE; r++){ 
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = " ";
            }
        }
    }

    /**
     * Displays the current board state to the console
     */
    public void displayBoard() {
        System.out.println("  1 2 3");

        for (int r = 0; r < SIZE; r++){
            System.out.print((r+1) + " ");

            for (int c = 0; c < SIZE; c++) {
                System.out.print(grid[r][c]);
                if (c < SIZE - 1)
                    System.out.print("|");                
            }
            System.out.println();
            if (r < SIZE - 1)
                System.out.println(" -------");
        }
    }

    /**
     * Places a symbol at given location from user.
     * Returns true if able to place, false otherwise
     * 
     * @param row
     * @param col
     * @param symbol
     * @return
     */
    public boolean placeSymbol(int row, int col, String symbol) {
        if (!isInBounds(row, col))
            return false;

        if (!grid[row][col].equals(" "))
            return false;

        grid[row][col] = symbol;
        return true;
    }

    /**
     * Checks if the values entered are out of bounds. 
     * Returns true if values are in the right bounds.
     * 
     * @param row
     * @param col
     * @return
     */
    private boolean isInBounds (int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    /**
     * Checks whether the given symbol has a winning line (3 in a row)
     * @param symbol
     * @return
     */
    public boolean checkWin(String symbol) {
        // check rows
        for (int r = 0; r < SIZE; r++){
            boolean rowWin = true;

            for (int c = 0; c < SIZE; c++) {
                if (!grid[r][c].equals(symbol)) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin)
                return true;
        }

        // check columns
        for (int c = 0; c < SIZE; c++){
            boolean colWin = true;
            for (int r = 0; r < SIZE; r++) {
                if (!grid[r][c].equals(symbol)) {
                    colWin = false;
                    break;
                }
            }
            if (colWin)
                return true;
        }

        // check main diagonal
        boolean diagMain = true;
        for (int i = 0; i < SIZE; i++){
            if (!grid[i][i].equals(symbol)) {
                diagMain = false;
                break;
            }
        }
        if (diagMain)
            return true;

        // check anti diagonal
        boolean diagAnti = true;
        for (int i = 0; i < SIZE; i++){
            if (!grid[i][SIZE-1-i].equals(symbol)) {
                diagAnti = false;
                break;
            }
        }
        return diagAnti;
    }

    /**
     * Returns true if the board has no empty cells
     * 
     * @return
     */
    public boolean isFull() {
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c].equals(" "))
                    return false;
            }
        }
        return true;
    }

    /**
     * Returns a random empty cell and {row, col}, or null if no empty cells
     * Used for a random move from the computer player
     * 
     * @return
     */
    public int[] getRandomEmptyCell() {
        List<int[]> moves = getAvailableMoves();

        if (moves.isEmpty())
            return null;

        return moves.get(random.nextInt(moves.size()));
    }

    /**
     * Returns a list of available moves as int[]{row,col}
     * Used by the computer player for the method: getRandomEmptyCell.
     * 
     * @return
     */
    public List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c].equals(" ")) {
                    moves.add(new int[]{r, c});
                }
            }
        }
        return moves;
    }

    /**
     * Get the symbol at that cell. Useful for testing.
     * @param row
     * @param col
     * @return
     */
    public String getCell(int row, int col) {
        if (!isInBounds(row,col))
            return null;
        return grid[row][col];
    }

}
