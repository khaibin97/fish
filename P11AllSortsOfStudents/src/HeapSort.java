import java.util.ArrayList;
import java.util.Collections;

/**
 * HeapSort class implements Sort interface to share a general method name and a default
 * implementation
 * 
 * @author Khai Bin Woon
 *
 */
public class HeapSort implements Sort {
    private final int indexToSort;//A constant since there is no need for change
    
    /**
     * HeapSort constructor gets the specified index of the element
     * @param index - index used for sorting
     */
    public HeapSort (int index) {
        indexToSort = index;
    }
    
    /**
     * heap sort creates a max-heap to find largest value and swaps to the end, and shrinks the tree
     * array elements at the end are sorted.
     * 
     * @param sortedList - arraylist to be sorted
     */
    @Override
    public void sort(ArrayList<Student> sortedList) {
        int n = sortedList.size();

        // Makes the array into a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(sortedList, n, i);
        }

        // One by one sort the largest to the end of array, reducing the tree afterwards
        for (int i = n - 1; i >= 0; i--) {
            // Move current root node to end
            Collections.swap(sortedList, 0, i);

            // rearrange max heap with the reduced heap
            heapify(sortedList, i, 0);
        }
    }

    /**
     * heapify helps the heapSort create a max-heap by rearranging the array elements
     * 
     * @param sortedList - arraylist to be sorted
     * @param n - current number of nodes in the tree
     * @param curr - "root" node, to be swapped to the biggest value to be the root node
     */
    private void heapify(ArrayList<Student> sortedList, int n, int curr) {
        int largest = curr; // current node index is largest
        int leftChild = 2 * largest + 1;// left child index
        int rightChild = 2 * largest + 2;// right child index

        // If left child bigger than currentNode
        if (leftChild < n && greaterThan(sortedList.get(leftChild), sortedList.get(largest), indexToSort)) {
            largest = leftChild;// move index into left child
        }

        if (rightChild < n && greaterThan(sortedList.get(rightChild), sortedList.get(largest), indexToSort)) {
            largest = rightChild;// move index into right child
        }

        // if largest node is not current, swap and call recursively
        if (largest != curr) {
            Collections.swap(sortedList, curr, largest);
            // do the same with the rest of the sub-tree
            heapify(sortedList, n, largest);
        }
    }

}
