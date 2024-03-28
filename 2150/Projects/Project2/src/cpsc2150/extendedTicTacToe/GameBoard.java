package cpsc2150.extendedTicTacToe;

import java.util.*;
import java.lang.*;

/**
 * class that contains the actual game board
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant board is of size BOARD_SIZE * BOARD_SIZE
     *
     * Correspondence BOARD = board
     */

    private char [][] board;

    /**
     * Constructor to make an empty game board
     * @ensures board = board[BOARD_SIZE][BOARD_SIZE]
     * @ensures for all entries board = " "
     *
     * @pre NONE
     * @post board = board[BOARD_SIZE][BOARD_SIZE]
     * @post for all 0 <= i < BOARD_SIZE and all 0 <= j < BOARD_SIZE, board[i][j] = " "
     */
    public GameBoard(){
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player){
        int i, j;
        i = marker.getColumn();
        j = marker.getRow();

        board[i][j] = player;
    }

    public boolean checkForDraw(){

        for(int i = START_X; i < BOARD_SIZE; i++){
            for (int j = START_Y; j < BOARD_SIZE; j++){
                if(board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public boolean checkHorizontalWin(BoardPosition lastPos, char player){
        int counter = 0;
        int leftBound, rightBound;
        int spacesAway = NUM_TO_WIN - 1;
        int i = lastPos.getColumn();
        int j = lastPos.getRow();

        //sets the left bound to be checked
        leftBound = Math.max(0, i - spacesAway);

        //sets the right bound to be checked
        rightBound = Math.min(i + spacesAway, BOARD_SIZE - 1);

        //loop to check through row for win, increments count when player is found, sets to zero otherwise
        //counter at NUM_TO_WIN indicates NUM_TO_WIN in a row
        for(int x = leftBound; x <= rightBound; x++){
            if(board[x][j] == player) counter++;
            else counter = 0;
            if (counter == NUM_TO_WIN) return true;
        }

        return false;
    }

    public boolean checkVerticalWin(BoardPosition lastPos, char player){
        int counter = 0;
        int upperBound, lowerBound;
        int spacesAway = NUM_TO_WIN - 1;
        int i = lastPos.getColumn();
        int j = lastPos.getRow();

        //sets the upper bound to be checked
        upperBound = Math.max(0, j - spacesAway);

        //sets the lower bound to be checked
        lowerBound = Math.min(BOARD_SIZE - 1, j + spacesAway);

        //loop to check through column for win, increments count when player is found, sets to zero otherwise
        //counter at NUM_TO_WIN indicates NUM_TO_WIN in a row
        for(int y = upperBound; y <= lowerBound; y++){
            if(board[i][y] == player) counter++;
            else counter = 0;
            if (counter == NUM_TO_WIN) return true;
        }

        return false;
    }

    public boolean checkDiagonalWin(BoardPosition lastPos, char player){
        int counter = 0;
        int spacesAway = NUM_TO_WIN - 1;
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
                if((x > BOARD_SIZE - 1) || (y > BOARD_SIZE -1)){
                    break;
                }
                //is player at the board position, if yes increment
                if(board[x][y] == player) counter++;
                else counter = 0;

                //has the player gotten enough in a row to win
                if(counter == NUM_TO_WIN) return true;

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
                if((x > BOARD_SIZE - 1) || (y > BOARD_SIZE -1)){
                    break;
                }
                //is player at the board position, if yes increment
                if(board[x][y] == player) counter++;
                else counter = 0;

                //has the player gotten enough in a row to win
                if(counter == NUM_TO_WIN) return true;

                x--;
            }
        }

        //both diagonals do not produce a win
        return false;


    }

    public char whatsAtPos(BoardPosition pos){
        char c;
        int i, j;
        i = pos.getColumn();
        j = pos.getRow();

        c = board[i][j];

        return c;
    }

    public boolean isPlayerAtPos(BoardPosition pos, char player){
        char c;

        c = whatsAtPos(pos);

        if(c == player) return true;
        else return false;
    }

    @Override
    public String toString(){
        String boardOutput = "  ";

        int y = 0;

        //print the column labels
        for(int x = 0; x < BOARD_SIZE; x++){
            boardOutput = boardOutput.concat(String.valueOf(x));
            boardOutput = boardOutput.concat(" ");
        }
        //print the row labels and the contents of the row
        while( y != BOARD_SIZE){
            boardOutput = boardOutput.concat("\n");
            boardOutput =boardOutput.concat(String.valueOf(y));
            boardOutput = boardOutput.concat("|");

            for(int x = 0; x < BOARD_SIZE; x++){
                boardOutput = boardOutput.concat(String.valueOf(board[x][y]));
                boardOutput =boardOutput.concat("|");
            }

            y++;
        }

        return boardOutput;
    }


}
