

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class Test1.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Test1
{
        public Test1()
    {
    }

    @BeforeEach
    public void setUp()
    {
    }

    @Test 
    public void testBoard() {
        Board boardOne = new Board(3);
        String result = boardOne.toString();
        String expected = "   1 2 3 \n   ______\nA |_ _ _ |\nB |_ _ _ |\nC |_ _ _ |\n   ______";
        assertEquals(expected, result);

        Board boardTwo = new Board(7);
        result = boardTwo.toString();
        expected = "   1 2 3 4 5 6 7 \n   ______________\nA |_ _ _ _ _ _ _ |\nB |_ _ _ _ _ _ _ |\nC |_ _ _ _ _ _ _ |\nD |_ _ _ _ _ _ _ |\nE |_ _ _ _ _ _ _ |\nF |_ _ _ _ _ _ _ |\nG |_ _ _ _ _ _ _ |\n   ______________";
        assertEquals(expected,result);
    }

    @Test
    public void testEnemy() {
        Board enemyBoard = new Board(3);
        enemyBoard.board[1][1] = new Enemy();
        enemyBoard.board[0][2] = new Enemy();
        String result = enemyBoard.toString();
        String expected = "   1 2 3 \n   ______\nA |_ _ & |\nB |_ & _ |\nC |_ _ _ |\n   ______";
        assertEquals(expected, result);
    }

    @Test
    public void testPlayer() {
        Board playerBoard = new Board(3);
        playerBoard.board[2][2] = new Player();
        String result = playerBoard.toString();
        String expected = "   1 2 3 \n   ______\nA |_ _ _ |\nB |_ _ _ |\nC |_ _ * |\n   ______";
        assertEquals(expected, result);
    }

    @Test
    public void testTreasure() {
        Board tBoard = new Board(3);
        tBoard.board[1][1] = new Treasure();
        String result = tBoard.toString();
        String expected = "   1 2 3 \n   ______\nA |_ _ _ |\nB |_ $ _ |\nC |_ _ _ |\n   ______";
        assertEquals(expected, result);
    }

    @Test
    public void testExit() {
        Board exitBoard = new Board(3);
        exitBoard.board[1][1] = new Exit();
        String result = exitBoard.toString();
        String expected = "   1 2 3 \n   ______\nA |_ _ _ |\nB |_ @ _ |\nC |_ _ _ |\n   ______";
        assertEquals(expected, result);
    }

    @Test
    public void testMove() {
        Board moveBoard = new Board(3);
        assertTrue(moveBoard.isValid(new Move (0,2))); 
        assertFalse(moveBoard.isValid(new Move(-2,0)));
        assertFalse(moveBoard.isValid(new Move(4,3)));
    }


    @AfterEach
    public void tearDown()
    {
    }
}
