import java.util.*;

/**
 * Class that controls the flow of the game.
 */
public class GameScreen {

    private static char currentPlayer;
    private static boolean gameOver = false;
    private static boolean playAgain;
    private static GameBoard board;


    /**
     * The main method controls the flow and actual play of the game.
     *
     * @pre NONE
     * @post A game of tic tac toe has been played
     */
    public static void main(String[] args){
        playAgain = true;

        while (playAgain == true) {
            gameOver = false;
            currentPlayer = 'X';
            board = new GameBoard();

            while (gameOver != true) {
                GameScreen.takeTurn(currentPlayer);
            }
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
        System.out.println(board.toString());

        System.out.println("CONGRATULATIONS PLAYER " + currentPlayer + "!");
        System.out.println("YOU WON!");
        System.out.println();

        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again? Enter Y or N");
        char choice = input.next().charAt(0);

        if(choice == 'Y') playAgain = true;
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
        System.out.println(board.toString());

        System.out.println("TIE");
        System.out.println("The game has ended in a draw.");
        System.out.println();

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

        board.placeMarker(input, player);

        if(board.checkForWinner(input)){
            gameWon();
            gameOver = true;
        }
        else if(board.checkForDraw()){
            gameDrawn();
            gameOver = true;
        }

        if(player == 'X') currentPlayer = 'O';
        else currentPlayer = 'X';


    }


}
