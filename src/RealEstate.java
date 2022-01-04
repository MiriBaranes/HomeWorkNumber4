
import java.util.Arrays;
import java.util.Scanner;

public class RealEstate {
    public static final int FOR_RENT=1;
    public static final int FOR_SALE=2;
    public static final int MIN_VALUE=0;
    public static final int APARTMENT_IN_BUILDING=1;
    public static final int PENTHOUSE_IN_BUILDING=2;
    public static final int PRIVAT_HOUSE=3;
    public static final int UNFILTERED_SELECTION=-999;
    public static final int ADD_ONE=1;
    public static final int ADVERTISING_LIMITED_MEDIATOR=10;
    public static final int ADVERTISING_LIMITED_REGULAR_USER=3;
    public static final int NOT_EXIST_INDEX=-1;
    private User[] users;
    private Property[] properties;
    private Address[] addresses;

    public RealEstate() {
        this.users = new User[MIN_VALUE];
        this.properties = new Property[MIN_VALUE];
        this.addresses = new Address[]{
                new Address("Ashkelon", "Malchei israel"),
                new Address("Ashkelon", "israel"),
                new Address("Ashkelon", "Ben zvi"),
                new Address("Tel Aviv", "Naama"),
                new Address("Rishon lezion", "israel amelch"),
                new Address("Ber sheva", "Malchei israel"),
                new Address("Nitan", "Eli sinai"),
                new Address("Ashdod", "israel"),
                new Address("Ashkelon", "hazabar"),
                new Address("Brechia", "Malchei israel")
        };
    }

    public User[] getUsers() {
        return users;
    }

    public Address[] getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }


    public Property[] getProperties() {
        return this.properties;
    }

    public void setProperties(Property[] properties) {
        this.properties = properties;
    }


    public void createUser() {
        String userName = new ParserUsername("Enter username", this).getInput();
        String password = new ParserPassword("Enter a password").getInput();
        String phoneNumber = new ParserPhone("Enter phone number").getInput();
        String aMediator = new ParserMediator("If you are a mediator press 1\nYou are a regular user press 2").getInput();

        User newUser = new User(userName, password, phoneNumber, aMediator.equals("1"));
        addUserToArray(newUser);

    }

    private Object[] addToArray(Object[] oldArray, Object toAdd) {
        Object[] newArray = new Object[oldArray.length + ADD_ONE];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        newArray[oldArray.length] = toAdd;
        newArray=Arrays.asList(newArray).toArray(oldArray);
        return newArray;
    }

    private void addPropertyToArray(Property propertyToAdd) {
        this.properties = (Property[]) addToArray(this.properties, propertyToAdd);
    }

    private void addUserToArray(User userToAdd) {
        this.users= (User[]) addToArray(this.users, userToAdd);

    }


    public int userIndexInTheArraysUsers(String username, String password) {
        int index = NOT_EXIST_INDEX;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(username) && this.users[i].getPassword().equals(password)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public User login() {
        String userName = getInput("Enter a username");
        String password = getInput("Enter a password");
        User user1 = null;

        int index = userIndexInTheArraysUsers(userName, password);
        if (index != NOT_EXIST_INDEX) {
            user1 = users[index];
        }
        return user1;
    }

    private String getInput(String request) {

        return new ParserBase(request).getInput();
    }

    private int getIntInput(String request, IntValidator validator) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.println(request);
            input = scanner.nextInt();
        } while (!validator.isValid(input));
        return input;
    }

    public boolean postNewProperty(User user) {
        boolean postNewProperty = false;
        int choiceTypeApartment;
        int floor = 0;
        int rooms;
        int apartmentNumber;
        int forRent;
        boolean isForRent = false;
        double price;

        if (advertisingLimit(user)) {
            String[] cities = cities();
            System.out.println("cities list: \n");
            printNumericalArray(cities);
            String cityName = getInput("Enter a city name");
            if (isAlreadyIn(cities, cityName)) {
                String[] streets = streetNames(this.addresses, cityName);
                System.out.println("The list of the streets is: \n");
                printNumericalArray(streets);
                String streetName =getInput("Enter a street name: ");
                if (isAlreadyIn(streets, streetName)) {
                    choiceTypeApartment = getIntInput(
                            "Type of apartment \n1- Apartment in building \n2- Penthouse in building \n3- Private house ",
                            (x) -> (APARTMENT_IN_BUILDING <= x && x <= PRIVAT_HOUSE)
                    );
                    if (choiceTypeApartment != PRIVAT_HOUSE) {
                        floor = getIntInput("What floor property (non-negative int)?", x -> x >= MIN_VALUE);
                    }
                    rooms = getIntInput("How many rooms are in the property (non-negative)?", x -> x >= MIN_VALUE);
                    apartmentNumber = getIntInput("Property number?", x -> x >= MIN_VALUE);
                    forRent = getIntInput("For Rent Press 1, For Sale Press 2.", x -> (x == FOR_RENT || x == FOR_SALE));

                    if (forRent == FOR_RENT) {
                        isForRent = true;
                    }

                    price = getIntInput("What is the price of the property?", x -> x >= MIN_VALUE);
                    Address address = new Address(cityName, streetName);
                    Property property = new Property(address, rooms, price, choiceTypeApartment, isForRent, apartmentNumber, floor, user);
                    addPropertyToArray(property);
                    postNewProperty = true;
                } else {
                    System.out.println("The street you wrote does not exist in the list");
                }
            } else {
                System.out.println("The city you wrote does not exist in the list");
            }
        } else {
            System.out.println("You over the limit of posting.");
        }
        return postNewProperty;
    }

    private boolean advertisingLimit(User user) {
        int count = 0;
        for (Property property : properties) {
            if (property.getAdvertiser().equals(user)) {
                count++;
            }
        }
        return ((count < ADVERTISING_LIMITED_REGULAR_USER) || (user.isMediator() && count < ADVERTISING_LIMITED_MEDIATOR));
    }

    public String[] cities() {
        String[] cities = new String[MIN_VALUE];
        for (int i = 0; i < this.addresses.length; i++) {
            String curr_city = this.addresses[i].getCityName();
            if (!isAlreadyIn(cities, curr_city)) {
                cities = (String[]) addToArray(cities, curr_city);
            }
        }
        return cities;
    }

    private boolean isAlreadyIn(String[] hay, String needle) {
        boolean found = false;
        for (String item : hay) {
            if (needle.equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public String[] streetNames(Address[] addresses, String city) {
        String[] streets = new String[MIN_VALUE];
        for (Address address : addresses) {
            if (address.getCityName().equals(city)) {
                streets = (String[]) addToArray(streets, address.getStreetName());
            }
        }
        return streets;
    }

    public void removeProperty(User user) {
        Property[] properties = userProperties(user);
        int size = properties.length;
        int removeIndex;
        if (size == MIN_VALUE) {
            System.out.println("No property posted.");
        } else {
            printAllUserProperties(user);
               removeIndex = getIntInput("Enter wanted property to remove by number if you dont wont to remove" +
                    "press -999: ", x -> (1 <= x && x <= size) || x==UNFILTERED_SELECTION) - 1;
            if (removeIndex!=UNFILTERED_SELECTION-1) {
                Property propertyToRemove = properties[removeIndex];
                this.properties = filterArrayByValue(this.properties, false, property -> property.equals(propertyToRemove));
                System.out.println("The property has been removed.");
            }
            else {
                System.out.println("At your request nothing was deleted\n");
            }
        }
    }

    public Property[] userProperties(User user) {
        return filterArrayByValue(this.properties, user.getUserName(), property -> property.getAdvertiser().getUserName());
    }

    public void printAllProperties() {
       printNumericalArray(this.properties);
    }

    private void printNumericalArray(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println((i + ADD_ONE) + " - " + array[i]);
        }
    }

    public void printAllUserProperties(User user) {
        Property[] propertiesUser = userProperties(user);
        printNumericalArray(propertiesUser);
        if (propertiesUser.length == 0) {
            System.out.println("There are no posted by you.");
        }
    }

    public Property[] search() {
        // region get input
        int forRentOrSale = getIntInput(
                "For rent press 1, For sale press 2, press -999 for all types",
                (x) -> x == FOR_RENT || x == FOR_SALE || x == UNFILTERED_SELECTION
        );

        int typeOfApartment = getIntInput("Enter a type of apartment:\n" +
                        "press 1 for Ordinary apartment in an apartment building.\n" +
                        "press 2 for Penthouse in an apartment building\n" +
                        "press 3 for private house\n" +
                        "press -999 for all types.",
                x -> (APARTMENT_IN_BUILDING <= x && x <= PRIVAT_HOUSE) || x == UNFILTERED_SELECTION);
        int numberOfRooms = getIntInput("number of rooms: ", x -> x >= MIN_VALUE || x == UNFILTERED_SELECTION);
        int minPrice = getIntInput("Enter wonted price min: ", x -> x >= MIN_VALUE || x == UNFILTERED_SELECTION);
        int maxPrice = getIntInput("Enter wonted price max: ", x -> x >= MIN_VALUE|| x == UNFILTERED_SELECTION);
        // endregion
        //region filter
        Property[] byRentOrSale = filterArrayByValue(this.properties, forRentOrSale, property -> property.isForRent() ? FOR_RENT : FOR_SALE);
        Property[] byUserTypeOfApartment = filterArrayByValue(byRentOrSale, typeOfApartment, Property::getTypeOfApartment);
        Property[] byUserNumberOfRooms = filterArrayByValue(byUserTypeOfApartment, numberOfRooms, Property::getNumberOfRooms);
        Property[] finalMach = filterArrayByValue(byUserNumberOfRooms, 2, property -> {
            int output = 0;
            if (minPrice == UNFILTERED_SELECTION || minPrice <= property.getPrice())
                output++;
            if (maxPrice == UNFILTERED_SELECTION || property.getPrice() <= maxPrice)
                output++;
            return output;
        });
        //endregion
        if (finalMach.length == MIN_VALUE) {
            System.out.println("There is no match according to the requests.");
        }
        printNumericalArray(finalMach);

        return finalMach;
    }

    private Property[] filterArrayByValue(Property[] properties, Object value, PropertyFieldGetter fieldGetter) {
        Property[] result = new Property[0];
        for (Property prop :
                properties) {
            if (value.equals(UNFILTERED_SELECTION) || fieldGetter.getField(prop).equals(value)) {
                result = (Property[]) addToArray(result, prop);
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder output= new StringBuilder();
        output.append("users: ");
        for (int i=0; i< this.users.length; i++){
            output.append(i+1).append(this.users[i]).append("\n");
        }
        output.append("properties: ");
        for (int i=0; i< this.properties.length; i++){
            output.append(i+1).append(this.properties[i]).append("\n");
        }

        output.append("addresses: ");
        for (int i=0; i< this.addresses.length; i++){
            output.append(i+1).append(this.addresses[i]).append("\n");
        }
        return output.toString();
    }

}
