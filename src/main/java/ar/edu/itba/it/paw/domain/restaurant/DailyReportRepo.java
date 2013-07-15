package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Date;
import java.util.List;

public interface DailyReportRepo {

    /**
     * Returns a single DailyReport by its restaurant and date 
     */
    public DailyReport get(Restaurant r, Date d);
    
    /**
     * Returns all monthly reports for all restaurants
     */
    public List<MonthlyReport> getMonthlyReports();
	
}
