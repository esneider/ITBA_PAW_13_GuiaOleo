package ar.edu.itba.it.paw.web.restaurant;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantState;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;

public class RegisterRestaurantPage extends NoSideBarPage {

	private static final long serialVersionUID = 471629832655073132L;

	@SpringBean
	private RestaurantRepo restRepo;
	
	@SpringBean
	private FoodTypeRepo ftRepo;

	private transient String name;
	private transient String address;
	private transient String area;
	private transient Float avgprice;
	private transient String website;
	private transient String timerange;
	private transient String telephone;
	private transient List<FoodType> foodtypes;

	public RegisterRestaurantPage() {
		super(true);

		add(new FeedbackPanel("restaurantFeedback"));

		Form<RegisterRestaurantPage> form = new Form<RegisterRestaurantPage>(
				"restaurantForm",
				new CompoundPropertyModel<RegisterRestaurantPage>(this)) {

			private static final long serialVersionUID = 1455744470158143935L;

			@Override
			protected void onSubmit() {

				name = Utils.normalizeString(name);
				address = Utils.normalizeString(address);
				area = Utils.normalizeString(area);
				website = Utils.normalizeString(website);
				timerange = Utils.normalizeString(timerange);
				telephone = Utils.normalizeString(telephone);

				if (!hasError()) {
					Restaurant rest = new Restaurant(name, address, area,
							telephone, website, timerange, avgprice,
							RestaurantState.Pending, new HashSet<FoodType>(foodtypes),
							RestaurantWicketSession.get().getUser(), new Date());
					
					restRepo.save(rest);
					setResponsePage(getApplication().getHomePage());
				}
				
			}
		};
		
		form.add(new TextField<String>("name").setRequired(true));
		form.add(new TextField<String>("address").setRequired(true));
		form.add(new TextField<String>("area").setRequired(true));
		form.add(new TextField<String>("telephone").setRequired(true));
		form.add(new TextField<String>("website").setRequired(true));
		form.add(new TextField<String>("timerange").setRequired(true));
		form.add(new TextField<String>("avgprice").setRequired(true));
		
		IModel<List<FoodType>> foodTypesModel = new LoadableDetachableModel<List<FoodType>>() {

			private static final long serialVersionUID = 1824811483108400829L;

			@Override
			protected List<FoodType> load() {
				return ftRepo.getAll();
			}
		};
		
		form.add(new ListMultipleChoice<FoodType>("foodtypes", foodTypesModel)
				.setChoiceRenderer(new ChoiceRenderer<FoodType>("name", "id")));
		
		add(form);
	}
	
}
