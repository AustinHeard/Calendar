import java.time.*;

public class Tile {

    private DayOfWeek dayOfWeek;
    private int date;

    private int arrX;
    private int arrY;
    private int tileNumber;

    public Tile (int x, int y, DayOfWeek dayOfWeek, int firstDayOfMonth) {
        // x y position in array
        arrX = x;
        arrY = y;

        // date
        this.dayOfWeek = dayOfWeek;


        setTileNumber();
        setDate(firstDayOfMonth);
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setters ******************
// ----------------------------------------------------------------------------------------------------------------

    private void setTileNumber() {
        this.tileNumber = getX() + getY()*7; 
    }

    private void setDate(int firstDayOfMonth) {
        this.date = getTileNumber() - firstDayOfMonth + 1;
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Getters ******************
// ----------------------------------------------------------------------------------------------------------------

    public int getDate() {
        return this.date;
    }

    public int getTileNumber() {
        return this.tileNumber;
    }

    public int getX() {
        return arrX;
    }
    
    public int getY() {
        return arrY;
    }

}// Tile
