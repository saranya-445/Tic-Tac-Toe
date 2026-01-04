package com.McMaster.SE2OP32025;
import java.util.List;
import java.util.Random;

/**
 * File: ComputerPlayer.java
 * Author: Lakshmi Saranya Alamanda
 * Description: Represents a computer player in the Tic Tac Toe game.
 * Extends the abstract Player class and implements the method to get moves using a simple random strategy.
 * The computer selects a random empty cell on the board for its move.
 */
public class ComputerPlayer extends Player {
    private final Random random = new Random();

    /**
     * Constructor to initialize computer player with a symbol (X or O)
     * @param symbol
     */
    public ComputerPlayer(String symbol) {
        super(symbol);
    }

    /**
     * Ask computer player for their next move, given the current board
     * returns an int[]{row, col} (0-based) or null if no move possible.
     */
    @Override
    public int[] getMove(Board board) {
        System.out.println("It's the computers turn");
        int[] move = board.getRandomEmptyCell();
        if (move != null){
            int N = board.getSize();
            if (move[0] >= 0 && move[0] < N && move[1] >= 0 && move[1] < N){
                System.out.println("The computer chose (" + (move[0] + 1) + "," + (move[1] + 1)+ ")");
                return move;
            }
        }

        // fallback: get list of available moves and pick one at random
        List<int[]> available = board.getAvailableMoves();
        if (available.isEmpty())
            return null;

        return available.get(random.nextInt(available.size()));
    }
    
}
