package com.McMaster.SE2OP32025;
import java.util.Scanner;

/**
 * File: HumanPlayer.java
 * Author: Lakshmi Saranya Alamanda
 * Description: Represents a human player in the Tic Tac Toe game.
 * Extends the abstract Player class and implements the method to get moves from user input.
 * Handles input validation to ensure valid moves are made.
 */
public class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer (String symbol) {
        super(symbol);
    }

    /**
     * Ask human player for their next move, given the current board
     * returns an int[]{row, col} (0-based) or null if no move possible.
     */
    @Override
    public int[] getMove(Board board) {
        int boardSize = board.getSize();

        // loop until valid move is entered
        while (true) { 
            try {
                System.out.println("Player " + symbol + " enter row and column ( in this format: (rows,column) )");
                String move = scanner.nextLine().trim();

                // remove parentheses if present
                move = move.replace("(", "").replace(")","");

                String[] moveArr = move.split(",");

                // parse row and column, converting to 0-based index
                int row = Integer.parseInt(moveArr[0].trim()) - 1;
                int col = Integer.parseInt(moveArr[1].trim()) - 1;

                // check for valid bounds
                if (row < 0 || col < 0 || row > boardSize || col > boardSize) {
                    System.out.println("Invalid Coordinates. Row and Column must be between 1 and " + boardSize);
                    continue;
                }

                // check if cell entered is empty
                String cell = board.getCell(row, col);
                if (cell == null) {
                    System.out.println("Invalid cell. Try again");
                    continue;
                }
                
                // checks if cell entered is empty
                if (!cell.equals(" ")) {
                    System.out.println("This cell is already taken. Choose another");
                    continue;
                }
                
                return new int[]{row, col};

                // catch invalid input format
            } catch (Exception e) {
                System.out.println("Invalid Input. Numbers must be between 1 and " + boardSize);
                scanner.nextLine();
            }

        }
    }
}
