import java.util.Objects;
import java.util.LinkedList;

/**
 * Board class creates the board that the game will be played
 * it sets all of the special characters on the board and also holds the methods for
 * moving the characters
 *
 * @author (Edwin Metcalf)
 * @version (2.0)
 */
public class Board
{
    int size;
    Piece[][] board;
    Move myLoc = new Move(0,0);
    Move[] enemies;

    Move treasureLoc;
    Move exitLoc;
    Piece collidedWith = new EmptyPiece();

    LinkedList<Move> enemiesMoves = new LinkedList<Move>();
    Player player = new Player();
    

    Board (int size) {
        this.board = new Piece[size][size];
        this.size = size;


        for(int r = 0; r < size; r++){
            for(int c = 0; c < size; c++){
                this.board[r][c] = new EmptyPiece();
            }
        }

    }

    boolean isValid(Move m) {
        if ((m.row >=0) && (m.row <= (size-1)) && (m.col >= 0) &&
            (m.col <= (size-1))) {return true;} else {return false;}
    }

    Move getRandomLoc() {
        int randNum1;
        int randNum2;
        int nSize = size - 1;
        randNum1 = (int)(nSize * Math.random() + 1);
        randNum2 = (int)(nSize * Math.random() + 1);
        boolean found = false;
        //System.out.println(randNum1 +" "+ randNum2);


        while (!found) {

            if (board[randNum1 - 1][randNum2 - 1].equals(new EmptyPiece())) { found = true; }
            else{ 
                randNum1 = (int)(nSize * Math.random() + 1);
                randNum2 = (int)(nSize * Math.random() + 1);
            }
        }

        Move move = new Move(randNum1,randNum2);
        return move;
    }

    public void setPlayer(Move loc) {
        this.player = new Player();
        myLoc.row = loc.row;
        myLoc.col = loc.col;

        //myLoc = loc;
        board[myLoc.row][myLoc.col] = player;

    }
    public void setEnemies(int num) {
        Enemy enemy = new Enemy();
        enemies = new Move[num];

        for (int i = 0; i < num; i++) {

            enemies[i] = getRandomLoc();
            board[enemies[i].row][enemies[i].col] = enemy;
            System.out.println(enemies[i]);
        }
    }

    public void setTreasure(int num) {
        for (int i = 0; i < num; i++) {
            Treasure treasure = new Treasure();
            treasureLoc = getRandomLoc();
            board[treasureLoc.row - 1][treasureLoc.col - 1] = treasure;
        }
    }
    
    public void setExit() {
        Exit exit = new Exit();
        exitLoc = getRandomLoc();
        board[exitLoc.row - 1][exitLoc.col - 1] = exit;
    }

    /** This is supoosed to move the player to the input if its a legal move on the board
     *
     *
     * @param deltaRow
     * @param deltaCol
     *
     */
    public String movePlayer(int deltaRow, int deltaCol){
        String playerReturn = "";
        int newRow = myLoc.row + deltaRow;
        int newCol = myLoc.col + deltaCol;
        Move newloc = new Move(newRow, newCol);

        if (!isValid(newloc)) {
            playerReturn = "That input is invalid try again";
            board[myLoc.row][myLoc.col] = player;
            //return playerReturn;
        } else {
            if (board[newRow][newCol] != new EmptyPiece()) {
                playerReturn = player.collide(board[newRow][newCol]);
            }
            board[myLoc.row][myLoc.col] = new EmptyPiece();
            board[newRow][newCol] = player;
            myLoc = newloc;
        }
        return playerReturn;

    }

    /**
     * this takes a move which is an enemies current location and then generates valid moves
     * for that enemy
     * @param curloc
     * @return
     */
    public Move getValidEnemyMove(Move curloc) {
        Enemy enemy = new Enemy();
        enemiesMoves.clear();
        if (isValid(new Move(curloc.row - 1,curloc.col)) && (!Objects.equals(enemy.collide(board[curloc.row - 1][curloc.col]), "Invalid"))) {
            enemiesMoves.add(new Move(curloc.row - 1, curloc.col));
        }
        if (isValid(new Move(curloc.row + 1,curloc.col)) && (!Objects.equals(enemy.collide(board[curloc.row + 1][curloc.col]), "Invalid"))) {
            enemiesMoves.add(new Move(curloc.row + 1, curloc.col));
        }
        if (isValid(new Move(curloc.row ,curloc.col - 1)) && (!Objects.equals(enemy.collide(board[curloc.row][curloc.col - 1]), "Invalid"))) {
            enemiesMoves.add(new Move(curloc.row, curloc.col - 1));
        }
        if (isValid(new Move(curloc.row,curloc.col + 1)) && (!Objects.equals(enemy.collide(board[curloc.row][curloc.col + 1]), "Invalid"))) {
            enemiesMoves.add(new Move(curloc.row, curloc.col + 1));
        }
        if (enemiesMoves.isEmpty()){
            return new Move(100,100);
        }
        int random = (int) (Math.random() * enemiesMoves.size());

        return enemiesMoves.get(random);
    }

    /**
     * this is supposed to move the enemies on the board to locations that were previously vetted by
     * the last method
     * @return
     */
    public String moveEnemies() {
        String enemyReturn = "";


        for (int i = 0; i < enemies.length; i++) {
            Move curloc = enemies[i];
            Move newloc = getValidEnemyMove(curloc);
            Enemy enemy = new Enemy();


            if (newloc.row == 100 && newloc.col == 100) {

            } else {
                if (!(board[newloc.row][newloc.col] instanceof EmptyPiece)) {
                    collidedWith = board[newloc.row][newloc.col];
                    enemyReturn = enemy.collide(collidedWith);
                }
                board[curloc.row][curloc.col] = new EmptyPiece();
                board[newloc.row][newloc.col] = enemy;
                enemies[i] = newloc;
                if (enemyReturn != null && enemyReturn.equals("you collided with the player its game over")) {
                    return enemyReturn;
                }
            }
        }
        return enemyReturn;


    }


    public String toString() {
        String print = "   ";

        for (int i = 0; i < size; i++){
            print += (i + 1) + " ";
        }
        print += "\n";

        print += "   ";

        for (int i = 0; i < size; i++){
            print += "__";
        }

        print += "\n";

        for (int r = 0; r < size; r++) {
            print += (char) ('A' + r) + " |";

            for (int c = 0; c < size; c++) {
                if (board[r][c].equals(new EmptyPiece())) {
                    print += "_ ";
                } else { print += board[r][c] + " ";}

                if (c == size - 1) { print += "|"; }
            }
            print += "\n";
        }
        print += "   ";

        for (int i = 0; i < size; i++) {
            print += "__";
        }
        return print;
    }



}
