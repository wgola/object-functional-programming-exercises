package lab14;

import java.time.LocalDate;

public abstract sealed class Property permits House, Flat {

    private String street;
    private int houseNumber;
    private String city;
    private String postalCode;
    private double area;
    private double price;
    private LocalDate dueDate;

    protected Property(String street, int houseNumber, String city, String postalCode, double area, double price,
            LocalDate dueDate) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.area = area;
        this.price = price;
        this.dueDate = dueDate;
    }

    protected Property() {
    }

    @Override
    public String toString() {
        return "Miasto: " + this.city + ", kod pocztowy: " + this.postalCode + ", ulica: " + this.street
                + ", numer domu: " + this.houseNumber + ", powierzchnia: " + this.area + "m^2, cena: " + this.price
                + "PLN, data obowiązywania oferty: " + this.dueDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
