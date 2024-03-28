package cpsc2150.extendedTicTacToe;

/**
 * Class holds the overridden method for toString,
 * which is used to return a string representing the game board
 */
public abstract class  AbsGameBoard implements IGameBoard{
    @Override
    public String toString(){
        String boardOutput = "   ";

        int y = 0;

        //print the column labels
        for(int x = 0; x < getNumColumns(); x++){
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
        while( y != getNumRows()){
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

            for(int x = 0; x < getNumColumns(); x++){
                BoardPosition pos = new BoardPosition(x, y);
                boardOutput = boardOutput.concat(String.valueOf(whatsAtPos(pos)));
                boardOutput =boardOutput.concat(" |");
            }

            y++;
        }

        return boardOutput;
    }
}



