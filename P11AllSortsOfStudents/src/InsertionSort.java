import java.util.ArrayList;

/**
 * InsertionSort class implements Sort interface to share a general method name and a default
 * implementation
 * 
 * @author Khai Bin Woon
 *
 */
public class InsertionSort implements Sort {

    private final int indexToSort;// A constant since there is no need for change

    /**
     * InsertionSort constructor gets the specified index of the element
     * 
     * @param index - index used for sorting
     */
    InsertionSort(int index) {
        indexToSort = index;
    }

    /**
     * insertionSort sorts by going through the array and moving smaller values to the front of the
     * array elements in the correct spot are untouched
     * 
     * @param sortedList - arraylist to be sorted
     */
    @Override
    public void sort(ArrayList<Student> sortedList) {
        for (int j = 1; j < sortedList.size(); j++) { // index proceeds and the front arrays are
                                                      // sorted
            Student key = sortedList.get(j);// current array to compare
            int i = j - 1;// the previous array
            while ((i > -1) && (greaterThan(sortedList.get(i), key, indexToSort))) { // compare
                                                                                     // current with
                                                                                     // the
                // previous elements
                // successively
                sortedList.set(i + 1, sortedList.get(i)); // "pushes" one element "backward" at a
                                                          // time
                i--;
            } // stops when the current array found its designated spot
            sortedList.set(i + 1, key);// sets the array to the sorted position
        }
    }

}
