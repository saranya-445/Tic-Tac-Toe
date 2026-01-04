package com.McMaster.SE2OP32025;
import java.util.Scanner;

/**
 * File: Game.java
 * Author: Lakshmi Saranya Alamanda
 * Descriptioin: Entry point and controller for a simple Tic Tac Toe game.
 *
 * This class is responsible for driving the game loop: creating the
 * board, prompting the user for play mode and symbols, creating players,
 * accepting moves from the current player, checking for win/draw
 * conditions, and announcing the final result.
 */
public class Game {
    protected final Scanner scanner = new Scanner(System.in);
    protected Board board;
    protected Player player1;
    protected Player player2;
    protected Player current;

    public static void main(String[] args) {
        new Game().playGame();
    }
    
    /**
     * Primary game loop.
     *
     * The method performs the following high-level steps:
     * - create the board
     * - prompt for play mode (human/computer)
     * - prompt player 1 for symbol when relevant
     * - instantiate players
     * - run the turn-based loop until a win or draw occurs
     */
    public void playGame() {

        boolean threeInRow = false; // flag set when a player wins
        String playerSym = "";     // symbol for player1 (X or O)
        String otherSym = "";      // symbol for player2
        
        Board board = createBoard();

        System.out.println("Welcome to the Tic Tac Toe Game.");
        int mode = chooseMode();

        // if there is at least one human player, ask which symbol player1 wants
        if (mode == 1 || mode == 2) {
            playerSym = chooseSym(mode);

            if (playerSym.equals("X")) {
                otherSym = "O";
            }
            else
                otherSym = "X";

            System.out.println("Player 2 will be " + otherSym);
        }

        storePlayers(mode, playerSym, otherSym);        

        current = player1;

        while (!threeInRow) { 
            // display board
            board.displayBoard();

            // obtain the next move from the current player
            int[] move = current.getMove(board);
            if (move == null) {
                System.out.println("No valid moves available. It is a draw!");
                break;
            }

            int row = move[0];
            int col = move[1];

            // attempt to place the player's symbol at the chosen location
            boolean placed = board.placeSymbol(row, col, current.getSymbol());
            if (!placed) {
                System.out.println("Could not place symbol there. Try again.");
                continue;
            }

            // if the current player has achieved the win condition, announce and finish
            if (board.checkWin(current.getSymbol())) {
                board.displayBoard();
                System.out.println("Player " + current.getSymbol() + " wins!\nCONGRATS!");
                threeInRow = true;
                break;
            }

            // if the board is full without a winner, it's a draw
            if (board.isFull()) {
                board.displayBoard();
                System.out.println("It's a draw! You both win");
                break;
            }

            // switch turns
            if (current == player1)
                current = player2;
            else
                current = player1;
        }
        System.out.println("GAME OVER!");
        scanner.close();
    }

    /**
     * Factory method for creating a new {@link Board} instance.
     *
     * Protected so subclasses can override to provide different board
     * implementations for testing or variations of the game.
     *
     * @return a fresh Board instance
     */
    protected Board createBoard() {
        return new Board();
    }

    /**
     * Ask the user which play mode to use.
     *
     * @return integer representing the selected mode
     */
    protected int chooseMode() {
        System.out.println("What mode would you like to play the game? Please enter the number you wish.\n1) Human vs Human\n2) Human vs Computer\n3) Computer vs Computer");
        int mode = scanner.nextInt();
        scanner.nextLine();
        return mode;
    }

    /**
     * Prompt player 1 to choose a symbol (X or O). Keeps asking until valid input.
     *
     * @param mode current game mode (unused here but kept for potential overrides)
     * @return the chosen symbol as an uppercase String: "X" or "O"
     */
    protected String chooseSym(int mode) {
        System.out.println("What symbol would you (Player 1) like to play as X or O.");
        String playerSym = scanner.nextLine().toUpperCase();

        while(!playerSym.equals("X") && !playerSym.equals("O")) {
            System.out.println("Invalid input. Please enter X or O");
            playerSym = scanner.nextLine().toUpperCase();
        }

        return playerSym;
    }

    /**
     * Instantiate and assign objects based on selected mode.
     *
     * Modes:
     * - 1: Human vs Human
     * - 2: Human vs Computer
     * - 3: Computer vs Computer
     *
     * @param mode selected play mode
     * @param playerSym symbol for player1
     * @param otherSym symbol for player2
     */
    protected void storePlayers(int mode, String playerSym, String otherSym) {
        switch (mode) {
            case 1:
                player1 = new HumanPlayer(playerSym);
                player2 = new HumanPlayer(otherSym);
                break;

            case 2:
                player1 = new HumanPlayer(playerSym);
                player2 = new ComputerPlayer(otherSym);
                break;
            case 3:
                // default symbols for computer vs computer
                player1 = new ComputerPlayer("X");
                player2 = new ComputerPlayer("O");
                break;

            default:
                System.out.println("Unknown Option - Default option is Human vs Computer");
                player1 = new HumanPlayer(playerSym);
                player2 = new HumanPlayer(otherSym);
                break;

        }
    }

}
