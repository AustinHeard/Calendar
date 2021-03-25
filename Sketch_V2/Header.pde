import java.util.*;
import java.time.*;
import java.time.format.TextStyle;

public class Header {

  private final PFont FONT = createFont("Georgia", 64);
  private final int TILE_STROKE_WEIGHT = 5;
  private Month month;
  private DayOfWeek dayOfWeek;
	private int monthNumber;

  public Header (Month month) {
    this.month = month;
  }

}// Header
