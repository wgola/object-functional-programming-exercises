package lab14;

import java.time.LocalDate;

public final class Flat extends Property {

    private double flatNumber;
    private double floorNumber;

    public Flat(String street, int houseNumber, String city, String postalCode, double area, double price,
            LocalDate dueDate, int flatNumber, int floorNumber) {
        super(street, houseNumber, city, postalCode, area, price,
                dueDate);
        this.flatNumber = flatNumber;
        this.floorNumber = floorNumber;
    }

    public Flat() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + ", numer mieszkania: " + this.flatNumber + ", numer piętra: " + this.floorNumber;
    }

    public double getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(double flatNumber) {
        this.flatNumber = flatNumber;
    }

    public double getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(double floorNumber) {
        this.floorNumber = floorNumber;
    }
}
