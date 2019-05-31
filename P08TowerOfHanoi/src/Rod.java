import java.util.NoSuchElementException;

public class Rod implements Comparable<Rod>{
    private int numberOfDisks; // tracks the number of disks on this rod
    private Disk[] disks;      // stores references to the disks on this rod
                               // index 0: bottom, index discNumber-1: top
     
    /**
     * Constructs a new rod that can hold a maximum of maxHeight Disks. The
     * numberOfDisks new Disks will be created with sizes between 1 and 
     * numberOfDisks (inclusive), and arranged from largest (on bottom) to the
     * smallest (on top) on this Rod.
     * @param maxHeight is the capacity or max number of Disks a rod can hold.
     * @param numberOfDisks is the initial number of Disks created on this rod.
     */
    public Rod(int maxHeight, int numberOfDisks) {
        disks = new Disk[maxHeight];
        this.numberOfDisks = numberOfDisks; 
        
        int index = 0;
        for (int i = numberOfDisks; i>0; i--) { //initialize rod with full disks.
            disks[index++] = new Disk(i);
        }
    }
     
    /**
     * Adds one new Disk to the top of this rod.
     * @param disk is a reference to the Disk being added to this rod.
     * @throws IllegalStateException when this rod is already full to capacity.
     */
    public void push(Disk disk) throws IllegalStateException {
        if(numberOfDisks < disks.length) {
            disks[numberOfDisks] = disk;
            numberOfDisks+=1;
        }
        else {
            throw new IllegalStateException("WARNING: Cannot add disk, rod is full.");
        }
    }
     
    /**
     * Removes and returns one Disk from the top of this rod.
     * @return a reference to the Disk that is being removed.
     * @throws NoSuchElementException when this rod is empty.
     */
    public Disk pop() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException("WARNING: Rod is Empty");
        } else {
            Disk temp = disks[--numberOfDisks];
            disks[numberOfDisks] = null;//post-decrement to decrease numofdisks
            return temp;
        } 
    }
     
    /**
     * Returns (without removing) one Disk from the top of this rod.
     * @return a reference to the Disk that is being returned.
     * @throws NoSuchElementException when this rod is empty.
     */
    public Disk peek() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException("WARNING: Rod is Empty");
        } else {
            return disks[numberOfDisks-1];
        }
    }
     
    /**
     * Indicates whether this rod is currently holding zero Disks.
     * @return true when there are no Disks on this rod.
     */
    public boolean isEmpty() {
        if (numberOfDisks==0) {
            return true;
        } else {
            return false;
        }
    }
     
    /**
     * Indicates whether this rod is currently full to its capacity with disks.
     * @return true when the number of Disks on this rod equals its max height.
     */
    public boolean isFull() { 
        if (disks[disks.length-1] != null) {
            return true;
        } else {
            return false;
        }
    } 
     
    /**
     * Compares one rod to another to determine whether it's legal to move the
     * top disk from this rod onto the other.
     * @param other is the destination rod we are considering moving a disk to.
     * @return +1 when moving a disk from this rod to other is legal,
     *         -1 when moving a disk from this rod to other is illegal,
     *         or 0 when this rod is empty and there are no disks to move.
     */
    @Override
    public int compareTo(Rod other) { 
        if (this.isEmpty()) {
            return 0;
        }
        else if (other.isFull() || (!other.isEmpty() && this.peek().compareTo(other.peek())>=0)) {
            return -1;
        }
        else {
            return 1;
        }
    }
     
    /**
     * The string representation of this rod includes its max height number
     * of rows separated by and ending with newline characters (\n).  Rows 
     * occupied by a disk will include that disk's string representation, and 
     * other rows instead contain a single vertical bar character (|).  All 
     * rows are centered by surrounding both sides with spaces until they are 
     * each capacity*2+1 characters wide.  Example of 5 capacity rod w\3 disks:
     * "     |     \n" +
     * "     |     \n" +
     * "   <=2=>   \n" +
     * "  <==3==>  \n" +
     * "<====5====>\n"
     * @return the string representation of this rod based on its contents.
     */
    @Override
    public String toString() { 
        int width = 0;
        if (disks[0] != null) {
            width = disks[0].toString().length();
        }
        
        String toString = "";
        for (int i = disks.length-1; i>=0; i--) {
            if (disks[i]==null) {
                toString += diskFormatter(width, "|");
            }
            else {
                toString += diskFormatter(width, disks[i].toString());
            }
        }
        return toString; 
    }
    
    private String diskFormatter(int width, String item) {
        String space = "";
        int amountOfSpaces = (width - item.length())/2;
        for (int i = 0; i<amountOfSpaces; i++) {
            space += " ";
        }
        return (space + item + space + "\n");
    }
    
//    public static void main(String args[]) {
//        Rod test = new Rod(9,9);
//        Rod test2 = new Rod(8,9);
//        Disk temp;
//        System.out.println(test.isFull());
//        System.out.println(test.toString()+test2.toString());
//        test.pop();test.pop();
//        System.out.println(test.peek().toString());
//        System.out.println(test2.compareTo(test));
//        temp = test2.pop();
//        System.out.println(test2.isEmpty());
//        test.push(temp);
//        System.out.println(test.toString()+test2.toString());
//        test.pop();test.pop();test.pop();test.pop();test.pop();test.pop();test.pop();
//        temp = test.pop();
//        test2.push(temp);
//        System.out.println(test2.toString());
//    }
}
