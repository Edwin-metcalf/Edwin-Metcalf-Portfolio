
/**
 * This is the player it is a * and holds a score the collide method is for when it hits
 * other characters in the game
 *
 * @author (Edwin Metcalf)
 * @version (2.0)
 */
public class Player extends Piece
{
    int score = 0;
    String message = "";
    Boolean alive = true;
    Player() {
        super('*');
    }

    /**
     * the Collide method returns the correct string for when the player interacts with another character on the
     * board
     * @param p
     * @return
     */
    public String collide(Object p) {

           if (p instanceof Treasure) {
               Treasure t = (Treasure) p;
               score += t.value;
               message = "You found " + score + " Dungeon Bucks!!";

           } else if (p instanceof Enemy) {
               alive = false;
               message = "Oh no you got smacked by an enemy, that is game over!";
               
           } else if (p instanceof Exit){
               message = "Holy cow you got outa there good job! That level is over.";

           }
           return message;
    }
}
