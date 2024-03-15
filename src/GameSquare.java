public class GameSquare {
    private String type;
    private boolean known;

    public GameSquare(String type, boolean known) {
        if(!type.equals("Breeze") && !type.equals("Empty") && !type.equals("Pit") && !type.equals("Start") && !type.equals("End")) 
            throw new IllegalArgumentException("Invalid game square type: " + type + "\nType must be equal to one of the following | 'Breeze' | 'Empty' | 'Pit' | 'Start' | 'End' |");
        this.type = type;
        this.known = known;
    }

    public String getType() {
        return this.type;
    }

    public boolean isKnown() {
        return this.known;
    }
}
