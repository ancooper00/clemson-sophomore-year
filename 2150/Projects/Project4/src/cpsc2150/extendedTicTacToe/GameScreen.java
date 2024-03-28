package cpsc2150.extendedTicTacToe;

import java.util.*;

/**
 * Class that controls the flow of the game.
 */
public class GameScreen {

    private static List<Character> players;

    private static int r;
    private static int c;
    private static int numWin;
    private static int numPlayers;
    private static int numTurns;

    private static char implementation;
    private static char currentPlayer;

    private static final int  MAXPLAYERS = 10;
    private static final int  MINPLAYERS = 2;
    private static boolean gameOver = false;
    private static boolean playAgain;
    private static IGameBoard board;


    /**
     * The main method controls the flow and actual play of the game.
     *
     * @pre NONE
     * @post A game of tic tac toe has been played
     */
    public static void main(String[] args){
        playAgain = true;

        //initialize list of players of the game
        players = new ArrayList<Character>();

        //plays game while true
        // at end of game when prompted to play again if N, playAgain is set to false ending the program
        while (playAgain == true) {
            gameOver = false;
            numTurns = 0;

            setBoardFeatures(); //set the features of the game (fast or mem efficient, players, board dimensions)

            //decides fast or memory efficient implementation
            if(implementation == 'F'){ board = new GameBoard(r, c, numWin); }
            else{ board = new GameBoardMem(r, c, numWin); }

            //starts with player 1 character
            currentPlayer = players.get(0);

            //if game has not ended, take a turn
            while (gameOver != true) {
                GameScreen.takeTurn(currentPlayer);
                currentPlayer = players.get(numTurns % numPlayers); //go to next player
            }

            //clear the players list so new players can be added for the next game
            players.clear();
        }
    }

    /**
     * gameWon controls the output of the game if a player has won and asks if the player would like to play again.
     *
     * @pre gameOver = true AND checkForWinner = true
     * @post playAgain = true iff choice = "Y" OR
     *       playAgain = false iff choice = "N"
     */
    public static void gameWon(){
        //print board
        System.out.println(board.toString());

        //print message of congratulations
        System.out.println("CONGRATULATIONS PLAYER " + currentPlayer + "!");
        System.out.println("YOU WON!");
        System.out.println();

        //asks player if they want to player again
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again? Enter Y or N");
        char choice = input.next().charAt(0);

        //if yes playAgain is set to true
        if((choice == 'Y') || (choice == 'y')) playAgain = true;
        else playAgain = false;

    }

    /**
     * gameDrawn controls the output of the game if the game ended in a tie and asks if
     * the players would like to play again.
     *
     * @pre gameOver = true AND checkForDraw = true
     * @post playAgain = true iff choice = "Y" OR
     *       playAgain = false iff choice = "N"
     */
    public static void gameDrawn(){
        //prints board
        System.out.println(board.toString());

        //prints message that game ended in draw
        System.out.println("TIE");
        System.out.println("The game has ended in a draw.");
        System.out.println();

        //asks player if they would like to play again
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again? Enter Y or N");
        char choice = input.next().charAt(0);

        if(choice == 'Y') playAgain = true;
        else playAgain = false;
    }

    /**
     * takeTurn controls the steps necessary for a player to complete their turn
     * @param player is the letter designating a player(X or O) to be added to the board
     *
     * @pre gameOver = false
     * @post player has been added to the board in desired location AND player has flipped characters
     */
    public static void takeTurn(char player){
        System.out.println(board.toString());

        int Row, Column;
        BoardPosition input = null;
        boolean spaceAvailable = false;

        //gets player to choose a valid space
        while (!spaceAvailable){
            Scanner sc = new Scanner(System.in);
            System.out.println("Player " + player + " Please enter your ROW");
            Row = sc.nextInt();
            Scanner inp = new Scanner(System.in);
            System.out.println("Player " + player + " Please enter your COLUMN");
            Column = inp.nextInt();

            input = new BoardPosition(Column, Row);

            if(!board.checkSpace(input)){
                System.out.println("That space is unavailable, please pick again");
                spaceAvailable = false;
            }
            else spaceAvailable = true;
        }

        //if space is valid, places  marker for player
        board.placeMarker(input, player);

        //check for a winner
        if(board.checkForWinner(input)){
            gameWon();
            gameOver = true;
        }
        //check for a draw
        else if(board.checkForDraw()){
            gameDrawn();
            gameOver = true;
        }

        //end turn by incrementing the turn count
        numTurns++;

    }


    /**
     * gets the main features of the game from the user (number of players, player tokens,
     * number of rows, number of columns, number needed to win, and game implementation)
     * @return NONE
     *
     * @pre NONE
     * @post MINPLAYERS <= numPlayers <= MAXPLAYERS AND
     *       each player has unique token AND
     *       MINROWS <= r <= MAXROWS AND
     *       MINCOLS <= c <= MAXCOLS AND
     *       MINWIN <= numWin <= MAXWIN AND numWin <= r AND numWin <= c AND
     *       implementation = 'F' or 'f' or 'M' or 'm'
     *
     */
    public static void setBoardFeatures(){
        boolean invalidInput = true;
        Scanner sc = new Scanner(System.in);

        //ask for valid number of players
        while (invalidInput){
            System.out.println("How many players?");
            numPlayers = sc.nextInt();
            if(numPlayers > MAXPLAYERS) System.out.println("Must be 10 players or fewer");
            else if (numPlayers < MINPLAYERS) System.out.println("Must be at least 2 players");
            else invalidInput = false;
        }

        //get valid player tokens
        for(int i = 1; i <= numPlayers; i++){
            char token;
            boolean tokenTaken = true;
            while(tokenTaken) {
                Scanner tok = new Scanner(System.in);
                System.out.println("Enter the character to represent player " + i);
                token = tok.next().charAt(0);
                if (players.contains(token)) System.out.println(token + " is already taken as a player token!");
                else{
                    players.add(token);
                    tokenTaken = false;
                }
            }
        }

        //ask for valid number of rows
        invalidInput = true;
        while (invalidInput){
            Scanner row = new Scanner(System.in);
            System.out.println("How many rows?");
            r = row.nextInt();
            if(r >= board.MINROWS && r <= board.MAXROWS) invalidInput = false;
            else System.out.println("Rows must be between " + board.MINROWS + " and " + board.MAXROWS);
        }
        //ask for valid number of columns
        invalidInput = true;
        while (invalidInput){
            Scanner cols = new Scanner(System.in);
            System.out.println("How many columns?");
            c = cols.nextInt();
            if(c >= board.MINCOLS && c <= board.MAXCOLS) invalidInput = false;
            else System.out.println("Columns must be between " + board.MINCOLS + " and " + board.MAXCOLS);
        }

        //ask for valid number in a row to win
        invalidInput = true;
        while (invalidInput){
            Scanner num = new Scanner(System.in);
            System.out.println("How many in a row to win?");
            numWin = num.nextInt();
            if(numWin >= board.MINWIN && numWin <= board.MAXWIN && numWin <= r && numWin <= c) invalidInput = false;
            else System.out.println("Number in a row must be at least " + board.MINWIN + ", less than " + board.MAXWIN + ", and less than the  length of the rows and columns");
        }

        //ask for valid game implementation
        invalidInput = true;
        while (invalidInput){
            Scanner imple = new Scanner(System.in);
            System.out.println("Would you like a Fast Game (F/f) or a Memory Effiecient Game (M/m)");
            implementation = imple.next().charAt(0);
            implementation = Character.toUpperCase(implementation);
            if(implementation == 'F' || implementation == 'M') invalidInput = false;
            else System.out.println("Please enter F or M");
        }
    }



}
