import java.time.*;
public class Calendar {

    // Global Tile Dimensions
    public final int TILE_ARR_LENGTH = 7;
    public final int TILE_ARR_HEIGHT = 6;
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

    public void show(){
        setTileSize();
        header.show();
        showTileArr();
    }

// ----------------------------------------------------------------------------------------------------------------
//  ****************** Initialization ******************
// ----------------------------------------------------------------------------------------------------------------

    public void createHeader(){
        header = new Header(month);
    }

    public void createTiles(){
        for (int y = 0; y < tileArr.length; ++y)
            for (int x = 0; x < tileArr[y].length; ++x)
                tileArr[y][x] = new Tile(x,y,dayOfWeek.SUNDAY.plus(x));
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
        return scalarX = width * .1;
    }
    
    public float setScalarY(){
        return scalarY = height * .1;
    }
}// Calendar