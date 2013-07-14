package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Date;
import java.util.List;

public interface DailyReportRepo {


    /**
     * Returns a list of all reports for the last month for the restaurant
     */
    public List<DailyReport> getLastMonth(Restaurant r);

    /**
     * Returns a single DailyReport by its restaurant and date 
     */
    public DailyReport get(Restaurant r, Date d);
	
}
