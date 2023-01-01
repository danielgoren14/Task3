import java.util.Scanner;
//O(1)
public class Main {
    public static void main(String[] args) {
        RealEstate realEstate = new RealEstate();
        menu(realEstate);
    }
    //O(1)
    public static void menu(RealEstate realEstate) {
        Scanner scanner = new Scanner(System.in);
        int userChoose;
        do {
            System.out.println("welcome to 'yad 2' \n please choose : \n 1 - to create user \n 2 - to login \n 3 - to exit ");
            userChoose = scanner.nextInt();
        } while (userChoose < 1 || userChoose > 3);
        if (userChoose == 1) {
            realEstate.createUser();
            menu(realEstate);
        } else if (userChoose == 2) {
            User user = realEstate.login();
            if (user == null) {
                menu(realEstate);
            } else {
                subMenu(user, realEstate);
            }
        }
    }
    //O(1)
    public static void subMenu(User user, RealEstate realEstate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                1 to make new post
                2 to remove post
                3 to see all the properties
                4 to see all your properties
                5 to search for properties
                6 to logout""");

        int userChoose;
        do {
            userChoose = scanner.nextInt();
        } while (userChoose < 1 || userChoose > 6);
        if (userChoose == 1) {
            boolean postAdded = realEstate.postNewProperty(user);
            if (postAdded) {
                user.addPostCount();
                System.out.println("add post successfully");
            }
            subMenu(user, realEstate);
        } else if (userChoose == 2) {
            realEstate.removeProperty(user);
            subMenu(user, realEstate);
        } else if (userChoose == 3) {
            realEstate.printAllProperties();
            subMenu(user, realEstate);
        } else if (userChoose == 4) {
            realEstate.printProperties(user);
            subMenu(user, realEstate);
        } else if (userChoose == 5) {
            Property[] searchedArray = realEstate.search();
            int counter = 1;
            if (!(searchedArray == null || searchedArray.length == 0)) {
                for (int i = 0; i < searchedArray.length; i++) {
                    System.out.println(counter+ ") ");
                    System.out.println(searchedArray[i]);
                    counter++;
                }
            }
            subMenu(user, realEstate);
        } else {
            System.out.println("logout successfully");
            menu(realEstate);
        }
    }
}