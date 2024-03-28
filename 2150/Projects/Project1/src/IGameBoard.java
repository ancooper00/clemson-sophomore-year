

/**
 * Abstractly, IGameBoard is the game board on which the game can be played.
 * This game board is bounded by a size of BOARD_SIZE x BOARD_SIZE and to start the game is
 * filled with blank spaces in which moves can be played.
 *
 * @Initialization ensures:
 *      Board is empty and is of size BOARD_SIZE x BOARD_SIZE
 *
 * @Defines: BOARD_SIZE : Z+
 *           NUM_ROWS : Z+
 *           NUM_COLUMNS : Z+
 *           START_X : Z
 *           START_Y : Z
 *           NUM_TO_WIN : Z+
 *
 * @Constraints: 0 < BOARD_SIZE
 *               0 < NUM_ROWS
 *               0 < NUM_COLUMNS
 *               START_X = 0
 *               START_Y = 0
 *               0 < NUM_TO_WIN
 */
public interface IGameBoard {

    static final int BOARD_SIZE = 8;
    static final int NUM_ROWS = 8;
    static final int NUM_COLUMNS = 8;
    static final int START_X = 0;
    static final int START_Y = 0;
    static final int NUM_TO_WIN = 5;


    /**
     * checkSpace checks to see if a position s is available on the game board
     * @param pos is the position to be checked
     * @return the truth value describing a position's availability
     *
     * @pre NONE
     * @post checkSpace = true iff  0 <= pos.Row < BOARD_SIZE AND 0 <= pos.Column < BOARD_SIZE AND board[pos.Column][pos.Row] = " "
     * @post board[pos.Column][pos.Row] = #board[pos.Column][pos.Row]
     */
    default boolean checkSpace(BoardPosition pos){
        //checks if pos is in bounds
        if(pos.getColumn() >= BOARD_SIZE || pos.getColumn() < 0) return false;
        if(pos.getRow() >= BOARD_SIZE || pos.getRow() < 0) return false;

        //checks if pos is empty
        if(isPlayerAtPos(pos, ' ')) return true;
        else return false;
    }


    /**
     * placeMarker places the player token (X or O) on the board at the position specified by marker
     * @param marker contains the location on the board where player wishes to place token
     * @param player is the token (X or O) to be placed on the board
     *
     * @pre checkSpace(marker) = true AND (player = X  or player = O)
     * @post board[marker.getColumn()][marker.getRow()] = player
     */
    void placeMarker(BoardPosition marker, char player);

    /**
     * checkForWinner will check to see if the lastPos placed resulted in a winner.
     *      If so it will return true, otherwise false.
     * @param lastPos the board location where the last play was made
     * @return the truth value describing whether a game has been won
     *
     * @pre lastPos is filled on the game board
     * @post checkForWinner = true if checkHorizontalWin(BoardPosition lastPos, char player) = true OR
     *                        checkVerticalWin(BoardPosition lastPos, char player) = true OR
     *                        checkDiagonalWin(BoardPosition lastPos, char player) = true
     */
    default boolean checkForWinner(BoardPosition lastPos){
        if(checkHorizontalWin(lastPos, whatsAtPos(lastPos))) return true;
        else if (checkVerticalWin(lastPos, whatsAtPos(lastPos))) return true;
        else if (checkDiagonalWin(lastPos, whatsAtPos(lastPos))) return true;
        else return false;
    }

    /**
     * checkForDraw will check to see if the game has resulted in a tie.
     * @return the truth value describing whether a game has been tied
     *
     * @pre checkForWinner = false
     * @post checkForDraw = true if for all 0 <= i < BOARD_SIZE and all 0 <= j < BOARD_SIZE, board[i][j] != " "
     */
    boolean checkForDraw();

    /**
     * checkHorizontalWin checks to see if the last marker placed resulted in 5 in a row horizontally
     * @param lastPos contains the location of the position of the last marker
     * @param player the token (X or O) just played
     * @return the truth value describing whether a game has been won horizontally
     *
     * @pre 0 <= lastPos.Row < BOARD_SIZE and 0 <= lastPos.Column < BOARD_SIZE AND (player = X  or player = O)
     * @post checkHorizontalWin = true iff counter = NUM_TO_WIN
     */
    boolean checkHorizontalWin(BoardPosition lastPos, char player);

    /**
     * checkVerticalWin checks to see if the last marker placed resulted in 5 in a row vertically
     * @param lastPos contains the location of the position of the last marker
     * @param player the token (X or O) just played
     * @return the truth value describing whether a game has been won vertically
     *
     * @pre 0 <= lastPos.Row < BOARD_SIZE and 0 <= lastPos.Column < BOARD_SIZE AND (player = X  or player = O)
     * @post checkVerticalWin = true iff counter = NUM_TO_WIN
     */
    boolean checkVerticalWin(BoardPosition lastPos, char player);

    /**
     * checkDiagonalWin checks to see if the last marker placed resulted in 5 in a row diagonally
     * @param lastPos contains the location of the position of the last marker
     * @param player the token (X or O) just played
     * @return the truth value describing whether a game has been won diagonally
     *
     * @pre 0 <= lastPos.Row < BOARD_SIZE and 0 <= lastPos.Column < BOARD_SIZE AND (player = X  or player = O)
     * @post checkDiagonallyWin = true iff counter = NUM_TO_WIN
     */
    boolean checkDiagonalWin(BoardPosition lastPos, char player);

    /**
     * whatsAtPos returns what is in the GameBoard at position pos
     * @param pos is the game board position to be checked
     * @return the character located at BoardPosition pos
     *
     * @pre  0 <= pos.Row < BOARD_SIZE AND 0 <= pos.Column < BOARD_SIZE
     *       [pos is located on the game board]
     * @post whatsAtPos = "X" if board[pos.Row][pos.Column] = "X" OR
     *       whatsAtPos = "O" if board[pos.Row][pos.Column] = "O" OR
     *       whatsAtPos = " " if board[pos.Row][pos.Column] = " "
     */
    char whatsAtPos(BoardPosition pos);

    /**
     * isPlayerAtPos returns true if the player is at pos, otherwise it returns false
     * @param pos is the board position to be checked for the player
     * @param player is the player being looked for
     * @return the truth value associated with whether or not the player at at pos
     *
     * @pre (player = "X" OR player = "0") AND
     *      (0 <= pos.Row < BOARD_SIZE AND 0 <= pos.Column < BOARD_SIZE)
     *      [pos is located on the game board]
     * @post isPlayerAtPos = true iff board[pos.Row][pos.Column] = player
     */
    boolean isPlayerAtPos(BoardPosition pos, char player);

    /**
     * An overridden version of toString(). Returns one string that shows the entire game board
     * @return string showing entire game board
     *
     * @pre board = board[BOARD_SIZE][BOARD_SIZE]
     * @post toString = boardOutput
     */
    @Override
    String toString();

    /**
     * @return returns the number of rows of the game board
     *
     * @pre NUM_ROWS has been initialized
     * @post getNumRows = NUM_TO_WIN
     * @post NUM_ROWS = #NUM_ROWS
     */
    default int getNumRows(){
        return NUM_ROWS;
    }


    /**
     * @return returns the number of columns of the game board
     *
     * @pre NUM_COLUMNS has been initialized
     * @post getNumColumns = NUM_COLUMNS
     * @post NUM_COLUMNS = #NUM_COLUMNS
     */
    default int getNumColumns(){
        return NUM_COLUMNS;
    }

    /**
     * @return returns the number of pieces needs to win
     *
     * @pre NUM_TO_WIN has been initialized
     * @post getNumToWin = NUM_TO_WIN
     * @post NUM_TO_WIN = #NUM_TO_WIN
     */
    default int getNumToWin(){
        return NUM_TO_WIN;
    }



}
