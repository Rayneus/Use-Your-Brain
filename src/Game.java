import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

import javax.swing.*;



public class Game {
    private static JFrame mainFrame;

    private static String endIcon = "resources/endSquare.png";
    public static String avatarIcon = "resources/avatar.png";
    private static String unknownIcon = "resources/unknownTile.png";
    private static String pitIcon = "resources/pitSquare.png";

    private static String difficulty;
    private static GameBoard myBoard;
    private static Player myPlayer;
    

    // Hard code start and end positions

    private static int startX;
    private static int startY;
    public static void main(String[] args) throws Exception {
        mainFrame = new JFrame();
        showStartScreen(mainFrame);
        
        // myBoard = new GameBoard(difficulty);

        // myPlayer = myBoard.getPlayer();
        mainFrame.setSize(1000, 1000);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

                if(myPlayer.getX() == myBoard.end_x && myPlayer.getY() == myBoard.end_y) {
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(endIcon);
                    System.out.println("Congratulations! You won!");
                    showEndScreen(startX, startY, mainFrame, myPlayer, myBoard);
                }
                else if (myBoard.board[myPlayer.getY()][myPlayer.getX()].getSquare().equals("Pit")){
                    myBoard.board[myPlayer.getY()][myPlayer.getX()].updateIcon(pitIcon);
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
        JLabel backgroundLabel = new JLabel(new ImageIcon("resources/Start Screen.png"));
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
        startScreen.add(backgroundLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);

        JButton TutorialButton = new JButton("Tutorial");  
        TutorialButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
        TutorialButton.setForeground(Color.WHITE);
        TutorialButton.setBackground(Color.BLACK);
        TutorialButton.setOpaque(true);
        TutorialButton.setBorderPainted(false);
        

        TutorialButton.addActionListener(e -> {
            JFrame tutorialScreen = new JFrame();
            tutorialScreen.setSize(1000, 1000);
            tutorialScreen.setLayout(new BorderLayout());
            tutorialScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Load the image
            ImageIcon backgroundImage = new ImageIcon("resources/tutorialScreen.png"); // Adjust the file path

            // Create a JLabel to display the image
            JLabel tutorialBackground = new JLabel(backgroundImage);
            tutorialBackground.setLayout(new BorderLayout());
            tutorialBackground.setHorizontalAlignment(JLabel.CENTER);
            
            JLabel tutorialLabel = new JLabel("<html><ol>"
                + "<li>Use the WASD keys to move the player to the end square</li>"
                + "<li>Where there is a Breeze there is a Pit nearby</li>"
                + "<li>You die if you fall into a pit</li>"
                + "<li>The end square is the goal</li>"
                + "</ol></html>");
            tutorialLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
            tutorialLabel.setForeground(Color.BLACK);
            tutorialLabel.setHorizontalAlignment(JLabel.CENTER);
            tutorialLabel.setBorder(BorderFactory.createEmptyBorder(150, 350, 150, 300));
            tutorialBackground.add(tutorialLabel, BorderLayout.CENTER);
            tutorialScreen.add(tutorialBackground, BorderLayout.CENTER);

            JButton backButton = new JButton("Back");
            backButton.setForeground(Color.WHITE);
            backButton.setBackground(Color.BLACK);
            backButton.setOpaque(true);
            backButton.setBorderPainted(false);
            backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            backButton.addActionListener(e2 -> {
            tutorialScreen.setVisible(false);
            startScreen.setVisible(true);
            });

            tutorialScreen.add(backButton, BorderLayout.SOUTH);
            tutorialScreen.isAlwaysOnTop();
            tutorialScreen.setVisible(true);
            startScreen.setVisible(false);

        });

        startButton.addActionListener(e -> {
            JFrame difficultyScreen = new JFrame();
            difficultyScreen.setSize(1000, 1000);
            difficultyScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ImageIcon backgroundImage = new ImageIcon("resources/difficultyScreen.png"); // Adjust the file path

            JLabel difficultyBackground = new JLabel(backgroundImage);
            difficultyBackground.setLayout(new BorderLayout());
            difficultyBackground.setHorizontalAlignment(JLabel.CENTER);

            JButton easyButton = new JButton("Easy");
            easyButton.setForeground(Color.WHITE);
            easyButton.setBackground(Color.BLACK);
            easyButton.setOpaque(true);
            easyButton.setBorderPainted(false);

            easyButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            easyButton.addActionListener(e2 -> {
                difficulty = "easy";
                myBoard = new GameBoard(difficulty);
                myPlayer = myBoard.getPlayer();
                mainFrame.add(myBoard, BorderLayout.CENTER);
                difficultyScreen.setVisible(false);
                mainFrame.setVisible(true);
            });

            JButton mediumButton = new JButton("Medium");
            mediumButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            mediumButton.setForeground(Color.WHITE);
            mediumButton.setBackground(Color.BLACK);
            mediumButton.setOpaque(true);
            mediumButton.setBorderPainted(false);

            mediumButton.addActionListener(e2 -> {
                difficulty = "medium";
                myBoard = new GameBoard(difficulty);
                mainFrame.add(myBoard, BorderLayout.CENTER);
                myPlayer = myBoard.getPlayer();
                difficultyScreen.setVisible(false);
                mainFrame.setVisible(true);
            });

            JButton hardButton = new JButton("Hard");
            hardButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            hardButton.setForeground(Color.WHITE);
            hardButton.setBackground(Color.BLACK);
            hardButton.setOpaque(true);
            hardButton.setBorderPainted(false);

            hardButton.addActionListener(e2 -> {
                difficulty = "hard";
                myBoard = new GameBoard(difficulty);
                mainFrame.add(myBoard, BorderLayout.CENTER);
                myPlayer = myBoard.getPlayer();
                difficultyScreen.setVisible(false);
                mainFrame.setVisible(true);
            });


            JButton backButton = new JButton("Back");
            backButton.setForeground(Color.WHITE);
            backButton.setBackground(Color.BLACK);
            backButton.setOpaque(true);
            backButton.setBorderPainted(false);

            backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            backButton.addActionListener(e2 -> {
                difficultyScreen.setVisible(false);
                startScreen.setVisible(true);
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.BLACK);
            buttonPanel.setLayout(new GridLayout(1, 2));

            buttonPanel.add(easyButton, BorderLayout.CENTER);
            buttonPanel.add(mediumButton, BorderLayout.CENTER);
            buttonPanel.add(hardButton, BorderLayout.CENTER);
            buttonPanel.add(backButton, BorderLayout.CENTER);
            difficultyScreen.add(difficultyBackground, BorderLayout.CENTER);
            difficultyScreen.add(buttonPanel, BorderLayout.SOUTH);

            difficultyScreen.isAlwaysOnTop();
            difficultyScreen.setVisible(true);
            startScreen.setVisible(false);

        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(startButton, BorderLayout.CENTER);
        buttonPanel.add(TutorialButton, BorderLayout.CENTER);
        startScreen.add(buttonPanel, BorderLayout.SOUTH);

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
        
        resultLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
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
        restartButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
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
        quitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
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