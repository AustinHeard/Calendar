import java.time.*;
public class Calendar {
  
  // parts of calendar
  public Header header;
  public Tile[][] tileArr = new Tile[TILE_ARR_HEIGHT][TILE_ARR_LENGTH];
   
  public Calendar() {
    setLocalDate();
    setFirstDayOfMonth();
    createHeader();
    createTiles();
  }

  public void setLocalDate(){
    localDate = localDate.of(2021,month,1);
  }

  public void setFirstDayOfMonth(){
    firstDayOfMonth = localDate.getDayOfWeek().getValue();
  }

  public void createHeader(){
    header = new Header(month);
  }

  public void createTiles(){
    for (int y = 0; y < tileArr.length; ++y)
      for (int x = 0; x < tileArr[y].length; ++x)
        tileArr[y][x] = new Tile(x,y,dayOfWeek.SUNDAY.plus(x));
  }
}