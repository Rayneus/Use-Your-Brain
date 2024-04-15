import java.awt.*;
import javax.swing.*;

public class GameBoard extends JPanel{
    GameSquare[][] board;
    int size;
    Player player;

    public GameBoard(int size, int start_x, int start_y, int end_x, int end_y) {
        this.board = new GameSquare[size][size];
        this.size = size;
        this.player = new Player(start_x, start_y);

        setLayout(new GridLayout(size, size));
        
        board[start_x][start_y] = new GameSquare("Start", true);
        board[start_x][start_y].updateIcon(Game.avatar);
        board[end_x][end_y] = new GameSquare("End", false);

        initialize();
    }

    private void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] == null)
                    this.board[i][j] = new GameSquare("Empty", false);
                add(this.board[i][j]);
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.player.getX() == j && this.player.getY() == i){
                    System.out.print("Player|");
                    continue;
                }
                System.out.print(this.board[i][j].getSquare() + "|");
            }
            System.out.println();
        }
    }

    
}
