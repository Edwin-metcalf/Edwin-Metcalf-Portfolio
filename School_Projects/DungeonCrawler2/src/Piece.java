
/**
 * Write a description of class Piece here.
 *
 * @author (Edwin Metcalf)
 * @version (1.0)
 */
public abstract class Piece
{
    public char symbol;

    public Piece(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Piece){
            Piece p = (Piece) o;
            if (p.symbol == this.symbol) {
                return true;
            } else {
                return false;
            }


        } else{
            return false;
        }
    }

    public String toString() {
        return "" + symbol;
    }


}
