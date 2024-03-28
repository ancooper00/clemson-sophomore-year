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

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;

        // Some code is needed here.
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        // Your code goes here.
    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}