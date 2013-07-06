package ar.edu.itba.it.paw.web.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.web.HomePage;

public class SideBarPage extends BasePage {

    private static final long serialVersionUID = 5052449722147192913L;

    @SpringBean
    private FoodTypeRepo ftRepo;

    @SpringBean
    private RestaurantRepo restRepo;

    public SideBarPage(final FoodType selected) {

        super();

        // All restaurants

        int numRestaurants = restRepo.getAll().size();

        Link<Void> generalLink = new Link<Void>("linkAll") {

            private static final long serialVersionUID = 273349550644734223L;

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        };

        generalLink.add(new Label("all", String.valueOf(numRestaurants)));
        generalLink.add(new Label("pluralizeAll", pluralizeItem("Restaurant", numRestaurants)));

        if (selected == null) {
            generalLink.add(new AttributeAppender("class", new Model<String>("active")));
        }

        add(generalLink);

        // Food types

        add(new RefreshingView<FoodType>("foodtypes") {

            private static final long serialVersionUID = 7702722087881335104L;

            @Override
            protected Iterator<IModel<FoodType>> getItemModels() {
                List<IModel<FoodType>> result = new ArrayList<IModel<FoodType>>();
                for (FoodType ft : ftRepo.getAll()) {
                    if (ft.getAmmount() > 0)
                        result.add(new EntityModel<FoodType>(FoodType.class, ft));
                }
                return result.iterator();
            }

            @Override
            protected void populateItem(final Item<FoodType> item) {
                final FoodType ft = item.getModelObject();
                Link<FoodType> link = new Link<FoodType>("foodtype",
                        item.getModel()) {

                            private static final long serialVersionUID = 8515291019588887965L;

                    @Override
                    public void onClick() {
                        setResponsePage(new HomePage(ft));
                    }
                };
                link.add(new Label("name", item.getModel()));
                link.add(new Label("ammount",
                        new AbstractReadOnlyModel<Integer>() {

                            private static final long serialVersionUID = 4023409327769339113L;

                            @Override
                            public Integer getObject() {
                                return ft.getAmmount();
                            }
                        }));
                link.add(new Label("pluralizeRestaurants", pluralizeItem(
                        "Restaurant", ft.getAmmount())));
                item.add(link);
                if (item.getModelObject().equals(selected)) {
                    item.add(new AttributeAppender("class", new Model<String>(
                            "active"), " "));
                }
            }
        });
    }

    private String pluralizeItem(String item, int ammount) {

        if (ammount != 1) {
            item += "s";
        }

        return item;
    }

}
