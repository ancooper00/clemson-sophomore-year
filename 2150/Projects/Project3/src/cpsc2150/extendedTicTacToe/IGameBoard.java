package cpsc2150.extendedTicTacToe;

/**
 * Abstractly, IGameBoard is the game board on which the game can be played.
 * This game board is bounded by a size of NUM_ROWS x NUM_COLUMNS and to start the game is
 * filled with blank spaces in which moves can be played.
 *
 * @Initialization ensures:
 *          Board is empty
 *
 * @Defines: NUM_ROWS : Z+
 *           NUM_COLUMNS : Z+
 *           START_X : Z
 *           START_Y : Z
 *           NUM_TO_WIN : Z+
 *           MINROWS = Z+;
 *           MINCOLS = Z+;
 *           MINWIN = Z+;
 *
 * @Constraints: 3 < NUM_ROWS <= 100
 *               3 < NUM_COLUMNS <= 100
 *               START_X = 0
 *               START_Y = 0
 *               3 < NUM_TO_WIN <= 25
 */
public interface IGameBoard {

    public static final int START_X = 0;
    public static final int START_Y = 0;

    public static final int MINROWS = 3;
    public static final int MINCOLS = 3;
    public static final int MINWIN = 3;

    public static final int MAXROWS = 100;
    public static final int MAXCOLS = 100;
    public static final int MAXWIN = 25;


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
        if(pos.getColumn() >= getNumColumns() || pos.getColumn() < 0) return false;
        if(pos.getRow() >= getNumRows() || pos.getRow() < 0) return false;

        //checks if pos is empty
        if(whatsAtPos(pos) == ' ') return true;
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
    default boolean checkForDraw(){
        //check if all spaces in board are filled, space by space
        for(int i = START_X; i < getNumRows(); i++){
            for (int j = START_Y; j < getNumColumns(); j++){
                BoardPosition current = new BoardPosition(i, j);
                if(whatsAtPos(current) == ' ') return false;
            }
        }
        return true;
    }

    /**
     * checkHorizontalWin checks to see if the last marker placed resulted in 5 in a row horizontally
     * @param lastPos contains the location of the position of the last marker
     * @param player the token (X or O) just played
     * @return the truth value describing whether a game has been won horizontally
     *
     * @pre 0 <= lastPos.Row < BOARD_SIZE and 0 <= lastPos.Column < BOARD_SIZE AND (player = X  or player = O)
     * @post checkHorizontalWin = true iff counter = NUM_TO_WIN
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player){
        int counter = 0;
        int leftBound, rightBound;
        int spacesAway = getNumToWin() - 1;
        int i = lastPos.getColumn();
        int j = lastPos.getRow();

        //sets the left bound to be checked
        leftBound = Math.max(0, i - spacesAway);

        //sets the right bound to be checked
        rightBound = Math.min(i + spacesAway, getNumColumns() - 1);

        //loop to check through row for win, increments count when player is found, sets to zero otherwise
        //counter at NUM_TO_WIN indicates NUM_TO_WIN in a row
        for(int x = leftBound; x <= rightBound; x++){
            BoardPosition current = new BoardPosition(x, j);
            if(isPlayerAtPos(current, player)) counter++;
            else counter = 0;
            if (counter == getNumToWin()) return true;
        }

        return false;
    }

    /**
     * checkVerticalWin checks to see if the last marker placed resulted in 5 in a row vertically
     * @param lastPos contains the location of the position of the last marker
     * @param player the token (X or O) just played
     * @return the truth value describing whether a game has been won vertically
     *
     * @pre 0 <= lastPos.Row < BOARD_SIZE and 0 <= lastPos.Column < BOARD_SIZE AND (player = X  or player = O)
     * @post checkVerticalWin = true iff counter = NUM_TO_WIN
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player){
        int counter = 0;
        int upperBound, lowerBound;
        int spacesAway = getNumToWin() - 1;
        int i = lastPos.getColumn();
        int j = lastPos.getRow();

        //sets the upper bound to be checked
        upperBound = Math.max(0, j - spacesAway);

        //sets the lower bound to be checked
        lowerBound = Math.min(getNumRows() - 1, j + spacesAway);

        //loop to check through column for win, increments count when player is found, sets to zero otherwise
        //counter at NUM_TO_WIN indicates NUM_TO_WIN in a row
        for(int y = upperBound; y <= lowerBound; y++){
            BoardPosition current = new BoardPosition(i, y);
            if(isPlayerAtPos(current, player)) counter++;
            else counter = 0;
            if (counter == getNumToWin()) return true;
        }

        return false;
    }


    /**
     * checkDiagonalWin checks to see if the last marker placed resulted in 5 in a row diagonally
     * @param lastPos contains the location of the position of the last marker
     * @param player the token (X or O) just played
     * @return the truth value describing whether a game has been won diagonally
     *
     * @pre 0 <= lastPos.Row < BOARD_SIZE and 0 <= lastPos.Column < BOARD_SIZE AND (player = X  or player = O)
     * @post checkDiagonallyWin = true iff counter = NUM_TO_WIN
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player){
        int counter = 0;
        int spacesAway = getNumToWin() - 1;
        int i = lastPos.getColumn();
        int j = lastPos.getRow();

        //for diagonal left to right
        int leftStartX, leftStartY, rightEndX, rightEndY;

        leftStartX = i - spacesAway;
        leftStartY = j - spacesAway;
        rightEndX = i + spacesAway;
        rightEndY = j + spacesAway;

        for(int x = leftStartX; x <= rightEndX; x++){
            for(int y = leftStartY; y <= rightEndY; y++){
                //is position out of bounds (not yet in board), continue loop forward
                if((x < 0) || (y < 0)){
                    continue;
                }
                //is position out of bounds (gone too far), end loop
                if((x > getNumColumns() - 1) || (y > getNumRows() -1)){
                    break;
                }
                //is player at the board position, if yes increment
                BoardPosition currentPos = new BoardPosition(x, y);
                if(isPlayerAtPos(currentPos, player)) counter++;
                else counter = 0;

                //has the player gotten enough in a row to win
                if(counter == getNumToWin()) return true;

                x++;
            }
        }

        //to check for diagonal win from right to left
        counter = 0;
        int rightStartX, rightStartY, leftEndX, leftEndY;
        rightStartX = i + spacesAway;
        rightStartY = j - spacesAway;
        leftEndX = i - spacesAway;
        leftEndY = j + spacesAway;

        for(int x = rightStartX; x >= leftEndX; x--){
            for(int y = rightStartY; y <= leftEndY; y++){
                //is position out of bounds (not yet in board), continue loop forward
                if((x < 0) || (y < 0)){
                    continue;
                }
                //is position out of bounds (gone too far), end loop
                if((x > getNumColumns() - 1) || (y > getNumRows() -1)){
                    break;
                }
                //is player at the board position, if yes increment
                BoardPosition currentPos = new BoardPosition(x, y);
                if(isPlayerAtPos(currentPos, player)) counter++;
                else counter = 0;

                //has the player gotten enough in a row to win
                if(counter == getNumToWin()) return true;

                x--;
            }
        }

        //both diagonals do not produce a win
        return false;
    }


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
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        if(whatsAtPos(pos) == player) return true;
        else return false;
    }

    /**
     * An overridden version of toString(). Returns one string that shows the entire game board
     * @return string showing entire game board
     *
     * @pre MINROWS <= NUM_ROWS <= MAXROWS
     * @pre MINCOLS <= NUM_COLUMNS <= MAXCOLS
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
    public int getNumRows();


    /**
     * @return returns the number of columns of the game board
     *
     * @pre NUM_COLUMNS has been initialized
     * @post getNumColumns = NUM_COLUMNS
     * @post NUM_COLUMNS = #NUM_COLUMNS
     */
    public int getNumColumns();

    /**
     * @return returns the number of pieces needs to win
     *
     * @pre NUM_TO_WIN has been initialized
     * @post getNumToWin = NUM_TO_WIN
     * @post NUM_TO_WIN = #NUM_TO_WIN
     */
    public int getNumToWin();




}
