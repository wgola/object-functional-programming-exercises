package lab14;

import java.time.LocalDate;

public final class Flat extends Property {

    private int flatNumber;
    private int floorNumber;

    public Flat(String street, int houseNumber, String city, String postalCode, double area, int price,
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

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
