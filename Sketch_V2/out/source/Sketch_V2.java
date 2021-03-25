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
//
//  ****************** Setup && Draw ******************
// 
// ----------------------------------------------------------------------------------------------------------------


public Calendar calendar;

// Month of The Calendar
public Month month = Month.APRIL;



// ----------------------------------------------------------------------------------------------------------------
//  setup - runs one time before draw
// ----------------------------------------------------------------------------------------------------------------
public void setup() {
  
  surface.setResizable(true);
  frameRate(144);
  background(244);
  calendar = new Calendar(month);
}

// ----------------------------------------------------------------------------------------------------------------
//  draw - runs after setup and until the program is closed 
// ----------------------------------------------------------------------------------------------------------------
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
    private DayOfWeek dayOfWeek;
    private int firstDayOfMonth;

    // Scaling values
    private float scalarX;
    private float scalarY;


    public Calendar(Month month) {
        setTileSize();

        setMonth(month);
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
        setCornerRoundness(tile);
    }

    public void drawDate(Tile tile) {
        textFont(FONT, DATE_FONT_SIZE);
        fill(0);
        textAlign(RIGHT,TOP);
        
        if (firstDayOfMonth > tile.getTileNumber() || header.getMonth().length(localDate.isLeapYear()) < tile.getDate()) {
        text("",setTileOffset("x", tile), setTileOffset("y", tile), TILE_SIZE_X, TILE_SIZE_Y);
        } else {
        text(String.valueOf(tile.getDate()),setTileOffset("x", tile) - TILE_STROKE_WEIGHT, setTileOffset("y", tile), TILE_SIZE_X, TILE_SIZE_Y);
        }
    }

    public float setTileOffset(String str, Tile tile) {
        return (str.equals("x")) ? tile.getX()*TILE_SIZE_X: TILE_SIZE_Y + tile.getY()*TILE_SIZE_Y;
    }
    
    private void setCornerRoundness(Tile tile){
        if (tile.getX() == 6 && tile.getY() == 5)
            rect(setTileOffset("x", tile), setTileOffset("y", tile), TILE_SIZE_X, TILE_SIZE_Y,0,0,10,0);
        else if (tile.getX() == 0 && tile.getY() == 5)
            rect(setTileOffset("x", tile), setTileOffset("y", tile), TILE_SIZE_X, TILE_SIZE_Y,0,0,0,10);
        else
            rect(setTileOffset("x", tile), setTileOffset("y", tile), TILE_SIZE_X, TILE_SIZE_Y);
    }


    // Show Header ---------------------------------
    public void showHeader(Header header) {
        drawHeaderArea();
        drawMonth(header);
        drawDaysOfWeek(header);
    }

    private void drawHeaderArea() {
        stroke(152);
        strokeWeight(TILE_STROKE_WEIGHT);
        fill(244);
        rect(0, 0, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y,10,10,0,0);
    }

    private void drawMonth(Header header) {
        textFont(FONT, 50);
        fill(0);
        textAlign(CENTER,TOP);
        text(header.getMonth().getDisplayName(TextStyle.FULL,Locale.ENGLISH),0, 0, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y);
    }

    private void drawDaysOfWeek(Header header) {
        for(int i = 0; i < dayOfWeek.values().length;i++){
            textFont(FONT, 15);
            fill(0);
            textAlign(CENTER,CENTER);
            text(header.getSunday().plus(i).getDisplayName(TextStyle.FULL,Locale.ENGLISH),i*TILE_SIZE_X, TILE_SIZE_Y * .75f, TILE_SIZE_X, TILE_SIZE_Y*.25f);


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
//  ****************** Setters ******************
// ----------------------------------------------------------------------------------------------------------------

    private void setMonth(Month month) {
        this.month = month;
    }

    public void setLocalDate(){
    //                    year , month , day of month
        localDate = localDate.of(2021,month,1);
    }

    public void setFirstDayOfMonth(){
        firstDayOfMonth = localDate.getDayOfWeek().getValue();
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Scaling System ******************
// ----------------------------------------------------------------------------------------------------------------

    public void setTileSize(){
        TILE_SIZE_X = setScalarX();
        TILE_SIZE_Y = setScalarY();
    }

    public float setScalarX(){
        return scalarX = width * .1f;
    }
    
    public float setScalarY(){
        return scalarY = height * .1f;
    }
}// Calendar



public class Header {

    private Month month;
    private DayOfWeek sunday = DayOfWeek.SUNDAY;

    public Header (Month month) {
        setMonth(month);
    }



// ----------------------------------------------------------------------------------------------------------------
//  ****************** Getters ******************
// ----------------------------------------------------------------------------------------------------------------

    public DayOfWeek getSunday() {
        return sunday;
    }

    public Month getMonth() {
        return month;
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Setters ******************
// ----------------------------------------------------------------------------------------------------------------

    private void setMonth(Month month) {
        this.month = month;
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

    private void setTileNumber() {
        this.tileNumber = getX() + getY()*7; 
    }

    private void setDate(int firstDayOfMonth) {
        this.date = getTileNumber() - firstDayOfMonth + 1;
    }

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
