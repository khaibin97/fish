import java.util.ArrayList;
import java.util.Collections;

/**
 * SelectionSort class implements Sort interface to share a general method name and a default
 * implementation
 * 
 * @author Khai Bin Woon
 *
 */
public class SelectionSort implements Sort{
    private final int indexToSort;//A constant since there is no need for change
    
    /**
     * SelectionSort constructor gets the specified index of the element
     * @param index - index used for sorting
     */
    public SelectionSort (int index) {
        indexToSort = index;
    }
    
    /**
     * selection sort sorts by swapping smallest elements to the front after going through the array
     * eventually, no swaps are needed at the end
     * 
     * @param sortedList - arraylist to be sorted
     */
    @Override
    public void sort(ArrayList<Student> sortedList) {
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int index = i; // initialize index to store smallest element index
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (greaterThan(sortedList.get(index), sortedList.get(j), indexToSort)) {// look for the smallest
                                                                            // element
                    index = j; // place a marker for swapping
                }
            }
            // swaps the array list references;
            Collections.swap(sortedList, index, i);
        }
    }
    
}
