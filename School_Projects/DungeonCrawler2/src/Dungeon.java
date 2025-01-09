
/**
 * The dungeon class puts our game together by creating the actual board that the game
 * will be played on and having our play method which controls the game flow
 * along with dealing with the player imputed turns
 *
 * @author (Edwin Metcalf)
 * @version (2.0)
 */

import java.util.Objects;
import java.util.Scanner;

public class Dungeon
{
    int size;
    Board boardRoom = new Board(size);
    int input = -1;
    Scanner s = new Scanner(System.in);
    Player player = new Player();
    boolean gameActive = true;
    boolean levelActive = true;
    boolean programActive = true;
    int totalScore = 0;
    int highScore = 0;


    /**
     * this method creates the player turn and creates the turn based game play loop
     * @return
     */
    public String playerTurn() {
       // String playerDirection = s.nextLine();
        boolean valid = false;
        String playerReturn = "";
        String playerDirection;

        System.out.println("Input your move!");

        while (!valid) {
            playerDirection = s.nextLine();


            switch (playerDirection) {
                case "w": {
                    playerReturn = boardRoom.movePlayer(-1, 0);
                    break;}
                case "a": {
                    playerReturn = boardRoom.movePlayer(0, -1);
                    break;}
                case "s": {
                    playerReturn = boardRoom.movePlayer(1, 0);
                    break; }
                case "d": {
                    playerReturn = boardRoom.movePlayer(0, 1);
                    break; }
                default: {
                     System.out.println("Invalid move please try WASD");
                }
            }
            if(!playerReturn.equals("Invalid")){
                System.out.println(playerReturn);
                valid = true;
            } else {
                System.out.println("Invalid");
            }
        }
        return playerReturn;
    }

    /**
     * this method takes in paramaters size and level and then creates a board unique to player input of
     * size and the level the player is currently on
     * @param level
     * @param size
     */
    public void init(int level, int size) {
        Move startingLoc = new Move(size - 1 ,size - 1);
        boardRoom = new Board(size);
        boardRoom.setPlayer(startingLoc);
        boardRoom.myLoc = startingLoc;
        boardRoom.setTreasure(5);
        boardRoom.setEnemies(level);
        boardRoom.setExit();

    }

    /**
     * this method play is the game play loop and goes through the building of the board and then the
     * turn based game play loop
     */

    public void play() {
        gameActive = true;
        levelActive = true;

        System.out.println("welcome to a fun fun dungeon crawler. Have fun!");

        while (input < 0 || input > 99) {
            System.out.println("how large would you like the map? return a number for the dimensions of the map: ");
            try {
                input = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("thats not a number try a number please: ");

            }
            if (input < 0 || input > 99) {
                System.out.println("that number does not work for the dimensions, try a smaller or bigger one:");
            }
        }
        size = input;
        System.out.println("Great choice for the board: ");


        int level = 0;
        totalScore = 0;


            while (gameActive) {
                level++;
                init(level, size);
                levelActive = true;
                String result = "";
                while (gameActive && levelActive) {

                    System.out.println(boardRoom);
                    result = playerTurn();


                    if (result.equals("Oh no you got smacked by an enemy, that is game over!") || result.equals("Holy cow you got outa there good job! That level is over.")) {

                        if (result.equals("Oh no you got smacked by an enemy, that is game over!")) {
                            gameActive = false;
                        }
                        break;
                    }


                    result = boardRoom.moveEnemies();
                    System.out.println("beware enemies moved");

                    //System.out.println(boardRoom);
                    if (result.equals("Enemy collided with the player its game over")) {
                        levelActive = false;
                        gameActive = false;
                    }
                    System.out.println(result);

                }
                totalScore += boardRoom.player.score;
                System.out.println("level score: " + boardRoom.player.score + ", total score: " + totalScore);
                if (level > (size * size) - 2){
                    System.out.println("you made it so far there are no more rooms for enemies!");
                    gameActive =false;
                }
            }
            System.out.println("game over!, gg here is your score: " + totalScore + ", you died on level: " + level);

            if (totalScore > highScore) {
                highScore = totalScore;
                System.out.println("thats a new highscore!! Great job!");
            }


            System.out.println("Press any button to play again or type \"quit\" to exit");
            String exit = s.nextLine();
            if (!exit.equals("quit")) {
                play();
            } else {
                System.out.println("goodbye, adios, see you next time");
            }
    }
}
