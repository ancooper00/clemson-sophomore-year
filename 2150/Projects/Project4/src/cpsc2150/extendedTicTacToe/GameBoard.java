package cpsc2150.extendedTicTacToe;

import java.util.*;
import java.lang.*;

/**
 * class that contains the actual game board
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant board is of size NUM_ROWS * NUM_COLUMNS
     * @invariant MINCOLS <= NUM_COLUMNS <= MAXCOLS
     * @invariant MINROWS <= NUM_ROWS <= MAXROWS
     * @invariant MINWIN <= NUM_TO_WIN <= MAXWIN
     *
     * Correspondence NUM_ROWS = NUM_ROWS
     *                NUM_COLUMNS = NUM_COLUMNS
     *                NUM_TO_WIN = NUM_TO_WIN
     *
     */

    private char [][] board;

    private int NUM_ROWS;
    private int NUM_COLUMNS;
    private int NUM_TO_WIN;

    /**
     * Constructor to make an empty game board
     * @ensures board = board[BOARD_SIZE][BOARD_SIZE]
     * @ensures for all entries board = " "
     *
     * @pre NONE
     * @post board = board[BOARD_SIZE][BOARD_SIZE]
     * @post for all 0 <= i < BOARD_SIZE and all 0 <= j < BOARD_SIZE, board[i][j] = " "
     * @post NUM_ROWS = r
     * @post NUM_COLUMNS = c
     * @post NUM_TO_WIN = numWIN
     */
    public GameBoard(int r, int c, int numWin){
        NUM_COLUMNS = c;
        NUM_ROWS = r;
        NUM_TO_WIN = numWin;

        //initialize all spaces in 2D array to blank character
        board = new char[NUM_ROWS][NUM_COLUMNS];
        for(int i = 0; i < NUM_ROWS; i++){
            for(int j = 0; j < NUM_COLUMNS; j++){
                board[i][j] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player){
        int i, j;
        i = marker.getColumn();
        j = marker.getRow();

        board[j][i] = player;
    }

    public char whatsAtPos(BoardPosition pos){
        char c;
        int i, j;
        i = pos.getColumn();
        j = pos.getRow();

        c = board[j][i];

        return c;
    }

    public int getNumRows(){
        return NUM_ROWS;
    }

    public int getNumColumns(){
        return NUM_COLUMNS;
    }

    public int getNumToWin(){
        return NUM_TO_WIN;
    }


}
