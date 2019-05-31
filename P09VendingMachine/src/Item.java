
public class Item {
    private int expirationDay; // starting at day 0, which represents Jan 1, 2018
    private String description; // a human readable description of this item
    
    public Item (int expirationDay, String description) {
        this.expirationDay = expirationDay;
        this.description = description;
    }
    
    public int getExpirationDay() {
        return expirationDay;
    }
    
    public String getDescription() {
        return description;
    }
}
