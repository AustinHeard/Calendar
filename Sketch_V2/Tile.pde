import java.time.*;

public class Tile {

    private DayOfWeek dayOfWeek;
    private int date;

    private int arrX;
    private int arrY;
    private int tileNumber;

    public Tile (int x, int y, DayOfWeek dayOfWeek) {
        // x y position in array
        arrX = x;
        arrY = y;

        // date
        this.dayOfWeek = dayOfWeek;


        setTileNumber();
        setDate();
    }

    private void setTileNumber() {
        this.tileNumber = getX() + getY()*7; 
    }

    private void setDate() {
        this.date = getTileNumber() - firstDayOfMonth + 1;
    }



    public int getTileNumber() {
        return this.tileNumber;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

}// Tile
