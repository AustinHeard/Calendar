import java.util.*;
import java.time.*;
import java.time.format.TextStyle;

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
        drawOutline(tile);
    }
    
    private void drawOutline(Tile tile){
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
        
        float tileOffsetY = setTileOffset("y", tile);
        float tileOffsetX = setTileOffset("x", tile);

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
        rect(0, 0, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y,10,10,0,0);
    }

    private void drawMonth_NameofDays(Header header) {
        // Draw Month
        textFont(FONT, 50);
        fill(0);
        textAlign(CENTER,TOP);
        text(header.getMonth().getDisplayName(TextStyle.FULL,Locale.ENGLISH),0, 0, TILE_SIZE_X*TILE_ARR_LENGTH, TILE_SIZE_Y);

        // Draw Names of Days of Week
        textFont(FONT, 15);
        fill(0);
        textAlign(CENTER,CENTER);

        for(int i = 0; i < dayOfWeek.values().length;i++){
            text(header.getSunday().plus(i).getDisplayName(TextStyle.FULL,Locale.ENGLISH),i*TILE_SIZE_X, TILE_SIZE_Y * .75, TILE_SIZE_X, TILE_SIZE_Y*.25);


            stroke(152);
            strokeWeight(TILE_STROKE_WEIGHT);
            noFill();
            rect(i*TILE_SIZE_X, TILE_SIZE_Y * .75, TILE_SIZE_X, TILE_SIZE_Y*.25);
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
    //                          (year , month, day of month)
        localDate = localDate.of(2021 , month,      1      );
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
        return scalarX = width * .1;
    }
    
    public float setScalarY(){
        return scalarY = height * .1;
    }

}// Calendar