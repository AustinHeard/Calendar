import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.time.*; 
import java.util.*; 
import java.time.*; 
import java.time.format.TextStyle; 
import java.util.*; 
import java.time.*; 
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
//  ****************** Global Variables ******************
// ----------------------------------------------------------------------------------------------------------------

public Calendar calendar;

// Month/Year of The Calendar
public Month month = Month.APRIL;
public int year = Year.now().getValue();


// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setup && Draw ******************
// ----------------------------------------------------------------------------------------------------------------

public void setup() {
  
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




public class Calendar {

    private final PFont FONT = createFont("Georgia", 64);
    private final int DATE_FONT_SIZE = 25;

    // Global Tile Dimensions
    public final int TILE_ARR_LENGTH = 7;
    public final int TILE_ARR_HEIGHT = 6;
    public final int TILE_STROKE_WEIGHT = 5;
    public float TILE_SIZE_X;
    public float TILE_SIZE_Y;


    // parts of calendar
    private Header header;
    private Tile[][] tileArr = new Tile[TILE_ARR_HEIGHT][TILE_ARR_LENGTH];


    // calendar data
    private LocalDate localDate;
    private Month month;
    private int year;
    private DayOfWeek dayOfWeek;
    private int firstDayOfMonth;

    // Scaling values
    private float scalarX;
    private float scalarY;


    public Calendar(Month month, int year) {
        setTileSize();

        setMonth(month);
        setYear(year);
        setLocalDate();
        setFirstDayOfMonth();

        createHeader();
        createTiles();
    }

    public void show() {
        setTileSize();
        showHeader(header);
        showTileArr();
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Initialization ******************
// ----------------------------------------------------------------------------------------------------------------

    public void createHeader() {
        header = new Header(month);
    }

    public void createTiles() {
        for (int y = 0; y < tileArr.length; ++y)
            for (int x = 0; x < tileArr[y].length; ++x)
                tileArr[y][x] = new Tile(x,y,dayOfWeek.SUNDAY.plus(x),firstDayOfMonth);
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Show/Draw ******************
// ----------------------------------------------------------------------------------------------------------------

    // Show Tiles ---------------------------------
    public void showTileArr() {
        for (int y = 0; y < TILE_ARR_HEIGHT; ++y) {
            for (int x = 0; x < TILE_ARR_LENGTH; ++x) {
                drawTile(tileArr[y][x]);
                drawDate(tileArr[y][x]);
            }
        }
    }

    public void drawTile(Tile tile) {
        stroke(152);
        strokeWeight(TILE_STROKE_WEIGHT);
        fill(244);
        drawOutline(tile);
    }
    
    private void drawOutline(Tile tile){
        float tileOffsetX = setTileOffset("x", tile) + TILE_SIZE_X;
        float tileOffsetY = setTileOffset("y", tile) + TILE_SIZE_Y;

        if (tile.getX() == 6 && tile.getY() == 5)
            rect(tileOffsetX, tileOffsetY, TILE_SIZE_X, TILE_SIZE_Y,0,0,10,0);
        else if (tile.getX() == 0 && tile.getY() == 5)
            rect(tileOffsetX, tileOffsetY, TILE_SIZE_X, TILE_SIZE_Y,0,0,0,10);
        else
            rect(tileOffsetX, tileOffsetY, TILE_SIZE_X, TILE_SIZE_Y);
    }

    public void drawDate(Tile tile) {
        textFont(FONT, DATE_FONT_SIZE);
        fill(0);
        textAlign(RIGHT,TOP);
        
        float tileOffsetX = setTileOffset("x", tile) + TILE_SIZE_X;
        float tileOffsetY = setTileOffset("y", tile) + TILE_SIZE_Y;

        if (firstDayOfMonth > tile.getTileNumber() || header.getMonth().length(localDate.isLeapYear()) < tile.getDate()) {
        text("",tileOffsetX, tileOffsetY, TILE_SIZE_X, TILE_SIZE_Y);
        } else {
        text(String.valueOf(tile.getDate()),tileOffsetX - TILE_STROKE_WEIGHT, tileOffsetY, TILE_SIZE_X, TILE_SIZE_Y);
        }
    }

    public float setTileOffset(String str, Tile tile) {
        return (str.equals("x")) ? tile.getX()*TILE_SIZE_X: TILE_SIZE_Y + tile.getY()*TILE_SIZE_Y;
    }


    // Show Header ---------------------------------
    public void showHeader(Header header) {
        drawHeaderArea();
        drawMonth_NameofDays(header);
    }

    private void drawHeaderArea() {
        stroke(152);
        strokeWeight(TILE_STROKE_WEIGHT);
        fill(244);
        rect(TILE_SIZE_X, TILE_SIZE_Y, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y,10,10,0,0);
    }

    private void drawMonth_NameofDays(Header header) {
        // Draw Month
        textFont(FONT, 50);
        fill(0);
        textAlign(CENTER,TOP);
        text(header.getMonth().getDisplayName(TextStyle.FULL,Locale.ENGLISH),TILE_SIZE_X, TILE_SIZE_Y, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y);

        // Draw Names of Days of Week
        textFont(FONT, 15);
        fill(0);
        textAlign(CENTER,CENTER);

        for(int i = 0; i < dayOfWeek.values().length;i++){
            text(DayOfWeek.SUNDAY.plus(i).getDisplayName(TextStyle.FULL,Locale.ENGLISH),i*TILE_SIZE_X + TILE_SIZE_X, TILE_SIZE_Y * .75f + TILE_SIZE_Y, TILE_SIZE_X, TILE_SIZE_Y*.25f);


            stroke(152);
            strokeWeight(TILE_STROKE_WEIGHT);
            noFill();
            rect(i*TILE_SIZE_X + TILE_SIZE_X, TILE_SIZE_Y * .75f + TILE_SIZE_Y, TILE_SIZE_X, TILE_SIZE_Y*.25f);
        }
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Getters ******************
// ----------------------------------------------------------------------------------------------------------------



// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setters ******************
// ----------------------------------------------------------------------------------------------------------------

    private void setMonth(Month month) {
        this.month = month;
    }
    
    private void setYear(int year) {
        this.year = year;
    }

    public void setLocalDate(){
    //                          (year , month, day of month)
        localDate = localDate.of(year , month,      1      );
    }

    public void setFirstDayOfMonth(){
        firstDayOfMonth = localDate.getDayOfWeek().getValue();
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Scaling System ******************
// ----------------------------------------------------------------------------------------------------------------

    public void setTileSize(){
        setScalarX();
        setScalarY();
    }

    public void setScalarX(){
        TILE_SIZE_X = width * .11f;
    }
    
    public void setScalarY(){
        TILE_SIZE_Y = height * .11f;
    }

}// Calendar



public class Header {

    private Month month;

    public Header (Month month) {
        setMonth(month);
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setters ******************
// ----------------------------------------------------------------------------------------------------------------

    private void setMonth(Month month) {
        this.month = month;
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Getters ******************
// ----------------------------------------------------------------------------------------------------------------

    public Month getMonth() {
        return month;
    }

}// Header


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
