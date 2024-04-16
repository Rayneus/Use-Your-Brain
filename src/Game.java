import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;



public class Game {
    private static String endIcon = "resources/endSquare.png";
    public static String avatarIcon = "resources/avatar.png";
    private static String unknownIcon = "resources/unknownSquare.png";// Hard code start and end positions
    private static int startX = 0;
    private static int startY = 0;
    private static int endX = 4;
    private static int endY = 4;
    public static void main(String[] args) throws Exception {
        JFrame mainFrame = new JFrame();
        
        GameBoard myBoard = new GameBoard(5, startX, startY, endX, endY);
        Player myPlayer = myBoard.player;
        mainFrame.add(myBoard, BorderLayout.CENTER);
        mainFrame.setSize(1000, 1000);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        showStartScreen(mainFrame);
        
        mainFrame.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
        
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(unknownIcon);
                    myPlayer.moveUp();
                    myBoard.printBoard();
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(avatarIcon);

                }
                else if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(unknownIcon);
                    myPlayer.moveRight();
                    myBoard.printBoard();
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(avatarIcon);
                }
                else if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(unknownIcon);
                    myPlayer.moveDown();
                    myBoard.printBoard();
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(avatarIcon);
                }
                else if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(unknownIcon);
                    myPlayer.moveLeft();
                    myBoard.printBoard();
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(avatarIcon);
                }

                if(myPlayer.getX() == endX && myPlayer.getY() == endY) {
            
                    
                    myBoard.board[myPlayer.getX()][myPlayer.getY()].updateIcon(endIcon);
                    System.out.println("Congratulations! You won!");
                    showEndScreen(startX, startY, mainFrame, myPlayer, myBoard);
                }
            }
        });
    }

    private static void redisplayBoard(GameBoard myBoard) {
        int size = myBoard.getBoardSize();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                myBoard.board[y][x].updateIcon(unknownIcon);
            }
        }
        myBoard.board[startY][startX].updateIcon(avatarIcon);
    }

    private static void showStartScreen(JFrame mainFrame) {
        JFrame startScreen = new JFrame();
        startScreen.setSize(1000, 1000);
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
    }

    // Method to show the end screen
    private static void showEndScreen(int startX, int startY, JFrame mainFrame, Player myPlayer, GameBoard myBoard) {
        // Create the JFrame for the end screen
        JFrame endScreen = new JFrame();
        endScreen.setSize(250, 250);
        endScreen.setLayout(new BorderLayout());
        endScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        // Load the image
        ImageIcon backgroundImage = new ImageIcon("resources/EndScreenBackground.png"); // Adjust the file path

        // Create a JLabel to display the image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
        */

        //Create label for if the player wins
        JLabel loseLabel = new JLabel("You Lost :(");
        // Create label for if the player wins
        JLabel winLabel = new JLabel("YOU WON!!");

        JLabel resultLabel;

        
        if(myPlayer.getX() == myBoard.end_x && myPlayer.getY() == myBoard.end_y) {
            resultLabel = winLabel;
        } else {
            resultLabel = loseLabel;
        }
        
        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        // Panel for winLabel centered on top half of the screen
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(resultLabel, BorderLayout.CENTER);
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
            redisplayBoard(myBoard);
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