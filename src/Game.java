import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
// import java.util.concurrent.TimeUnit;    //debugging

import javax.swing.*;
// import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception {
        JFrame mainFrame = new JFrame();

        // Hard code start and end positions
        int startX = 0;
        int startY = 0;
        int endX = 4;
        int endY = 4;
        GameBoard myBoard = new GameBoard(5, startX, startY, endX, endY);
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
        startButton.addActionListener(StartEvent -> {
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

                if(myPlayer.getX() == endX && myPlayer.getY() == endY) {
                    //end screen
                    showEndScreen(startX, startY, mainFrame, myPlayer, myBoard);
                }
            }
        });

        mainFrame.add(myBoard, BorderLayout.CENTER);
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*      // debugging
        while(true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(myPlayer.getX() + " " + myPlayer.getY());
            if(myPlayer.getX() == endX && myPlayer.getY() == endY) {
                System.out.println("Congratulations! You won!");
                // return;
            }
        }
        */
    }

        // Method to show the end screen
        private static void showEndScreen(int startX, int startY, JFrame mainFrame, Player myPlayer, GameBoard myBoard) {
            // Create the JFrame for the end screen
            JFrame endScreen = new JFrame();
            endScreen.setSize(250, 250);
            endScreen.setLayout(new BorderLayout());
            endScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            // Load the image
            ImageIcon backgroundImage = new ImageIcon("resources/EndScreenBackground.png"); // Adjust the file path
    
            // Create a JLabel to display the image
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setLayout(new BorderLayout());
            backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
    
            // Create label if the player wins
            JLabel winLabel = new JLabel("YOU WON!!");
            winLabel.setFont(new Font("Arial", Font.BOLD, 15));
            winLabel.setHorizontalAlignment(JLabel.CENTER);
    
            // Panel for winLabel centered on top half of the screen
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.add(winLabel, BorderLayout.CENTER);
            topPanel.setPreferredSize(new Dimension(250, 125)); // Adjust height as needed
    
            // Panel for both buttons stacked vertically
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
            // Create restart button
            JButton restartButton = new JButton("Restart");
            restartButton.setFont(new Font("Arial", Font.BOLD, 15));
            restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            restartButton.addActionListener(e -> {
                endScreen.setVisible(false);
                myPlayer.SetPostion(startX, startY);
                myBoard.printBoard();
                mainFrame.setVisible(true);
            });
            buttonPanel.add(restartButton);
    
            // Create Quit button
            JButton quitButton = new JButton("Quit");
            quitButton.setFont(new Font("Arial", Font.BOLD, 15));
            quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            quitButton.addActionListener(e -> {
                System.exit(0); // Quit the application
            });
            buttonPanel.add(quitButton);
    
            // Add components to the end screen
            endScreen.add(topPanel, BorderLayout.NORTH);
            endScreen.add(buttonPanel, BorderLayout.SOUTH);
            endScreen.setLocationRelativeTo(null); // Center the end screen on the screen
            endScreen.setVisible(true);
        }
}
