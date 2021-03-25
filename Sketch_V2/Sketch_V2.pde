import java.time.*;

// ----------------------------------------------------------------------------------------------------------------
//
//  ****************** Setup && Draw ******************
// 
// ----------------------------------------------------------------------------------------------------------------

// Dimensions
public final int TILE_ARR_LENGTH = 7;
public final int TILE_ARR_HEIGHT = 6;
public float TILE_SIZE_X;
public float TILE_SIZE_Y;


public Calendar calendar;

// date data
public LocalDate localDate;
public Month month = Month.APRIL;
public DayOfWeek dayOfWeek;
public int firstDayOfMonth;

// Scaling data
public float scalarX;
public float scalarY;


// ----------------------------------------------------------------------------------------------------------------
//  setup - runs one time before draw
// ----------------------------------------------------------------------------------------------------------------
public void setup() {
  size(1000, 1000);
  surface.setResizable(true);
  frameRate(144);
  background(244);
  calendar = new Calendar();
}

// ----------------------------------------------------------------------------------------------------------------
//  draw - runs after setup and until the program is closed 
// ----------------------------------------------------------------------------------------------------------------
public void draw() {
  showTileArr();
  header.show();


  calendar.show();
  calendar.setTileSize();
}


// ----------------------------------------------------------------------------------------------------------------
//  showTileArr - paints the tiles on the screen
// ----------------------------------------------------------------------------------------------------------------
public void showTileArr(){
  // println("------------------------------------------------");
  for (int y = 0; y < TILE_ARR_HEIGHT; ++y){
    for (int x = 0; x < TILE_ARR_LENGTH; ++x){
      // print("X: " + x + " " + "Y: " + y +" ");
      tileArr[y][x].show();
      // print("Tile Number: " + tileArr[y][x].getTileNumber());
    }
    // println("");
  }
}