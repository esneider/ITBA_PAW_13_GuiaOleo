package ar.edu.itba.it.paw.domain.restaurant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;

@Repository
public class HibernateDailyReportRepo extends AbstractHibernateRepo
		implements
			DailyReportRepo {

	@Autowired
	public HibernateDailyReportRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public DailyReport get(Restaurant r, Date d) {
		List<DailyReport> list = find(
				"from DailyReport where restaurant_id = ? and date(date) = current_date()",
				r.getId());
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<MonthlyReport> getMonthlyReports() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -30);
		Date d = c.getTime();

		List<DailyReport> dailyList = find("from DailyReport where date > ?", d);
		Map<Restaurant, MonthlyReport> monthlyMap = new HashMap<Restaurant, MonthlyReport>();

		for (DailyReport dr : dailyList) {
			if (!monthlyMap.containsKey(dr.getRestaurant())) {
				monthlyMap.put(
						dr.getRestaurant(),
						new MonthlyReport(dr.getRestaurant(), dr
								.getHighlightClicks(), dr.getHighlightShows()));
			}
			MonthlyReport mr = monthlyMap.get(dr.getRestaurant());
			mr.addShows(dr.getHighlightShows());
			mr.addClicks(dr.getHighlightClicks());
		}
		
		return new ArrayList<MonthlyReport>(monthlyMap.values());
	}
}
