package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.restaurant.DailyReportRepo;
import ar.edu.itba.it.paw.domain.restaurant.MonthlyReport;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;
import ar.edu.itba.it.paw.web.common.HighlightedRestaurantLink;
import ar.edu.itba.it.paw.web.restaurant.RestaurantListPage;
import ar.edu.itba.it.paw.web.restaurant.RestaurantViewPage;

public class MonthlyReportsPage extends NoSideBarPage {

	@SpringBean
	private DailyReportRepo reportRepo;

	public MonthlyReportsPage() {
		super(true);

		RestaurantWicketSession session = getRestaurantWicketSession();

		if (!session.getUser().isAdmin()) {
			setResponsePage(RestaurantListPage.class);
			return;
		}

		IModel<List<MonthlyReport>> reportList = new LoadableDetachableModel<List<MonthlyReport>>() {

			@Override
			protected List<MonthlyReport> load() {
				return reportRepo.getMonthlyReports();
			}

		};

		add(new PropertyListView<MonthlyReport>("reports", reportList) {

			@Override
			protected void populateItem(ListItem<MonthlyReport> item) {
				final IModel<Restaurant> restaurantModel = new EntityModel<Restaurant>(
						Restaurant.class, item.getModelObject().getRestaurant());
				Link<Restaurant> restaurantLink = new HighlightedRestaurantLink<Restaurant>(
						"rlink", restaurantModel) {

					private static final long serialVersionUID = 3046179147235866459L;

					@Override
					public void onClick() {
						setResponsePage(new RestaurantViewPage(restaurantModel,
								false));
					}

				};

				restaurantLink.setDefaultModel(restaurantModel);
				restaurantLink.add(new Label("restaurant.name"));

				item.add(restaurantLink);

				item.add(new Label("shows", item.getModelObject().getShows()));
				item.add(new Label("clicks", item.getModelObject().getClicks()));
			}

		});

		// add(new RefreshingView<MonthlyReport>("reports") {
		//
		// @Override
		// protected Iterator<IModel<MonthlyReport>> getItemModels() {
		// List<IModel<MonthlyReport>> monthlyModelList = new
		// ArrayList<IModel<MonthlyReport>>();
		// for (MonthlyReport report : reportRepo.getMonthlyReports()) {
		// monthlyModelList.add(new EntityModel<MonthlyReport>(
		// MonthlyReport.class, report));
		// }
		// return monthlyModelList.iterator();
		// }
		//
		// @Override
		// protected void populateItem(Item<MonthlyReport> item) {
		// final IModel<Restaurant> restaurantModel = new
		// EntityModel<Restaurant>(
		// Restaurant.class, item.getModelObject().getRestaurant());
		// Link<Restaurant> restaurantLink = new
		// HighlightedRestaurantLink<Restaurant>("rlink", restaurantModel) {
		//
		// private static final long serialVersionUID = 3046179147235866459L;
		//
		// @Override
		// public void onClick() {
		// setResponsePage(new RestaurantViewPage(restaurantModel));
		// }
		//
		// };
		//
		// restaurantLink.setDefaultModel(restaurantModel);
		// restaurantLink.add(new Label("restaurant.name"));
		//
		// add(restaurantLink);
		//
		// item.add(new Label("shows", item.getModelObject().getShows()));
		// item.add(new Label("clicks", item.getModelObject().getClicks()));
		//
		// }
		//
		// });

	}
}
