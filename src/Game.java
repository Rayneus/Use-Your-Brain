import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        GameBoard myBoard = new GameBoard(5, 0, 0, 4, 4);
        Player myPlayer = myBoard.player;
        Scanner myScanner = new Scanner(System.in);
        char input;

        while(true) {
            myBoard.printBoard();

            if(myPlayer.getX() == 4 && myPlayer.getY() == 4)
                break;

            System.out.println("Enter your move (WASD)");
            input = myScanner.next().charAt(0);

            if(input =='w')
                myPlayer.moveUp();
            else if(input == 'a')
                myPlayer.moveLeft();
            else if(input == 's')
                myPlayer.moveDown();
            else if(input == 'd')
                myPlayer.moveRight();
        }
        myScanner.close();
    }
}
