package com.McMaster.SE2OP32025;

/**
 * File: Player.java
 * Author: Lakshmi Saranya Alamanda
 * Description: Abstract class representing a player in the Tic Tac Toe game.
 * Defines common properties and methods for human and computer players.
 */
public abstract class Player {
    protected String symbol;

    /**
     * Constructor to initialize player with a symbol (X or O)
     * 
     * @param symbol player's symbol
     */
    public Player(String symbol) {
        this.symbol = symbol.toUpperCase();
    }

    /**
     * Get the player's symbol
     * 
     * @return player's symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Ask player for their next move, given the current board
     * returns an int[]{row, col} (0-based) or nullif not move possible
     */
    public abstract int[] getMove(Board board);

}
