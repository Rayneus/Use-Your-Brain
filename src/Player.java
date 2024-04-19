public class Player {
    private int x_pos;
    private int y_pos;
    private int limit;

    public Player(int start_x, int start_y, int size) {
        this.x_pos = start_x;
        this.y_pos = start_y;
        this.limit = size-1;
    }

    public int getX() {
        return this.x_pos;
    }

    public int getY() {
        return this.y_pos;
    }


    public void moveUp() {
        if(this.y_pos <= 0){}
        else {
            this.y_pos -= 1;
        }
    }

    public void moveDown() {
        if(this.y_pos >= limit){}
        else {
        this.y_pos += 1;
        }
    }

    public void moveRight() {
        if(this.x_pos >= limit){}
        else {
        this.x_pos += 1;
        }
    }

    public void moveLeft() {
        if(this.x_pos <= 0){}
        else {
        this.x_pos -= 1;
        }
    }

    public void SetPostion( int x, int y) {
        this.x_pos = x;
        this.y_pos = y;
    }

}
