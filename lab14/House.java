package lab14;

import java.time.LocalDate;

public final class House extends Property {

    private double landArea;

    public House(String street, int houseNumber, String city, String postalCode, double area, double price,
            LocalDate dueDate, double landArea) {
        super(street, houseNumber, city, postalCode, area, price,
                dueDate);
        this.landArea = landArea;
    }

    public House() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + ", powierzchnia działki: " + this.landArea + "m^2";
    }

    public double getLandArea() {
        return landArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }
}
