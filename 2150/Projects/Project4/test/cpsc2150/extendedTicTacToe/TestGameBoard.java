package cpsc2150.extendedTicTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {

    //constructor to make board using fast implementation
    private IGameBoard MakeABoard(int r, int c, int numToWin){
        IGameBoard gb = new GameBoard(r, c, numToWin);
        return gb;
    }

    //make a string of the expected board array
    private String makeBoardString(char[][] board) {
        String boardOutput = "   ";

        int y = 0;

        //print the column labels
        for(int x = 0; x < board[0].length; x++){
            //take care of formatting double digits
            if(x >= 10){
                boardOutput = boardOutput.concat(String.valueOf(x));
            }
            else{
                boardOutput = boardOutput.concat(" ");
                boardOutput = boardOutput.concat(String.valueOf(x));
            }
            boardOutput = boardOutput.concat("|");
        }
        //print the row labels and the contents of the row
        while( y != board.length){
            boardOutput = boardOutput.concat("\n");
            //take care of formatting double digits
            if(y >= 10){
                boardOutput = boardOutput.concat(String.valueOf(y));
            }
            else{
                boardOutput = boardOutput.concat(" ");
                boardOutput = boardOutput.concat(String.valueOf(y));
            }
            boardOutput = boardOutput.concat("|");

            for(int x = 0; x < board[0].length; x++){
                BoardPosition pos = new BoardPosition(x, y);
                boardOutput = boardOutput.concat(String.valueOf(board[y][x]));
                boardOutput =boardOutput.concat(" |");
            }

            y++;
        }

        return boardOutput;
    }


    /*
     * test Constructor
     */

    //tests game board of minimum size
    @Test
    public void test_Constructor_Min_Size(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //make sure board is equal
        assertEquals(r, gb.getNumRows());
        assertEquals(c, gb.getNumColumns());
        assertEquals(numWin, gb.getNumToWin());

        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());


    }

    //tests game board with maximum size
    @Test
    public void test_Constructor_Max_Size(){
        int r = 100;
        int c = 100;
        int numWin = 25;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //make sure board is equal
        assertEquals(r, gb.getNumRows());
        assertEquals(c, gb.getNumColumns());
        assertEquals(numWin, gb.getNumToWin());

        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());

    }

    //routine test
    @Test
    public void test_Constructor_Routine(){
        int r = 6;
        int c = 6;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //make sure board is equal
        assertEquals(r, gb.getNumRows());
        assertEquals(c, gb.getNumColumns());
        assertEquals(numWin, gb.getNumToWin());

        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());

    }

    /*
     * test checkSpace
     */

    //test at (0,0)
    @Test
    public void test_checkSpace_at_upperLeftCorner(){
        int r = 4;
        int c = 4;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        int x = 0;
        int y = 0;
        BoardPosition pos = new BoardPosition(x, y);

        //make sure board is equal
        assertTrue(gb.checkSpace(pos));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());

    }

    //test at (4,4) -> out of bounds
    @Test
    public void test_checkSpace_outOfBounds(){
        int r = 4;
        int c = 4;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        int x = 4;
        int y = 4;
        BoardPosition pos = new BoardPosition(x, y);

        //make sure board is equal
        assertFalse(gb.checkSpace(pos));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test case where space is already taken by a player
    @Test
    public void test_checkSpace_Unavailable(){
        int r = 4;
        int c = 4;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        int x = 1;
        int y = 1;
        BoardPosition pos = new BoardPosition(x, y);
        gb.placeMarker(pos, 'x');
        expected[y][x] = 'x';

        //make sure board is equal
        assertFalse(gb.checkSpace(pos));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }


    /*
     * test checkHorizontalWin
     */

    //tests when winning token placed in middle of consecutive tokens
    @Test
    public void test_Horizontal_MiddleWin(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition mark1 = new BoardPosition(0,2);
        gb.placeMarker(mark1, 'x');
        expected[2][0] = 'x';
        BoardPosition mark2 = new BoardPosition(1,2);
        gb.placeMarker(mark2, 'x');
        expected[2][1] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';
        BoardPosition mark4 = new BoardPosition(3,2);
        gb.placeMarker(mark4, 'x');
        expected[2][3] = 'x';
        BoardPosition mark5 = new BoardPosition(3,3);
        gb.placeMarker(mark5, 'x');
        expected[3][3] = 'x';

        BoardPosition markO1 = new BoardPosition(0,3);
        gb.placeMarker(markO1, 'o');
        expected[3][0] = 'o';
        BoardPosition markO2 = new BoardPosition(1,3);
        gb.placeMarker(markO2, 'o');
        expected[3][1] = 'o';
        BoardPosition markO3 = new BoardPosition(2,3);
        gb.placeMarker(markO3, 'o');
        expected[3][2] = 'o';
        BoardPosition markO4 = new BoardPosition(4,3);
        gb.placeMarker(markO4, 'o');
        expected[3][4] = 'o';

        BoardPosition lastPos = new BoardPosition(2,2);

        //compare boards
        assertTrue(gb.checkHorizontalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed on left boundary of game board
    @Test
    public void test_Horizontal_left_Bound(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition mark1 = new BoardPosition(0,2);
        gb.placeMarker(mark1, 'x');
        expected[2][0] = 'x';
        BoardPosition mark2 = new BoardPosition(1,2);
        gb.placeMarker(mark2, 'x');
        expected[2][1] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';
        BoardPosition mark4 = new BoardPosition(3,2);
        gb.placeMarker(mark4, 'x');
        expected[2][3] = 'x';
        BoardPosition mark5 = new BoardPosition(3,3);
        gb.placeMarker(mark5, 'x');
        expected[3][3] = 'x';

        BoardPosition markO1 = new BoardPosition(0,3);
        gb.placeMarker(markO1, 'o');
        expected[3][0] = 'o';
        BoardPosition markO2 = new BoardPosition(1,3);
        gb.placeMarker(markO2, 'o');
        expected[3][1] = 'o';
        BoardPosition markO3 = new BoardPosition(2,3);
        gb.placeMarker(markO3, 'o');
        expected[3][2] = 'o';
        BoardPosition markO4 = new BoardPosition(4,3);
        gb.placeMarker(markO4, 'o');
        expected[3][4] = 'o';

        BoardPosition lastPos = new BoardPosition(0,2);

        //compare boards
        assertTrue(gb.checkHorizontalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed on right boundary of game board
    @Test
    public void test_Horizontal_right_Bound(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition mark1 = new BoardPosition(4,2);
        gb.placeMarker(mark1, 'x');
        expected[2][4] = 'x';
        BoardPosition mark2 = new BoardPosition(1,2);
        gb.placeMarker(mark2, 'x');
        expected[2][1] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';
        BoardPosition mark4 = new BoardPosition(3,2);
        gb.placeMarker(mark4, 'x');
        expected[2][3] = 'x';
        BoardPosition mark5 = new BoardPosition(3,3);
        gb.placeMarker(mark5, 'x');
        expected[3][3] = 'x';

        BoardPosition markO1 = new BoardPosition(0,3);
        gb.placeMarker(markO1, 'o');
        expected[3][0] = 'o';
        BoardPosition markO2 = new BoardPosition(1,3);
        gb.placeMarker(markO2, 'o');
        expected[3][1] = 'o';
        BoardPosition markO3 = new BoardPosition(2,3);
        gb.placeMarker(markO3, 'o');
        expected[3][2] = 'o';
        BoardPosition markO4 = new BoardPosition(4,3);
        gb.placeMarker(markO4, 'o');
        expected[3][4] = 'o';

        BoardPosition lastPos = new BoardPosition(4,2);

        //compare boards
        assertTrue(gb.checkHorizontalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed does not result in horizontal win
    @Test
    public void test_Horizontal_No_Win(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition mark1 = new BoardPosition(4,1);
        gb.placeMarker(mark1, 'x');
        expected[1][4] = 'x';
        BoardPosition mark2 = new BoardPosition(0,1);
        gb.placeMarker(mark2, 'x');
        expected[1][0] = 'x';
        BoardPosition mark3 = new BoardPosition(1,1);
        gb.placeMarker(mark3, 'x');
        expected[1][1] = 'x';
        BoardPosition mark4 = new BoardPosition(3,1);
        gb.placeMarker(mark4, 'x');
        expected[1][3] = 'x';

        BoardPosition markO1 = new BoardPosition(1,2);
        gb.placeMarker(markO1, 'o');
        expected[2][1] = 'o';
        BoardPosition markO2 = new BoardPosition(1,3);
        gb.placeMarker(markO2, 'o');
        expected[3][1] = 'o';
        BoardPosition markO3 = new BoardPosition(2,2);
        gb.placeMarker(markO3, 'o');
        expected[2][2] = 'o';
        BoardPosition markO4 = new BoardPosition(3,2);
        gb.placeMarker(markO4, 'o');
        expected[2][3] = 'o';

        BoardPosition lastPos = new BoardPosition(0,1);

        //compare boards
        assertFalse(gb.checkHorizontalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    /*
     * test checkVerticalWin
     */

    //tests when last token placed is in the middle of a string, resulting in a win
    @Test
    public void test_Vertical_Middle(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(2,0);
        gb.placeMarker(mark1, 'x');
        expected[0][2] = 'x';
        BoardPosition mark2 = new BoardPosition(2,1);
        gb.placeMarker(mark2, 'x');
        expected[1][2] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';
        BoardPosition mark4 = new BoardPosition(2,3);
        gb.placeMarker(mark4, 'x');
        expected[3][2] = 'x';
        BoardPosition mark5 = new BoardPosition(0,2);
        gb.placeMarker(mark5, 'x');
        expected[2][0] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,3);
        gb.placeMarker(markO2, 'o');
        expected[3][0] = 'o';
        BoardPosition markO3 = new BoardPosition(0,4);
        gb.placeMarker(markO3, 'o');
        expected[4][0] = 'o';
        BoardPosition markO4 = new BoardPosition(3,3);
        gb.placeMarker(markO4, 'o');
        expected[3][3] = 'o';

        BoardPosition lastPos = new BoardPosition(2,1);

        //compare boards
        assertTrue(gb.checkVerticalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed at the upper boundary of the board
    @Test
    public void test_Vertical_Upper(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(2,0);
        gb.placeMarker(mark1, 'x');
        expected[0][2] = 'x';
        BoardPosition mark2 = new BoardPosition(2,1);
        gb.placeMarker(mark2, 'x');
        expected[1][2] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';
        BoardPosition mark4 = new BoardPosition(2,3);
        gb.placeMarker(mark4, 'x');
        expected[3][2] = 'x';
        BoardPosition mark5 = new BoardPosition(0,2);
        gb.placeMarker(mark5, 'x');
        expected[2][0] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,3);
        gb.placeMarker(markO2, 'o');
        expected[3][0] = 'o';
        BoardPosition markO3 = new BoardPosition(0,4);
        gb.placeMarker(markO3, 'o');
        expected[4][0] = 'o';
        BoardPosition markO4 = new BoardPosition(3,3);
        gb.placeMarker(markO4, 'o');
        expected[3][3] = 'o';

        BoardPosition lastPos = new BoardPosition(2,0);

        //compare boards
        assertTrue(gb.checkVerticalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed at the lower boundary of the board
    @Test
    public void test_Vertical_Lower(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(2,4);
        gb.placeMarker(mark1, 'x');
        expected[4][2] = 'x';
        BoardPosition mark2 = new BoardPosition(2,1);
        gb.placeMarker(mark2, 'x');
        expected[1][2] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';
        BoardPosition mark4 = new BoardPosition(2,3);
        gb.placeMarker(mark4, 'x');
        expected[3][2] = 'x';
        BoardPosition mark5 = new BoardPosition(0,2);
        gb.placeMarker(mark5, 'x');
        expected[2][0] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,3);
        gb.placeMarker(markO2, 'o');
        expected[3][0] = 'o';
        BoardPosition markO3 = new BoardPosition(0,4);
        gb.placeMarker(markO3, 'o');
        expected[4][0] = 'o';
        BoardPosition markO4 = new BoardPosition(3,3);
        gb.placeMarker(markO4, 'o');
        expected[3][3] = 'o';

        BoardPosition lastPos = new BoardPosition(2,4);

        //compare boards
        assertTrue(gb.checkVerticalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed does not result in a vertical win
    @Test
    public void test_Vertical_No_Win(){
        int r = 5;
        int c = 5;
        int numWin = 4;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(2,4);
        gb.placeMarker(mark1, 'x');
        expected[4][2] = 'x';
        BoardPosition mark2 = new BoardPosition(2,1);
        gb.placeMarker(mark2, 'x');
        expected[1][2] = 'x';
        BoardPosition mark3 = new BoardPosition(2,0);
        gb.placeMarker(mark3, 'x');
        expected[0][2] = 'x';
        BoardPosition mark4 = new BoardPosition(2,3);
        gb.placeMarker(mark4, 'x');
        expected[3][2] = 'x';
        BoardPosition mark5 = new BoardPosition(0,2);
        gb.placeMarker(mark5, 'x');
        expected[2][0] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,3);
        gb.placeMarker(markO2, 'o');
        expected[3][0] = 'o';
        BoardPosition markO3 = new BoardPosition(0,4);
        gb.placeMarker(markO3, 'o');
        expected[4][0] = 'o';
        BoardPosition markO4 = new BoardPosition(3,3);
        gb.placeMarker(markO4, 'o');
        expected[3][3] = 'o';

        BoardPosition lastPos = new BoardPosition(2,3);

        //compare boards
        assertFalse(gb.checkVerticalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());

    }


    /*
     * test checkDiagonalWin
     */

    //tests when last token placed is in the top left corner (0,0)
    @Test
    public void test_Diagonal_Upper_Left(){
        int r = 5;
        int c = 5;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(0,0);
        gb.placeMarker(mark1, 'x');
        expected[0][0] = 'x';
        BoardPosition mark2 = new BoardPosition(1,1);
        gb.placeMarker(mark2, 'x');
        expected[1][1] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';

        BoardPosition lastPos = new BoardPosition(0,0);

        //compare boards
        assertTrue(gb.checkDiagonalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed is in the upper right corner (4,0)
    @Test
    public void test_Diagonal_Upper_Right(){
        int r = 5;
        int c = 5;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(4,0);
        gb.placeMarker(mark1, 'x');
        expected[0][4] = 'x';
        BoardPosition mark2 = new BoardPosition(3,1);
        gb.placeMarker(mark2, 'x');
        expected[1][3] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';

        BoardPosition lastPos = new BoardPosition(4,0);

        //compare boards
        assertTrue(gb.checkDiagonalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed is in the lower right corner (4,4)
    @Test
    public void test_Diagonal_Lower_Right(){
        int r = 5;
        int c = 5;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(4,4);
        gb.placeMarker(mark1, 'x');
        expected[4][4] = 'x';
        BoardPosition mark2 = new BoardPosition(3,3);
        gb.placeMarker(mark2, 'x');
        expected[3][3] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';

        BoardPosition lastPos = new BoardPosition(4,4);

        //compare boards
        assertTrue(gb.checkDiagonalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed is in the lower left corner (0,4)
    @Test
    public void test_Diagonal_Lower_Left(){
        int r = 5;
        int c = 5;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(0,4);
        gb.placeMarker(mark1, 'x');
        expected[4][0] = 'x';
        BoardPosition mark2 = new BoardPosition(1,3);
        gb.placeMarker(mark2, 'x');
        expected[3][1] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';

        BoardPosition lastPos = new BoardPosition(0,4);

        //compare boards
        assertTrue(gb.checkDiagonalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when winning diagonal does not hit an edge (routine)
    @Test
    public void test_Diagonal_Routine(){
        int r = 5;
        int c = 5;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(1,1);
        gb.placeMarker(mark1, 'x');
        expected[1][1] = 'x';
        BoardPosition mark2 = new BoardPosition(3,3);
        gb.placeMarker(mark2, 'x');
        expected[3][3] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';

        BoardPosition lastPos = new BoardPosition(2,2);

        //compare boards
        assertTrue(gb.checkDiagonalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when winning diagonal where both ends of the diagonal hit an edge of the board
    @Test
    public void test_Diagonal_Edges(){
        int r = 5;
        int c = 5;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(2,0);
        gb.placeMarker(mark1, 'x');
        expected[0][2] = 'x';
        BoardPosition mark2 = new BoardPosition(3,1);
        gb.placeMarker(mark2, 'x');
        expected[1][3] = 'x';
        BoardPosition mark3 = new BoardPosition(4,2);
        gb.placeMarker(mark3, 'x');
        expected[2][4] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';

        BoardPosition lastPos = new BoardPosition(4,2);

        //compare boards
        assertTrue(gb.checkDiagonalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last move does not result in a win diagonally
    @Test
    public void test_Diagonal_No_Win(){
        int r = 5;
        int c = 5;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(1,1);
        gb.placeMarker(mark1, 'x');
        expected[1][1] = 'x';
        BoardPosition mark2 = new BoardPosition(3,1);
        gb.placeMarker(mark2, 'x');
        expected[1][3] = 'x';
        BoardPosition mark3 = new BoardPosition(2,2);
        gb.placeMarker(mark3, 'x');
        expected[2][2] = 'x';

        BoardPosition markO1 = new BoardPosition(0,1);
        gb.placeMarker(markO1, 'o');
        expected[1][0] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';

        BoardPosition lastPos = new BoardPosition(3,1);

        //compare boards
        assertFalse(gb.checkDiagonalWin(lastPos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    /*
     * test checkForDraw
     */

    //tests when last token placed results in full board and draw (also in position (0,0))
    @Test
    public void test_Draw_True(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(0,0);
        gb.placeMarker(mark1, 'x');
        expected[0][0] = 'x';
        BoardPosition mark2 = new BoardPosition(2,0);
        gb.placeMarker(mark2, 'x');
        expected[0][2] = 'x';
        BoardPosition mark3 = new BoardPosition(0,1);
        gb.placeMarker(mark3, 'x');
        expected[1][0] = 'x';
        BoardPosition mark4 = new BoardPosition(1,1);
        gb.placeMarker(mark4, 'x');
        expected[1][1] = 'x';
        BoardPosition mark5 = new BoardPosition(1,2);
        gb.placeMarker(mark5, 'x');
        expected[2][1] = 'x';

        BoardPosition markO1 = new BoardPosition(1,0);
        gb.placeMarker(markO1, 'o');
        expected[0][1] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';
        BoardPosition markO3 = new BoardPosition(2,1);
        gb.placeMarker(markO3, 'o');
        expected[1][2] = 'o';
        BoardPosition markO4 = new BoardPosition(2,2);
        gb.placeMarker(markO4, 'o');
        expected[2][2] = 'o';

        //compare boards
        assertTrue(gb.checkForDraw());
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when token placed is the first token on the board
    @Test
    public void test_Draw_FirstSpace(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(0,0);
        gb.placeMarker(mark1, 'x');
        expected[0][0] = 'x';

        //compare boards
        assertFalse(gb.checkForDraw());
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when last token placed makes the board 1 from full
    @Test
    public void test_Draw_Almost_Full(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(0,0);
        gb.placeMarker(mark1, 'x');
        expected[0][0] = 'x';
        BoardPosition mark2 = new BoardPosition(2,0);
        gb.placeMarker(mark2, 'x');
        expected[0][2] = 'x';
        BoardPosition mark3 = new BoardPosition(0,1);
        gb.placeMarker(mark3, 'x');
        expected[1][0] = 'x';
        BoardPosition mark4 = new BoardPosition(1,1);
        gb.placeMarker(mark4, 'x');
        expected[1][1] = 'x';
        BoardPosition mark5 = new BoardPosition(1,2);
        gb.placeMarker(mark5, 'x');
        expected[2][1] = 'x';

        BoardPosition markO1 = new BoardPosition(1,0);
        gb.placeMarker(markO1, 'o');
        expected[0][1] = 'o';
        BoardPosition markO2 = new BoardPosition(2,1);
        gb.placeMarker(markO2, 'o');
        expected[1][2] = 'o';
        BoardPosition markO3 = new BoardPosition(0,2);
        gb.placeMarker(markO3, 'o');
        expected[2][0] = 'o';

        //compare boards
        assertFalse(gb.checkForDraw());
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //tests when some of board has been filled (middle of play)
    @Test
    public void test_Draw_Routine(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board (both gameboard object and array)
        BoardPosition mark1 = new BoardPosition(0,0);
        gb.placeMarker(mark1, 'x');
        expected[0][0] = 'x';
        BoardPosition mark2 = new BoardPosition(2,0);
        gb.placeMarker(mark2, 'x');
        expected[0][2] = 'x';
        BoardPosition mark3 = new BoardPosition(0,1);
        gb.placeMarker(mark3, 'x');
        expected[1][0] = 'x';

        BoardPosition markO1 = new BoardPosition(1,0);
        gb.placeMarker(markO1, 'o');
        expected[0][1] = 'o';
        BoardPosition markO2 = new BoardPosition(0,2);
        gb.placeMarker(markO2, 'o');
        expected[2][0] = 'o';


        //compare boards
        assertFalse(gb.checkForDraw());
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }


    /*
     * test whatsAtPos
     */

    //test (0,0)
    @Test
    public void test_whatsAtPos_upperLeft(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'x');
        expected[0][0] = 'x';

        //compare boards
        assertEquals(gb.whatsAtPos(pos), 'x');
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());

    }

    //test (2,2)
    @Test
    public void test_whatsAtPos_lowerRight(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(2,2);
        gb.placeMarker(pos, 'x');
        expected[2][2] = 'x';

        //compare boards
        assertEquals(gb.whatsAtPos(pos), 'x');
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test (1,1)
    @Test
    public void test_whatsAtPos_Middle(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(1,1);
        gb.placeMarker(pos, 'o');
        expected[1][1] = 'o';

        //compare boards
        assertEquals(gb.whatsAtPos(pos), 'o');
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test (2,2)
    @Test
    public void test_whatsAtPos_EmptySpace(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition mark = new BoardPosition(1,1);
        gb.placeMarker(mark, 'o');
        expected[1][1] = 'o';

        BoardPosition pos = new BoardPosition(2,2);

        //compare boards
        assertEquals(gb.whatsAtPos(pos), ' ');
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test (2, 1)
    @Test
    public void test_whatsAtPos_Edge(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(2,1);
        gb.placeMarker(pos, 'x');
        expected[1][2] = 'x';

        //compare boards
        assertEquals(gb.whatsAtPos(pos), 'x');
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    /*
     * test isPlayerAtPos
     */

    //test at (0,0)
    @Test
    public void test_isPlayerAt_at_upperLeftCorner(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'x');
        expected[0][0] = 'x';

        //compare boards
        assertTrue(gb.isPlayerAtPos(pos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test at (2,2)
    @Test
    public void test_isPlayerAt_at_lowerRight(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(2,2);
        gb.placeMarker(pos, 'x');
        expected[2][2] = 'x';

        //compare boards
        assertTrue(gb.isPlayerAtPos(pos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test (1,1) and other player on board
    @Test
    public void test_isPlayerAt_at_Middle(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(1,1);
        gb.placeMarker(pos, 'x');
        expected[1][1] = 'x';
        BoardPosition mark2 = new BoardPosition(0,0);
        gb.placeMarker(mark2, 'o');
        expected[0][0] = 'o';

        //compare boards
        assertTrue(gb.isPlayerAtPos(pos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test with no players
    @Test
    public void test_isPlayerAt_No_Players(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(1,1);

        //compare boards
        assertFalse(gb.isPlayerAtPos(pos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }

    //test with wrong player
    @Test
    public void test_isPlayerAt_Wrong_Player(){
        int r = 3;
        int c = 3;
        int numWin = 3;
        //make expected board for comparison
        char [][] expected = new char[r][c];

        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                expected[j][i] = ' ';
            }
        }

        IGameBoard gb = MakeABoard(r, c, numWin);

        //place markers on board
        BoardPosition pos = new BoardPosition(1,1);
        gb.placeMarker(pos, 'o');
        expected[1][1] = 'o';
        BoardPosition mark2 = new BoardPosition(0,0);
        gb.placeMarker(mark2, 'x');
        expected[0][0] = 'x';

        //compare boards
        assertFalse(gb.isPlayerAtPos(pos, 'x'));
        //compare strings of board
        String expectedBoard = makeBoardString(expected);
        assertEquals(expectedBoard, gb.toString());
    }


}
















