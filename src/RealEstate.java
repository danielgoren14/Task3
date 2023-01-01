import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RealEstate {
    private User[] usersArray;
    private Property[] properties;
    private final City[] cities;

    private final int INDEX_SOUTH = 0;
    private final int INDEX_MIDLAND = 1;
    private final int INDEX_NORTH = 2;
    private final int INDEX_SHARON = 3;
    private final int INDEX_NEGEV = 4;

    //O(n)
    public RealEstate() {
        this.usersArray = new User[0];
        this.properties = new Property[0];
        this.cities = new City[10];
        String[] arrayOfCity = {"ashdod", "tel aviv", "eilat", "ashkelon", "jerusalem", "rehovot", "haifa", "bat yam", "baer sheva", "herzelia"};
        String[] possibleNameOfStreet = {"rimon", "nisan", "apple", "shaul", "horev", "israel", "shoam", "baron"
                , "haim", "africa", "daniel", "gadi", "tamar",
                "dov", "tut", "gur", "roni", "avraham", "sinai", "gordon", "black", "brown"};
        String[] arrayOfArea = {"south", "midland", "north", "sharon", "negev"};
        for (int i = 0; i < arrayOfCity.length; i++) {
            int areaIndex = areaMatcher(arrayOfCity, i);
            String[] randomArrayOfStreets = randomStreets(possibleNameOfStreet);
            this.cities[i] = new City(arrayOfCity[i], arrayOfArea[areaIndex], randomArrayOfStreets);
        }
    }
    //O(n)
    private String[] randomStreets(String[] possibleNameOfStreet) {
        String[] randomStreetNames = {"1", "1", "1", "1", "1"};
        Random random = new Random();
        for (int i = 0; i < randomStreetNames.length; i++) {
            int index;
            do {
                index = random.nextInt(0, possibleNameOfStreet.length);
            } while (alreadyInCity(randomStreetNames, possibleNameOfStreet, index));
            randomStreetNames[i] = possibleNameOfStreet[index];
        }
        return randomStreetNames;
    }
    //O(n)
    private boolean alreadyInCity(String[] randomArrayOfStreetName, String[] possibleNameOfStreet, int index) {
        for (int i = 0; i < randomArrayOfStreetName.length; i++) {
            if (randomArrayOfStreetName[i].equals(possibleNameOfStreet[index])) {
                return true;
            }
        }
        return false;
    }
    //O(1).
    private int areaMatcher(String[] arrayOfCity, int i) {
        if (arrayOfCity[i].equals("ashdod") || arrayOfCity[i].equals("ashkelon")) {
            return this.INDEX_SOUTH;
        }
        if (arrayOfCity[i].equals("tel aviv") || arrayOfCity[i].equals("jerusalem") || arrayOfCity[i].equals("rehovot") || arrayOfCity[i].equals("bat yam")) {
            return this.INDEX_MIDLAND;
        }
        if (arrayOfCity[i].equals("haifa")) {
            return this.INDEX_NORTH;
        }
        if (arrayOfCity[i].equals("herzelia")) {
            return this.INDEX_SHARON;
        }
        if (arrayOfCity[i].equals("eilat") || arrayOfCity[i].equals("baer " + "sheva")) {
            return this.INDEX_NEGEV;
        }
        return 0;
    }
    //O(n)
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter a user name: ");
        String username;
        boolean alreadyUsed;
        if (this.usersArray != null) {
            do {
                username = scanner.nextLine();
                alreadyUsed = false;
                for (int i = 0; i < this.usersArray.length; i++) {
                    if (username.equals(this.usersArray[i].getUsername())) {
                        alreadyUsed = true;
                        System.out.println("this username is already taken, please choose another username");
                        break;
                    }
                }
            } while (alreadyUsed);
        } else {
            username = scanner.nextLine();
        }
        String password;
        do {
            System.out.println("please enter a password that contains at least one number and one of the next signs:($,%,_) and have a length of 5 digits: ");
            password = scanner.nextLine();
        } while (!isValidPassword(password));

        System.out.println("please enter your phone number (only 10 digits without '-' or pre number(972) and is an israeli number)");
        String phoneNumber = scanner.nextLine();
        while (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("number invalid");
            System.out.println("enter correct phone number ");
            phoneNumber = scanner.nextLine();
        }
        System.out.println("are u a broker y/n? ");
        String isBroker = scanner.nextLine();
        boolean broker = false;
        while (!isBroker.equals("y") && !isBroker.equals("n")) {
            System.out.println("please enter only 'y' or 'n'! ");
            isBroker = scanner.nextLine();
        }
        if (isBroker.equals("y")) {
            broker = true;
        }
        addUserToArray(username, password, phoneNumber, broker);
    }
    //O(n)
    private void addUserToArray(String userName, String password, String phone, boolean isBroker) {
        User newUser = new User(userName, password, phone, isBroker);
        User[] newLengthForUser = new User[this.usersArray.length + 1];
        if (this.usersArray != null) {
            for (int i = 0; i < this.usersArray.length; i++) {
                newLengthForUser[i] = this.usersArray[i];
            }
            newLengthForUser[this.usersArray.length] = newUser;
            this.usersArray = newLengthForUser;
        } else {
            this.usersArray = new User[1];
            this.usersArray[0] = newUser;
        }
    }
    //O(1)
    private static boolean isValidPassword(String password) {
        return password.matches("(?=.*([$%_].*\\d|\\d.*[$%_]).*).{5,}");
    }
    //O(1)
    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("05\\d{8}");
    }
    //O(n)
    User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your username: ");
        String usernameLogin = scanner.nextLine();
        System.out.println("please enter your password: ");
        String passwordLogin = scanner.nextLine();
        User userInfo = null;
        for (int i = 0; i <= this.usersArray.length - 1; i++) {
            if (this.usersArray[i].getUsername().equals(usernameLogin)) {
                if (this.usersArray[i].getPassword().equals(passwordLogin)) {
                    userInfo = this.usersArray[i];
                }
            }
        }
        if (userInfo == null) {
            System.out.println("incorrect username or password");
        }
        return userInfo;
    }
    //O(n)*3
    boolean postNewProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        if (!ifUserCanPost(user)) {
            System.out.println("max post count reached");
            return false;
        }
        String streetOfNewProperty;
        boolean isPropertyForRent;
        int floorNumber = 0;
        int typeOfHouse;
        int numberOfRooms;
        int priceOfProperty;
        int numberOfProperty;
        int index = -1;
        int streetIndex = -1;

        for (int i = 0; i < this.cities.length; i++) {
            System.out.println((this.cities[i].getCityName()));
        }
        System.out.println("please enter your city of your property: ");
        String cityOfNewProperty = scanner.nextLine();
        for (int i = 0; i < this.cities.length; i++) {
            if (this.cities[i].getCityName().equals(cityOfNewProperty)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("City does not exist");
            return false;
        }
        System.out.println(Arrays.toString(this.cities[index].getListOfStreets()));
        System.out.println("please enter the street name of your new property: ");
        streetOfNewProperty = scanner.nextLine();
        for (int i = 0; i < this.cities[index].getListOfStreets().length; i++) {
            if (this.cities[index].getListOfStreets()[i].equals(streetOfNewProperty)) {
                streetIndex = i;
                break;
            }
        }
        if (streetIndex == -1) {
            System.out.println("street does not exist");
            return false;
        }
        System.out.println("""
                please choose which type of house u have:\s
                 enter 1 to an apartment
                 enter 2 to a penthouse
                 enter 3 to a detachment home""");
        typeOfHouse = scanner.nextInt();
        if (typeOfHouse > 3 || typeOfHouse < 1) {
            System.out.println("incorrect value");
            return false;
        } else if (typeOfHouse == 1 || typeOfHouse == 2) {
            System.out.println("enter floor number");
            floorNumber = scanner.nextInt();
        }
        System.out.println("enter how many room ");
        numberOfRooms = scanner.nextInt();
        System.out.println("enter house number");
        numberOfProperty = scanner.nextInt();
        System.out.println("If the property is for rent enter 1 ,if it for sale enter 2");
        int tempForRent = scanner.nextInt();
        if (tempForRent == 1) {
            isPropertyForRent = true;
        } else if (tempForRent == 2) {
            isPropertyForRent = false;
        } else {
            System.out.println("incorrect value");
            return false;
        }
        System.out.println("enter price ");
        priceOfProperty = scanner.nextInt();
        addPropertyToArray(this.cities[index], streetOfNewProperty, user.getUsername(), isPropertyForRent, user.getPhoneNumber(), user.getIsBrokers(), floorNumber, typeOfHouse, numberOfRooms, priceOfProperty, numberOfProperty);
        return true;
    }
    //O(1)
    private boolean ifUserCanPost(User user) {
        return !((user.getIsBrokers() && user.getPostCount() == 5) || (!user.getIsBrokers() && user.getPostCount() == 2));
    }
    //O(n)
    private void addPropertyToArray(City city, String street, String userName, boolean isForRenting, String phoneNumber,
                                    boolean isBroker, int floor, int type, int amountOfRooms, int price, int houseNumber) {
        Property[] newLengthForPropertyList = new Property[this.properties.length+1];
        Property newProperty = new Property(city, street, userName, isForRenting, phoneNumber, isBroker, floor, type, amountOfRooms, price, houseNumber);
        if (this.properties != null) {
            for (int i = 0; i <this.properties.length ; i++) {
                newLengthForPropertyList[i] =this.properties[i];
            }
            newLengthForPropertyList[this.properties.length] = newProperty;
            this.properties = newLengthForPropertyList;
        } else {
            this.properties = new Property[1];
        }
    }
    //O(n)*2
    void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        if (this.properties == null || this.properties.length == 0) {
            System.out.println("you did not post any property");
        } else {
            int counter = 0;
            for (int i = 0; i < this.properties.length; i++) {
                if (user.getUsername().equals(this.properties[i].getSellerName())) {
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("you dont have any properties");
            } else {
                printProperties(user);
                int userChoose;
                do {
                    System.out.println("choose property to remove");
                    userChoose = scanner.nextInt();
                } while (userChoose <= 0 || userChoose > counter);
                for (int i = 0; i < this.properties.length; i++) {
                    if (user.getUsername().equals(this.properties[i].getSellerName())) {
                        userChoose--;
                        if (userChoose <= 0) {
                            userChoose = i;
                            break;
                        }
                    }
                }
                deletePropertiesFromArray(userChoose);
                user.subtractPostCount();
                System.out.println("property removed");
            }
        }
    }
    //O(n)*2
    private void deletePropertiesFromArray(int index) {
        this.properties[index] = null;
        Property[] tempProperties = new Property[this.properties.length - 1];
        for (int i = 0; i < index; i++) {
            tempProperties[i] = this.properties[i];
        }
        for (int i = index; i < tempProperties.length; i++) {
            tempProperties[i] = this.properties[i + 1];
        }
        this.properties = tempProperties;
    }
    //O(n)
    void printAllProperties() {
        if (this.properties == null || this.properties.length == 0) {
            System.out.println("list empty");
            return;
        }
        for (int i = 0; i < this.properties.length; i++) {
            System.out.println(i + 1 + ")");
            System.out.println(this.properties[i]);
        }
    }
    //O(n)
    void printProperties(User user) {
        if (this.properties == null || this.properties.length == 0) {
            System.out.println("list empty");
            return;
        }
        int counter = 1;
        for (int i = 0; i < this.properties.length; i++) {
            if (user.getUsername().equals(this.properties[i].getSellerName())) {
                System.out.println(counter + ")");
                System.out.println(this.properties[i]);
                counter++;
            }
        }
        if (counter == 1) {
            System.out.println("list is empty");
        }
    }
    //O(n)
    Property[] search() {
        if (this.properties == null || this.properties.length == 0) {
            System.out.println("list is empty");
            return null;
        }
        final int IGNORE = -999;
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                search panel :
                to ignore any parameter please enter "-999"
                """);

        int typeOfHouse;
        do {
            System.out.println("""
                    please choose which type of house u want:\s
                     enter 1 for apartments
                     enter 2 for penthouses
                     enter 3 for detachment homes""");
            typeOfHouse = scanner.nextInt();
        } while ((typeOfHouse < 1 || typeOfHouse > 3) && typeOfHouse != IGNORE);

        String sellOrRent;
        do {
            System.out.println(" for rent, enter 'rent', or for sell, enter 'sell'");
            sellOrRent = scanner.next();
        } while (!(sellOrRent.equals("rent") || sellOrRent.equals("sell")) && !sellOrRent.equals("-999"));

        int amountOfRooms;
        do {
            System.out.println("Please enter the amount of rooms in the house");
            amountOfRooms = scanner.nextInt();
        } while ((amountOfRooms < 1 || amountOfRooms > 100) && amountOfRooms != IGNORE);

        System.out.println("Please enter the range of prices you want to choose");
        int minPrice;
        do {
            System.out.println("enter minimum price");
            minPrice = scanner.nextInt();
        } while (minPrice < 0 && minPrice != IGNORE);

        if (minPrice == IGNORE) {
            minPrice = 0;
        }

        int maxPrice;
        do {
            System.out.println(" enter maximum price");
            maxPrice = scanner.nextInt();
        } while (maxPrice < minPrice && maxPrice != IGNORE);
        if (maxPrice == IGNORE) {
            maxPrice = 2147483647;
        }
        int counter = 0;
        int[] index = new int[this.properties.length];
        for (int i = 0; i < this.properties.length; i++) {
            if (searchPrice(i, minPrice, maxPrice) && searchRoomAmount(i, amountOfRooms) && searchHouseType(i, typeOfHouse) && searchSellOrRent(i, sellOrRent)) {
                index[counter] = i;
                counter++;
            }
        }
        Property[] searchedProperty = new Property[counter];
        for (int i = 0; i < counter; i++) {
            searchedProperty[i] = this.properties[index[i]];
        }
        if (counter == 0) {
            System.out.println("there is no result that match");
        }
        return searchedProperty;
    }
    //O(1)
    private boolean searchPrice(int index, int minPrice, int maxPrice) {
        return (minPrice <= this.properties[index].getPrice() && maxPrice >= this.properties[index].getPrice());
    }
    //O(1)
    private boolean searchRoomAmount(int index, int amountOfRooms) {
        if (amountOfRooms == -999) {
            return true;
        } else {
            return (amountOfRooms == this.properties[index].getAmountOfRooms());
        }
    }
    //O(1)
    private boolean searchHouseType(int index, int typeOfHouse) {
        if (typeOfHouse == -999) {
            return true;
        } else {
            return typeOfHouse == this.properties[index].getType();
        }
    }
    //O(1)
    private boolean searchSellOrRent(int index, String sellOrRent) {
        boolean isForRent = false;
        if (sellOrRent.equals("rent")) {
            isForRent = true;
        } else if (sellOrRent.equals("-999")) {
            return true;
        }
        return this.properties[index].getIsForRenting() == isForRent;
    }
}
