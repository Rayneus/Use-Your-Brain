import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
// import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception {
        JFrame mainFrame = new JFrame();
        GameBoard myBoard = new GameBoard(5, 0, 0, 4, 4);
        Player myPlayer = myBoard.player;
        // Scanner myScanner = new Scanner(System.in);
        // char input;

        JFrame startScreen = new JFrame();
        startScreen.setSize(800, 800);
        startScreen.setLayout(new BorderLayout());
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel startLabel = new JLabel("Use Your Brain!");
        startLabel.setFont(new Font("Arial", Font.BOLD, 30));
        startLabel.setHorizontalAlignment(JLabel.CENTER);
        startScreen.add(startLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(e -> {
            startScreen.setVisible(false);
            mainFrame.setVisible(true);
        });
        startScreen.add(startButton, BorderLayout.SOUTH);
        startScreen.isAlwaysOnTop();
        startScreen.setVisible(true);

        

        mainFrame.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
                    myPlayer.moveUp();
                    myBoard.printBoard();
                }
                else if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
                    myPlayer.moveRight();
                    myBoard.printBoard();
                }
                else if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
                    myPlayer.moveDown();
                    myBoard.printBoard();
                }
                else if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
                    myPlayer.moveLeft();
                    myBoard.printBoard();
                }
            }
        });

        mainFrame.add(myBoard, BorderLayout.CENTER);
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        while(true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(myPlayer.getX() + " " + myPlayer.getY());
            if(myPlayer.getX() == 4 && myPlayer.getY() == 4) {
                System.out.println("Congratulations! You won!");
                // return;
            }
        }
    }
}
