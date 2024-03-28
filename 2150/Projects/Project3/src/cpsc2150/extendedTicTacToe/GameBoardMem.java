package cpsc2150.extendedTicTacToe;

import java.util.*;
import java.lang.*;

/**
 class that holds the memory efficient map implementation of the game board
 **/

public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    /**
     * @invariant board is of size NUM_ROWS * NUM_COLUMNS
     * @invariant MINCOLS <= NUM_COLUMNS <= MAXCOLS
     * @invariant MINROWS <= NUM_ROWS <= MAXROWS
     * @invariant MINWIN <= NUM_TO_WIN <= MAXWIN
     *
     * Correspondence BOARD = board
     */

    private Map<Character, List<BoardPosition>> board;

    private int NUM_ROWS;
    private int NUM_COLUMNS;
    private int NUM_TO_WIN;

    /**
     * Constructor to make an empty game board
     * @ensures board is a map with Characters as the keys and List<BoardPosition> as the values
     * @param c is the number of columns user wants
     * @param r is the number of rows user wants
     * @param numWin is the number of tokens in a row to win the user wants
     *
     * @pre NONE
     * @post board is a map with Characters as keys and List<BoardPosition> as the values
     * @post NUM_ROWS = r
     * @post NUM_COLUMNS = c
     * @post NUM_TO_WIN = numWIN
     */
    public GameBoardMem(int r, int c, int numWin){
        NUM_COLUMNS = c;
        NUM_ROWS = r;
        NUM_TO_WIN = numWin;

        //creates map to represent the board
        board = new HashMap<Character, List<BoardPosition>>();
    }

    public void placeMarker(BoardPosition marker, char player){
        //if player is not a key add new key value pair
        // else add to player list
        if(board.containsKey(player)){
            board.get(player).add(marker);
        }
        else{
            board.put(player, new ArrayList<BoardPosition>());
            board.get(player).add(marker);
        }
    }

    public char whatsAtPos(BoardPosition pos){
        //loop through all lists and find whats at pos
        for(Character C : board.keySet()){
            for(BoardPosition bPos : board.get(C)){
                if (bPos.equals(pos)) return C;
            }
        }

        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        //if player is not on the board at all
        if(!board.containsKey(player)){
            return false;
        }

        //in list for player loop through all positions to see if pos in list
        else{
            for(BoardPosition bPos : board.get(player)) {
                if (pos.equals(bPos)) return true;
            }
        }

        return false;
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

