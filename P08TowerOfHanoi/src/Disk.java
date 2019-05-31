
public class Disk implements Comparable<Disk> {

    private int size; // 1-9: restricts disk movement, and used for drawing
    
    /**
     * Constructs a new immutable disk object with the specified size.
     * @param size is used for drawing and comparing against other disks.
     * @throws IllegalArgumentException when size is not between 1 and 9.
     */
    public Disk(int size) throws IllegalArgumentException {
        if (size >= 1 && size <= 9) {
            this.size = size;
        }
        else {
            throw new IllegalArgumentException("WARNING: Number must be between 1-9.");
        }
    }
     
    /**
     * Compares one disk to another to determine which is larger, and therefore
     * which can be moved on top of the other.
     * @param other is a reference to the disk we are comparing this one to.
     * @return a positive number when this.size > other.size,
     *         a negative number when this.size < other.size, or
     *         zero when this.size == other.size, or other is null.
     */
    @Override
    public int compareTo(Disk other) {
        
        if (other == null || this.size == other.size) {
            return 0;
        }
        else {
            return (this.size - other.size);
        }
    }
     
    /**
     * The string representation of this disk object includes its integer size
     * surrounded by size-1 equals characters (=) on each side, and enclosed 
     * within angle brackets (<>).  For example:
     *     size 1: "<1>"
     *     size 2: "<=2=>"
     *     size 3: "<==3==>"
     * @return the string representation of this disk object based on its size.
     */
    @Override
    public String toString() { 
        String toString="<";
        String temp="";
        for (int i = 0; i<size-1; i++) {
            temp+="=";
        }
        toString = toString + temp + Integer.toString(size) + temp + ">";
        return toString; 
    }
    
}
