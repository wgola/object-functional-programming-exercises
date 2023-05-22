package lab14;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    static Scanner numbersScanner = new Scanner(System.in);
    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("""
                Program służący do dodawania oraz wyszukiwania ofert sprzedaży domów oraz mieszkań.""");
        ListOfOffers listOfOffers = new ListOfOffers();

        insertData(listOfOffers);

        boolean run = true;
        while (run) {
            int option = chooseOption();

            switch (option) {
                case 1 -> addHouseOffer(listOfOffers);
                case 2 -> addFlatOffer(listOfOffers);
                case 3 -> showCurrentHouseOffers(listOfOffers);
                case 4 -> showCurrentFlatOffers(listOfOffers);
                case 5 -> showFilteredHouses(listOfOffers);
                case 6 -> showFilteredFlats(listOfOffers);
                case 7 -> {
                    System.out.println("Kończenie działania programu.");
                    run = false;
                }
                default -> System.out.println("Wybrano niepoprawną opcję!");
            }
        }
    }

    private static void insertData(ListOfOffers listOfOffers) {
        listOfOffers.addSaleOffer(
                new House("Jana Kazimierza", 5, "Gdańsk", "12-345", 123.5, 100000, LocalDate.of(2023, 5, 20), 15));
        listOfOffers.addSaleOffer(
                new House("Jana Kazimierza", 6, "Gdańsk", "23-456", 234.5, 123456, LocalDate.of(2023, 5, 30), 100));
        listOfOffers.addSaleOffer(
                new Flat("Jana Kazimierza", 5, "Gdańsk", "12-345", 123.5, 100000, LocalDate.of(2023, 5, 20), 10, 6));
        listOfOffers.addSaleOffer(
                new Flat("Jana Kazimierza", 5, "Gdańsk", "12-345", 123.5, 100000, LocalDate.of(2025, 5, 20), 6, 10));
    }

    static int chooseOption() {
        System.out.println(
                """
                        Wybierz opcję:
                        1) dodanie oferty sprzedaży domu
                        2) dodanie oferty sprzedaży mieszkania
                        3) wyświetlenie aktualnych ofert sprzedaży domów
                        4) wyświetlenie aktualnych ofert sprzedaży mieszkań
                        5) wyświetlenie wszystkich aktualnych ofert sprzedaży domów, w podanej miejscowości, o powierzchni nie mniejszej niż podana wartość
                        6) wyświetlenie wszystkich aktualnych ofert sprzedaży mieszkań, w podanej miejscowości, nie droższych niż podana wartość i od podanego piętra wzwyż
                        7) Wyjście z programu""");

        int option = numbersScanner.nextInt();

        return option;
    }

    private static void addHouseOffer(ListOfOffers listOfOffers) {
        House newHouse = new House();

        addProperty(newHouse);

        double landArea = getLandArea();

        newHouse.setLandArea(landArea);

        listOfOffers.addSaleOffer(newHouse);
        System.out.println("Dodano ofertę sprzedaży domu.");
    }

    private static void addFlatOffer(ListOfOffers listOfOffers) {
        Flat newFlat = new Flat();

        addProperty(newFlat);

        int flatNumber = getFlatNumber();

        newFlat.setFlatNumber(flatNumber);

        int floorNumber = getFloorNumber();

        newFlat.setFloorNumber(floorNumber);

        listOfOffers.addSaleOffer(newFlat);
        System.out.println("Dodano ofertę sprzedaży mieszkania.");
    }

    private static void showCurrentHouseOffers(ListOfOffers listOfOffers) {
        LocalDate currentDate = LocalDate.now();
        Predicate<Property> filter = p -> p instanceof House h
                && (h.getDueDate().equals(currentDate) || h.getDueDate().isAfter(currentDate));

        List<Property> filteredHouses = listOfOffers.getAllOffers(filter);

        showOffers(filteredHouses, "Brak aktualnych ofert sprzedaży domów!");
    }

    private static void showCurrentFlatOffers(ListOfOffers listOfOffers) {
        LocalDate currentDate = LocalDate.now();
        Predicate<Property> filter = p -> p instanceof Flat f
                && (f.getDueDate().equals(currentDate) || f.getDueDate().isAfter(currentDate));

        List<Property> filteredFlats = listOfOffers.getAllOffers(filter);

        showOffers(filteredFlats, "Brak aktualnych ofert sprzedaży mieszkań!");
    }

    private static void showFilteredHouses(ListOfOffers listOfOffers) {
        LocalDate currentDate = LocalDate.now();

        String city = getCity();
        double area = getArea();

        Predicate<Property> filter = p -> p instanceof House h
                && (h.getDueDate().equals(currentDate) || h.getDueDate().isAfter(currentDate))
                && h.getCity().equals(city) && h.getArea() >= area;

        List<Property> filteredHouses = listOfOffers.getAllOffers(filter);

        showOffers(filteredHouses, "Brak aktualnych ofert sprzedaży domów!");
    }

    private static void showFilteredFlats(ListOfOffers listOfOffers) {
        LocalDate currentDate = LocalDate.now();

        String city = getCity();
        double price = getPrice();
        int floorNumber = getFloorNumber();

        Predicate<Property> filter = p -> p instanceof Flat f
                && (f.getDueDate().equals(currentDate) || f.getDueDate().isAfter(currentDate))
                && f.getCity().equals(city) && f.getPrice() <= price && f.getFloorNumber() >= floorNumber;

        List<Property> filteredFlats = listOfOffers.getAllOffers(filter);

        showOffers(filteredFlats, "Brak aktualnych ofert sprzedaży mieszkań!");
    }

    private static void showOffers(List<Property> listOfOffers, String errorMessage) {
        if (listOfOffers.size() == 0) {
            System.out.println(errorMessage);
        } else {
            for (int i = 0; i < listOfOffers.size(); i++) {
                System.out.println(i + 1 + ". " + listOfOffers.get(i));
            }
        }
    }

    private static void addProperty(Property property) {
        String street = getStreet();

        property.setStreet(street);

        int houseNumber = getHouseNumber();

        property.setHouseNumber(houseNumber);

        String city = getCity();

        property.setCity(city);

        String postalCode = getPostalCode();

        property.setPostalCode(postalCode);

        double area = getArea();

        property.setArea(area);

        double price = getPrice();

        property.setPrice(price);

        LocalDate dueDate = getDueDate();

        property.setDueDate(dueDate);
    }

    private static double getLandArea() {
        System.out.println("Podaj powierzchnie działki: ");

        double landArea = numbersScanner.nextDouble();
        while (landArea <= 0) {
            System.out.println("Podano złą powierzchnię działki! Spróbuj ponownie: ");
            landArea = numbersScanner.nextDouble();
        }

        return landArea;
    }

    private static int getFlatNumber() {
        System.out.println("Podaj numer mieszkania: ");

        int flatNumber = numbersScanner.nextInt();
        while (flatNumber <= 0) {
            System.out.println("Podano zły numer mieszkania! Spróbuj ponownie: ");
            flatNumber = numbersScanner.nextInt();
        }

        return flatNumber;
    }

    private static int getFloorNumber() {
        System.out.println("Podaj numer piętra: ");

        int floorNumber = numbersScanner.nextInt();
        while (floorNumber <= 0) {
            System.out.println("Podano zły numer piętra! Spróbuj ponownie: ");
            floorNumber = numbersScanner.nextInt();
        }

        return floorNumber;
    }

    private static LocalDate getDueDate() {
        System.out.println("Podaj datę obowiązywania oferty: ");
        LocalDate dueDate = null;
        boolean gettingTime = true;

        while (gettingTime) {
            try {
                dueDate = LocalDate.parse(stringScanner.nextLine());
                gettingTime = false;
            } catch (DateTimeParseException e) {
                System.out.println("Podano zły format daty! Spróbuj ponownie: ");
            }
        }

        return dueDate;
    }

    private static double getPrice() {
        System.out.println("Podaj cenę: ");

        double price = numbersScanner.nextDouble();
        while (price <= 0) {
            System.out.println("Podano złą cenę! Spróbuj ponownie: ");
            price = numbersScanner.nextDouble();
        }

        return price;
    }

    private static double getArea() {
        System.out.println("Podaj powierzchnie: ");

        double area = numbersScanner.nextDouble();
        while (area <= 0) {
            System.out.println("Podano złą powierzchnie! Spróbuj ponownie: ");
            area = numbersScanner.nextDouble();
        }

        return area;
    }

    private static String getStreet() {
        return getStringArg("Podaj ulicę: ", "Podano złą ulicę: ");
    }

    private static int getHouseNumber() {
        System.out.println("Podaj numer domu: ");

        int houseNumber = numbersScanner.nextInt();
        while (houseNumber <= 0) {
            System.out.println("Podano zły numer domu! Spróbuj ponownie: ");
            houseNumber = numbersScanner.nextInt();
        }

        return houseNumber;
    }

    private static String getCity() {
        return getStringArg("Podaj miejscowość: ", "Podano złą miejscowość!");
    }

    private static String getPostalCode() {
        return getStringArg("Podaj kod pocztowy: ", "Podano zły kod pocztowy!");
    }

    private static String getStringArg(String message, String errorMessage) {
        System.out.println(message);

        String arg = stringScanner.nextLine();
        while (arg.isEmpty()) {
            System.out.println(errorMessage + " Spróbuj ponownie: ");
            arg = stringScanner.nextLine();
        }

        return arg;
    }
}
