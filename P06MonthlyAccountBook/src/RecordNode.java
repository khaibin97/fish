
public class RecordNode {
    private int day;
    private double amount;
    private RecordNode next;
    
    public RecordNode(int day, double amount) {
        this.day = day;
        this.amount = amount;
        next = null;
        
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public RecordNode getNext() {
        return next;
    }
    public void setNext(RecordNode next) {
        this.next = next;
    }
    
}
