import java.util.Arrays;

public class City {
    private final String CITY_NAME;
    private final String AREA;
    private final String[] LIST_OF_STREET;
    //O(1)
    public City(String cityName, String area, String[] listOfStreets) {
        this.CITY_NAME = cityName;
        this.AREA = area;
        this.LIST_OF_STREET = listOfStreets;
    }
    //O(1)
    public String getCityName() {
        return this.CITY_NAME;
    }
    //O(1)
    public String getArea() {
        return this.AREA;
    }
    //O(1)
    public String[] getListOfStreets() {
        return this.LIST_OF_STREET;
    }
    //O(1)
    public String toString() {
        return "City name : " + this.CITY_NAME + "\nArea : " + getArea() + "\n List of streets :" + Arrays.toString(getListOfStreets());
    }
}



