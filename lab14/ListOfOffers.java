package lab14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListOfOffers {

    private List<Property> listOfOffers = new ArrayList<>();

    public ListOfOffers() {
    }

    public void addSaleOffer(Property offer) {
        this.listOfOffers.add(offer);
    }

    public List<Property> getAllOffers(Predicate<Property> filter) {
        List<Property> filteredProperties = new ArrayList<>();

        for (Property property : this.listOfOffers) {
            if (filter.test(property))
                filteredProperties.add(property);
        }

        return filteredProperties;
    }
}
