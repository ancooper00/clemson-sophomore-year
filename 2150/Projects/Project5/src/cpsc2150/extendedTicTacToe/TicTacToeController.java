package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    public static final int MAX_PLAYERS = 10;
    private Character[] players = new Character[]{'X', 'O', 'Y', 'A', 'B', 'C', 'E', 'K', 'M', 'S'};
    private int numTurns = 0;
    private int numPlayers;
    private boolean playingGame = true;
    private char curPlayer;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        curGame = model;
        screen = view;

        // Some code is needed here.
        numPlayers = np;
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        //while in the state of playing the game
        if(playingGame){
            //get the current player
            curPlayer = players[numTurns % numPlayers];

            //check space entered by player
            boolean valid;
            BoardPosition newSpace = new BoardPosition(col, row);
            if(curGame.checkSpace(newSpace)) valid = true;
            else valid = false;
            //display message if space not valid
            if(!valid){
                screen.setMessage("Space not available. Please pick a different space");
                return;
            }
            //if valid set space and update turns
            else{
                curGame.placeMarker(newSpace, curPlayer);
                screen.setMarker(row, col, curPlayer);
                numTurns++;
            }

            //check if win
            if(curGame.checkForWinner(newSpace)){
                screen.setMessage("Congratulations! " + curPlayer + " won! Click any button to start a new game");
                playingGame = false; //end the game
            }
            //check if draw
            else if (curGame.checkForDraw()){
                screen.setMessage("The game has ended in a draw. Click any button to start a new game");
                playingGame = false; //end the game
            }
            //move to next turn
            else{
                curPlayer = players[numTurns % numPlayers]; //update current player
                screen.setMessage("It is " + curPlayer + "'s turn.");
            }
        }
        //to restart game
        else{
            numTurns = 0; //reset number of moves for next game
            curPlayer = players[0]; //set to first player
            playingGame = true; //restart the game
            newGame(); //call to restart gameplay
        }

    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}