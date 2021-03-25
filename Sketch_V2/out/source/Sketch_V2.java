import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.time.*; 
import java.time.*; 
import java.util.*; 
import java.time.*; 
import java.time.format.TextStyle; 
import java.time.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Sketch_V2 extends PApplet {



// ----------------------------------------------------------------------------------------------------------------
//
//  ****************** Setup && Draw ******************
// 
// ----------------------------------------------------------------------------------------------------------------

public Calendar calendar;
// Dimensions
public final int TILE_ARR_LENGTH = 7;
public final int TILE_ARR_HEIGHT = 6;
public float TILE_SIZE_X;
public float TILE_SIZE_Y;


// parts of calendar
public Header header;
public Tile[][] tileArr = new Tile[TILE_ARR_HEIGHT][TILE_ARR_LENGTH];

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

public class Calendar {
// ----------------------------------------------------------------------------------------------------------------
//
//  ****************** Constructor ******************
// 
// ----------------------------------------------------------------------------------------------------------------



  public Calendar() {
    setTileSize();
    boardInitialization();
  }

// ----------------------------------------------------------------------------------------------------------------
//
//  ****************** Initialization ******************
// 
// ----------------------------------------------------------------------------------------------------------------



// ----------------------------------------------------------------------------------------------------------------
//  boardInitialization - draws checkerboard of calendar 
// ----------------------------------------------------------------------------------------------------------------
  public void boardInitialization(){
    setLocalDate();
    setFirstDayOfMonth();
    createHeader();
    createTiles();
  }
// ----------------------------------------------------------------------------------------------------------------
//  createHeader - creates the header of the calendar. Sets month and header image  
// ----------------------------------------------------------------------------------------------------------------
  public void createHeader(){
    header = new Header(month);
  }
// ----------------------------------------------------------------------------------------------------------------
//  createTiles - creates the tiles for ever day of the month and
//                lays them according to the days in the month
// ----------------------------------------------------------------------------------------------------------------
  public void createTiles(){
    for (int y = 0; y < tileArr.length; ++y)
      for (int x = 0; x < tileArr[y].length; ++x)
        tileArr[y][x] = new Tile(x,y,dayOfWeek.SUNDAY.plus(x));
  }
// ----------------------------------------------------------------------------------------------------------------
//  setFirstDayOfMonth - sets firstDayOfMonth to the numerical value of
//                       the day of the week that the month starts on
// ----------------------------------------------------------------------------------------------------------------
  public void setFirstDayOfMonth(){
    firstDayOfMonth = localDate.getDayOfWeek().getValue();
  }
// ----------------------------------------------------------------------------------------------------------------
//  setLocalDate - sets the localDate variable to the first of the correct month
// ----------------------------------------------------------------------------------------------------------------
  public void setLocalDate(){
    localDate = localDate.of(2021,month,1);
  }
// ----------------------------------------------------------------------------------------------------------------
//  setTileSize - sets the scale of the TILE_SIZE according to the width of the window
// ----------------------------------------------------------------------------------------------------------------
  public void setTileSize(){
    TILE_SIZE_X = setScalarX();
    TILE_SIZE_Y = setScalarY();
  }


// ----------------------------------------------------------------------------------------------------------------
//
//  ****************** Scaling System ******************
//
// ----------------------------------------------------------------------------------------------------------------

// ----------------------------------------------------------------------------------------------------------------
//  setScalarX - sets the scale of the X Scalar
// ----------------------------------------------------------------------------------------------------------------
  public float setScalarX(){
    return scalarX = width * .1f;
  }
// ----------------------------------------------------------------------------------------------------------------
//  setScalarY - sets the scale of the Y Scalar
// ----------------------------------------------------------------------------------------------------------------
  public float setScalarY(){
    return scalarY = height * .1f;
  }
}
// ----------------------------------------------------------------------------------------------------------------
//
//  ****************** Header ******************
// 
// ----------------------------------------------------------------------------------------------------------------



public class Header {
// ----------------------------------------------------------------------------------------------------------------
//  ****************** Global Variables ******************
// ----------------------------------------------------------------------------------------------------------------
  private final PFont FONT = createFont("Georgia", 64);
  private final int TILE_STROKE_WEIGHT = 5;
  private Month month;
  private DayOfWeek dayOfWeek;
  private int monthNumber;

// ----------------------------------------------------------------------------------------------------------------
//  Header - Constructor accepts args (int Length/Height,)
// ----------------------------------------------------------------------------------------------------------------
  public Header (Month month) {
    this.month = month;
  }



// ----------------------------------------------------------------------------------------------------------------
//  ****************** Do Stuffers ******************
// ----------------------------------------------------------------------------------------------------------------



// ----------------------------------------------------------------------------------------------------------------
//  show - Runs all of the drawing functions
// ----------------------------------------------------------------------------------------------------------------
  public void show() {
    drawHeaderArea();
    drawMonth();
    drawDaysOfWeek();
  }
// ----------------------------------------------------------------------------------------------------------------
//  drawHeaderArea - Draws area for the month and days to go in
// ----------------------------------------------------------------------------------------------------------------
  private void drawHeaderArea() {
    stroke(152);
    strokeWeight(TILE_STROKE_WEIGHT);
    fill(244);
    rect(0, 0, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y,10,10,0,0);
  }
// ----------------------------------------------------------------------------------------------------------------
//  drawMonth - Draws the Month in the correct spot on the screen
// ----------------------------------------------------------------------------------------------------------------
  private void drawMonth() {
    textFont(FONT, 50);
    fill(0);
    textAlign(CENTER,TOP);
    text(month.getDisplayName(TextStyle.FULL,Locale.ENGLISH),0, 0, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y);
  }
// ----------------------------------------------------------------------------------------------------------------
//  drawDaysOfWeek - Draws the days of the week in the correct spot
// ----------------------------------------------------------------------------------------------------------------
  private void drawDaysOfWeek() {
    for(int i = 0; i < dayOfWeek.values().length;i++){
      textFont(FONT, 15);
      fill(0);
      textAlign(CENTER,CENTER);
      text(dayOfWeek.SUNDAY.plus(i).getDisplayName(TextStyle.FULL,Locale.ENGLISH),i*TILE_SIZE_X, TILE_SIZE_Y * .75f, TILE_SIZE_X, TILE_SIZE_Y*.25f);
      
      
      stroke(152);
      strokeWeight(TILE_STROKE_WEIGHT);
      noFill();
      rect(i*TILE_SIZE_X, TILE_SIZE_Y * .75f, TILE_SIZE_X, TILE_SIZE_Y*.25f);
    }
  }


// ----------------------------------------------------------------------------------------------------------------
//  ****************** Getters ******************
// ----------------------------------------------------------------------------------------------------------------



// ----------------------------------------------------------------------------------------------------------------
//  getMonth - returns the month object
// ----------------------------------------------------------------------------------------------------------------
  public Month getMonth() {
    return month;
  }


// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setters ******************
// ----------------------------------------------------------------------------------------------------------------





}// Header
// ----------------------------------------------------------------------------------------------------------------
//
//  ****************** Tile ******************
// 
// ----------------------------------------------------------------------------------------------------------------

public class Tile {
// ----------------------------------------------------------------------------------------------------------------
//  ****************** Global Variables ******************
// ----------------------------------------------------------------------------------------------------------------
  private final PFont FONT = createFont("Georgia", 64);
  private final int DATE_FONT_SIZE = 25;
  private final int TILE_STROKE_WEIGHT = 5;


// date data
  private DayOfWeek dayOfWeek;
  private int date;

// position data
  private int x;
  private int y;
  private int tileNumber;

// ----------------------------------------------------------------------------------------------------------------
//  Tile - Constructor accepts args (int x position in array,int y position in array, DayOfWeek dayOfWeek)
// ----------------------------------------------------------------------------------------------------------------
  public Tile (int x, int y, DayOfWeek dayOfWeek) {
    // x y position in array
    this.x = x;
    this.y = y;

    // date
    this.dayOfWeek = dayOfWeek;


    setTileNumber();
    setDate();
  }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Do Stuffers ******************
// ----------------------------------------------------------------------------------------------------------------

// ----------------------------------------------------------------------------------------------------------------
//  show - Runs all of the drawing functions
// ----------------------------------------------------------------------------------------------------------------
  public void show() {
    drawTile();
    drawDate();
  }
// ----------------------------------------------------------------------------------------------------------------
//  drawTile - Draws the tile in the correct spot on the screen
// ----------------------------------------------------------------------------------------------------------------
  private void drawTile() {
    stroke(152);
    strokeWeight(TILE_STROKE_WEIGHT);
    fill(244);
    setCornerRoundness();
  }
// ----------------------------------------------------------------------------------------------------------------
//  drawDate - Draws the date in the correct spot on the screen and the correct date in the correct position
// ----------------------------------------------------------------------------------------------------------------
  private void drawDate() {
    textFont(FONT, DATE_FONT_SIZE);
    fill(0);
    textAlign(RIGHT,TOP);
    
    if (firstDayOfMonth > getTileNumber() || header.getMonth().length(localDate.isLeapYear()) < getDate()) {
      text("",getTileOffset("x"), getTileOffset("y"), TILE_SIZE_X, TILE_SIZE_Y);
    } else {
      text(String.valueOf(getDate()),getTileOffset("x") - TILE_STROKE_WEIGHT, getTileOffset("y"), TILE_SIZE_X, TILE_SIZE_Y);
    }
  
  }



// ----------------------------------------------------------------------------------------------------------------
//  ****************** Getters ******************
// ----------------------------------------------------------------------------------------------------------------

// ----------------------------------------------------------------------------------------------------------------
//  getX - returns value of X
// ----------------------------------------------------------------------------------------------------------------
  public int getX() {
    return x;
  }
// ----------------------------------------------------------------------------------------------------------------
//  getY - returns value of Y
// ----------------------------------------------------------------------------------------------------------------
  public int getY() {
    return y;
  }
// ----------------------------------------------------------------------------------------------------------------
//  getTileOffset - returns the offset of the tile
// ----------------------------------------------------------------------------------------------------------------
  public float getTileOffset(String str) {
    return (str.equals("x")) ? getX()*TILE_SIZE_X: TILE_SIZE_Y + getY()*TILE_SIZE_Y;
  }
// ----------------------------------------------------------------------------------------------------------------
//  getTileNumber - returns the Number of the tile
// ----------------------------------------------------------------------------------------------------------------
  public int getTileNumber() {
    return this.tileNumber;
  }
// ----------------------------------------------------------------------------------------------------------------
//  setDate - sets the  date
// ----------------------------------------------------------------------------------------------------------------
  public int getDate() {
    return this.date;
  }



// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setters ******************
// ----------------------------------------------------------------------------------------------------------------


// ----------------------------------------------------------------------------------------------------------------
//  setTileNumber - sets the Number of the tile
// ----------------------------------------------------------------------------------------------------------------
  private void setTileNumber() {
    this.tileNumber = getX() + getY()*7; 
  }
// ----------------------------------------------------------------------------------------------------------------
//  setCornerRoundness - sets the corners to round on the bottom of calendar
// ----------------------------------------------------------------------------------------------------------------
  private void setCornerRoundness(){
    if (getX() == 6 && getY() == 5)
      rect(getTileOffset("x"), getTileOffset("y"), TILE_SIZE_X, TILE_SIZE_Y,0,0,10,0);
    else if (getX() == 0 && getY() == 5)
      rect(getTileOffset("x"), getTileOffset("y"), TILE_SIZE_X, TILE_SIZE_Y,0,0,0,10);
    else
      rect(getTileOffset("x"), getTileOffset("y"), TILE_SIZE_X, TILE_SIZE_Y);
  }
// ----------------------------------------------------------------------------------------------------------------
//  setDate - sets the  date
// ----------------------------------------------------------------------------------------------------------------
  private void setDate() {
    this.date = getTileNumber() - firstDayOfMonth + 1;
  }


}// Tile
  public void settings() {  size(1000, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Sketch_V2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
