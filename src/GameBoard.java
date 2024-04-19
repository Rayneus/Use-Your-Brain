import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;

public class GameBoard extends JPanel{
    public GameSquare[][] board;
    private int size;
    private Player player;
    private List<List<String>> raw;
    // Hard code start and end positions
    public int start_x;
    public int start_y;
    public int end_x;
    public int end_y;

    public GameBoard(String dif) {
        try {
            if(dif.equals("easy"))
                raw = readCSV("resources/5x5.csv");
            else if(dif.equals("medium"))
                raw = readCSV("resources/6x6.csv");
            else
                raw = readCSV("resources/7x7.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.size = raw.size();
        this.board = new GameSquare[size][size];
        setLayout(new GridLayout(size, size));


        initialize();
    }

    private void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String type = this.raw.get(i).get(j);

                if (type.equals("Start")){
                    this.player = new Player(i, j, size);
                    this.start_x = i;
                    this.start_y = j;
                }
                else if (type.equals("End")) {
                    this.end_x = i;
                    this.end_y = j;
                }

                this.board[i][j] = new GameSquare(type);
                add(board[i][j]);
            }
        }
        this.board[start_x][start_y].updateIcon(Game.avatarIcon);
    }

    private List<List<String>> readCSV(String filename) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // removing UTF-8 BOM
                line = line.replace("\u00EF\u00BB\u00BF", "");
                line = line.replace("\uFEFF", "");
                
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        return records;
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

    public int getBoardSize() {
        return size;
    }

    public Player getPlayer() {
        return this.player;
    }
}
