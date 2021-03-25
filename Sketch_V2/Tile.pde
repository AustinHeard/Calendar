import java.time.*;

public class Tile {

  private final PFont FONT = createFont("Georgia", 64);
  private final int DATE_FONT_SIZE = 25;
  private final int TILE_STROKE_WEIGHT = 5;

  private DayOfWeek dayOfWeek;
  private int date;

  private int x;
  private int y;
  private int tileNumber;

  public Tile (int x, int y, DayOfWeek dayOfWeek) {
    // x y position in array
    this.x = x;
    this.y = y;

    // date
    this.dayOfWeek = dayOfWeek;


    setTileNumber();
    setDate();
  }

}// Tile
