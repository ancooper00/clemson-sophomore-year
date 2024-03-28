
public abstract class AbsGameBoard implements IGameBoard{
    @Override
    public String toString(){
        String boardOutput = "  ";

        int y = 0;


        for(int x = 0; x < BOARD_SIZE; x++){
            boardOutput = boardOutput.concat(String.valueOf(x));
            boardOutput = boardOutput.concat(" ");
        }
        while( y != BOARD_SIZE){
            boardOutput = boardOutput.concat("\n");
            boardOutput =boardOutput.concat(String.valueOf(y));
            boardOutput = boardOutput.concat("|");

            for(int x = 0; x < BOARD_SIZE; x++){
                BoardPosition pos = new BoardPosition(x, y);
                boardOutput = boardOutput.concat(String.valueOf(whatsAtPos(pos)));
                boardOutput =boardOutput.concat("|");
            }

            y++;
        }

        return boardOutput;
    }
}



