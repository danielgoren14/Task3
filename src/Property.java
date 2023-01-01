public class Property {
    final private City CITY;
    final private String STREET;
    final private int AMOUNT_OF_ROOM;
    final private int PRICE;
    final private int TYPE;
    final private boolean IS_FOR_RENT;
    final private int HOUSE_NUMBER;
    final private int FLOOR;
    final private String SELLER_NAME;
    final private String PHONE_NUMBER;
    final private boolean IS_BROKER;
    //O(1)
    public Property(City city, String street, String userName, boolean isForRenting, String phoneNumber, boolean isBroker, int floor, int type, int amountOfRooms, int price, int houseNumber) {
        this.CITY = city;
        this.STREET = street;
        this.SELLER_NAME = userName;
        this.PHONE_NUMBER = phoneNumber;
        this.IS_BROKER = isBroker;
        this.TYPE = type;
        this.FLOOR = floor;
        this.AMOUNT_OF_ROOM = amountOfRooms;
        this.PRICE = price;
        this.HOUSE_NUMBER = houseNumber;
        this.IS_FOR_RENT = isForRenting;
    }
    //O(1)
    public double getPrice() {
        return this.PRICE;
    }
    //O(1)
    public int getAmountOfRooms() {
        return this.AMOUNT_OF_ROOM;
    }
    //O(1)
    public int getFloor() {
        return this.FLOOR;
    }
    //O(1)
    public int getHouseNumber() {
        return this.HOUSE_NUMBER;
    }
    //O(1)
    public String getCity() {
        return this.CITY.getCityName();
    }
    //O(1)
    public String getStreet() {
        return this.STREET;
    }
    //O(1)
    public String getSellerName() {
        return this.SELLER_NAME;
    }
    //O(1)
    public int getType() {
        return this.TYPE;
    }
    //O(1)
    public boolean getIsForRenting() {
        return this.IS_FOR_RENT;
    }
    //O(1)
    public String toString() {
        String broker;
        if (this.IS_BROKER) {
            broker = "(real estate agent)";
        } else {
            broker = "(not an agent)";
        }

        String floorNumber;
        if (this.TYPE == 3) {
            floorNumber = "";
        } else {
            floorNumber = "floor" + getFloor();
        }

        String houseType = null;
        if (this.TYPE == 3) {
            houseType = "detached home";
        } else if (this.TYPE == 1) {
            houseType = "apartment ";
        } else if (this.TYPE == 2) {
            houseType = "penthouse";
        }

        String forRent;
        if (this.IS_FOR_RENT) {
            forRent = "for renting";
        } else {
            forRent = "for selling";
        }

        return getCity() + "  - " + getStreet() + " " + getHouseNumber() + "." + "\n" + houseType + " - " + forRent + ": " + this.AMOUNT_OF_ROOM + " rooms ,  " + floorNumber +
                "\nPrice: " + this.PRICE + "$\nContact info: " + this.SELLER_NAME + " " + this.PHONE_NUMBER + " " + broker +"\n";
    }
}


