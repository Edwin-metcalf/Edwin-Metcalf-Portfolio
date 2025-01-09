
/**
 * Similar to player this creates the enemy piece and generates responses to when the
 * enemies collide with other characters
 *
 * @author (Edwin Metcalf)
 * @version (2.0)
 */
public class Enemy extends Piece
{
    String message;
    Enemy() {
        super('&');
    }

    /**
     * the collide method returns the correct string when the enemy hits other characters
     * @param p
     * @return
     */
    public String collide(Object p) {

        if (p instanceof Treasure) {
            message = "The enemy ate the Treasure oh no!";

        } else if (p instanceof Enemy) {

            message = "Invalid";

        } else if (p instanceof Exit){
            message = "Invalid";

        } else if (p instanceof Player){
            message = "Enemy collided with the player its game over";
        }

        return message;

    }

}
