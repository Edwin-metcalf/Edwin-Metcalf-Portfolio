
/**
 * Write a description of class Move here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Move
{
    int row;
    int col;
    Move(int row, int col){
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return row + "," + col;
    }
}
