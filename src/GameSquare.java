import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameSquare extends JLabel{
    private String square;

    public GameSquare(String type) {
        if(!type.equals("Breeze") && !type.equals("Empty") && !type.equals("Pit") && !type.equals("Start") && !type.equals("End")) 
            throw new IllegalArgumentException("Invalid game square type: " + type + "\nType must be equal to one of the following | 'Breeze' | 'Empty' | 'Pit' | 'Start' | 'End' |");
        this.square = type;

        setIcon(new ImageIcon("resources/unknownTile.png"));
    }

    public String getSquare() {
        return this.square;
    }

    public void updateIcon(String name){
        setIcon(new ImageIcon(name));
    }
}
