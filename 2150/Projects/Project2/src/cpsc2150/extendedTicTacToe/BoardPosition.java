package cpsc2150.extendedTicTacToe;

/**
 * This class is used to keep track of an individual cell for a board
 */
public class BoardPosition {

    private int Row;
    private int Column;

    /**
     * Constructor for the BoardPosition object
     * @param c is the value to be assigned to Column
     * @param r is the value to be assigned to Row
     *
     * @pre NONE
     * @post Row = r AND Column = c
     */
    public BoardPosition(int c, int r){
        Row = r;
        Column = c;
    }

    /**
     * getRow returns the value Row in the BoardPosition object
     * @return the value held in Row
     *
     * @pre NONE
     * @post getRow = Row
     */
    public int getRow(){
        return Row;
    }

    /**
     * getColumn returns the value Column in the BoardPosition object
     * @return the value held in Column
     *
     * @pre NONE
     * @post getColumn = Column
     */
    public int getColumn(){
        return Column;
    }

    /**
     * equals override to determine if 2 BoardPositions are the same
     * @return the truth value of a comparison between 2 BoardPositions
     *
     * @pre NONE
     * @post equals = true if Row = o.Row AND Column = o.Column
     */
    @Override
    public boolean equals(Object other){
        if (other == null) return false;

        if(other == this) return true;
        if(!(other instanceof BoardPosition)) return false;

        BoardPosition comp = (BoardPosition) other;

        if((Row == comp.Row) && (Column == comp.Column)) return true;
        else return false;
    }

    /**
     * Overrride of toString that creates and returns a string in the format "<row>,<column>"
     * @return string version of the position in a BoardPosition object
     *
     * @pre NONE
     * @post toString = string in the format "<row>,<column>"
     */
    @Override
    public String toString(){
        return String.format("(" + Row + "," + Column + ")");
    }

}
