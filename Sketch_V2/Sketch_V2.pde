import java.time.*;

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Global Variables ******************
// ----------------------------------------------------------------------------------------------------------------

public Calendar calendar;

// Month/Year of The Calendar
public Month month = Month.APRIL;
public int year = 2020;


// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setup && Draw ******************
// ----------------------------------------------------------------------------------------------------------------

public void setup() {
  size(1000, 1000);
  surface.setTitle("Calendar");
  surface.setResizable(true);
  frameRate(144);
  background(244);
  calendar = new Calendar(month, year);
}

public void draw() {
  calendar.show();
  calendar.setTileSize();
}