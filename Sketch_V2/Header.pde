import java.util.*;
import java.time.*;

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
