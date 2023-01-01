public class User {
    final private String USERNAME;
    final private String PASSWORD;
    final private String PHONE_NUMBER;
    final private boolean IS_BROKER;
    private int postCount = 0;
    //O(1)
    public User(String userName, String password, String phoneNumber, boolean isBrokers) {
        this.USERNAME = userName;
        this.PASSWORD = password;
        this.PHONE_NUMBER = phoneNumber;
        this.IS_BROKER = isBrokers;
    }
    //O(1)
    public String getPassword() {
        return this.PASSWORD;
    }
    //O(1)
    public String getUsername() {
        return this.USERNAME;
    }
    //O(1)
    public boolean getIsBrokers() {
        return this.IS_BROKER;
    }
    //O(1)
    public String getPhoneNumber() {
        return this.PHONE_NUMBER;
    }
    //O(1)
    public String toString() {
        return "username: " + this.USERNAME;
    }
    //O(1)
    public int getPostCount() {
        return this.postCount;
    }
    //O(1)
    public void addPostCount() {
        this.postCount++;
    }
    //O(1)
    public void subtractPostCount() {
        this.postCount--;
    }
}
