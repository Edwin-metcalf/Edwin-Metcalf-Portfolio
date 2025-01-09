
/**
 * Write a description of class Treasure here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Treasure extends Piece
{
    int value;
    double randDecimal = Math.random();
    int randInt = (int)(10 * randDecimal + 1);

    Treasure() {
        super('$');
        this.value = randInt * 100;
    }
}
