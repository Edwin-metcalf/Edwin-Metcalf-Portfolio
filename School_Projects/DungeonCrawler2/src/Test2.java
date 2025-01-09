import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Test2 {
    Enemy e = new Enemy();
    Player p = new Player();
    Treasure t = new Treasure();
    Exit ex = new Exit();
    Board b = new Board(5);
    public Test2()
    {
    }

    @BeforeEach
    public void setUp()
    {
    }

    @Test
    public void testCollidePlayer() {
        t.value = 100;
        p.score += t.value;
        assertEquals(p.collide(t),"You found " + p.score + " Dungeon Bucks!!");
        assertEquals(p.collide(ex),"Holy cow you got outa there good job! That level is over.");
        assertEquals(p.collide(e),"Oh no you got smacked by an enemy, that is game over!");
    }
    @Test
    public void testCollideEnemy(){
        assertEquals(e.collide(p),"you collided with the player its game over" );
        assertEquals(e.collide(e),"Invalid" );
        assertEquals(e.collide(ex),"Invalid" );
        assertEquals(e.collide(t),"The enemy ate the Treasure oh no!");
    }
    @Test
    public void testMovePlayer() {
        Move playerLoc = new Move(b.size -1,b.size -1);
        b.setPlayer(playerLoc);
        System.out.println(b);
        b.movePlayer(-1,0);
        assertEquals(b.board[b.size -2][b.size -1], new Player());
        System.out.println(b);
        //assertEquals(b.movePlayer(1,0), );
        //assertThrows(IllegalArgumentException.class () -> b.movePlayer(2,0););
    }
    @Test
    public void testGetValidEnemyMove() {
        int enemyRow = 3;
        int enemyCol = 3;
        //Move move = b.getValidEnemyMove(b.enemies[1]);
        Enemy testEnemy = new Enemy();
        b.board[enemyRow][enemyCol] = testEnemy;
        Move testMove = b.getValidEnemyMove(new Move(3,3));
        b.board[testMove.row][testMove.col] = new Enemy();
        System.out.println(b);
        System.out.println(testMove);

       // assertEquals(b.getValidEnemyMove(testMove),();
    }
    @Test
    public void testMoveEnemies() {
        b.setEnemies(3);
        b.setTreasure(5);
        b.setExit();
        System.out.println(b);
        b.moveEnemies();
        System.out.println(b);
    }





    @AfterEach
    public void tearDown()
    {
    }
}
