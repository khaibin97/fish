import java.util.ArrayList;
import java.util.Collections;

/**
 * Sort interface provides a framework for a sort class to use
 * 
 * @author Khai Bin Woon
 *
 */
public interface Sort { 
    
    /**
     * sort method interface, all sort classes should implement a sort method
     * @param sortedList - arraylist to be sorted
     */
    public void sort(ArrayList<Student> sortedList);
    
    /**
    * greaterThan method compares the score value of the Student object instead of the references the
    * value taken is based on the user input it is read as a greater than b, works as if (a>b) with
    * integers 
    * This method is shared between all different type of sorts.
    * 
    * @param a - A Student object to compare
    * @param b - A Student object to compare
    * @param scoreIndex - the index to use on the score array
    * @return true or false based on (a>b)
    */
    default boolean greaterThan(Student a,Student b, int scoreIndex) {
        // gets the value from the Student class
        String studentA = a.getValue(scoreIndex);
        String studentB = b.getValue(scoreIndex);

        if (scoreIndex == 0) { // if index is 0, sorting alphabetically, comparing strings
            if (studentA.compareTo(studentB) > 0) {
                return true;
            } else {
                return false;
            }
        } else { // the rest are scores, compare by integers
            if (Integer.parseInt(studentA) > Integer.parseInt(studentB)) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    

}
