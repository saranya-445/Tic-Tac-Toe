package com.McMaster.SE2OP32025;

/**
 * File: UpdatedGame.java
 * Author: Lakshmi Saranya Alamanda
 * Description: Represents the updated Tic Tac Toe game allowing variable board size and win length.
 * Extends the original Game class to override
 * the board creation with user-defined N and M values.
 */
public class UpdatedGame extends Game {
    public static void main(String[] args) {
        new UpdatedGame().playGame();
    }

    /**
     * Overrides / replaces 3x3 board behaviour
     * Creates an UpdatedBoard with user-defined N and M values
     * @return
     */
    @Override
    protected Board createBoard() {
        int N = 3;
        int M = 3;

        // validate N
        while (true) { 
            System.out.println("Enter your desired board size N (number must be between 3 and 20): ");
            try {
                N = scanner.nextInt();
                scanner.nextLine();

                if (N >= 3 && N <= 20) 
                    break;
                else
                    System.out.println("Value of N should be between 3 and 20");
            
            } catch (NumberFormatException e) {
                System.out.println("Invalid N. Please enter an integer between 3 and 20");
            }
        }

        // validate M
        while (true) { 
            System.out.println("Enter win length M (number must be between 3 and "+ N +"): ");
            try {
                M = scanner.nextInt();
                scanner.nextLine();

                if (M >= 3 && M <= N) 
                    break; 
                else
                    System.out.println("Value of M should be between 3 and " + N);
            
            } catch (NumberFormatException e) {
                System.out.println("Invalid M. Please enter an integer between 3 and " + N);
            }
        }

        return new UpdatedBoard(N,M);
    }
}
