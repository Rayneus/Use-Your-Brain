import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameSquare extends JLabel{
    private String square;
    private boolean known;

    public GameSquare(String type, boolean known) {
        if(!type.equals("Breeze") && !type.equals("Empty") && !type.equals("Pit") && !type.equals("Start") && !type.equals("End")) 
            throw new IllegalArgumentException("Invalid game square type: " + type + "\nType must be equal to one of the following | 'Breeze' | 'Empty' | 'Pit' | 'Start' | 'End' |");
        this.square = type;
        this.known = known;

        setIcon(new ImageIcon("resources/unknownSquare.png"));
    }

    public String getSquare() {
        return this.square;
    }

    public boolean isKnown() {
        return this.known;
    }

    public void makeKnown() {
        this.known = true;
    }

    public void makeUnknown() {
        this.known = false;
    }

    public void updateIcon(String name){
        setIcon(new ImageIcon(name));
    }
}
