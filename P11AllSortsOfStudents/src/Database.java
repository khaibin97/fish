import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Database class handles all the ArrayList, acts as a "middle-man" to pass data different classes
 * 
 * @author Khai Bin Woon
 *
 */
public class Database {
    private ArrayList<Student> list = new ArrayList();// list of students name in original order.
    private ArrayList<Student> sortedList;// array list to handle the sorting separately
    private int indexToSort = 0; //
    Scanner scnr;

    /**
     * Database constructor to read the file path input
     * 
     * @param fileName - file path for the text file
     */
    public Database(String fileName) {
        parseFile(fileName.trim()); // Loads the file into arraylists
    }

    /**
     * Opens the file and loads it into an array lists, creating a Student object for each student
     * 
     * @param name - file path name
     */
    private void parseFile(String name) {
        try {
            scnr = new Scanner(new File(name)); // opens the file
        } catch (Exception e) {
            System.out.println("WARNING: Could not find or open the " + name + " file.");
            return; // returns the method so the user may try again
        }

        while (scnr.hasNextLine()) { // scans each line and store into Student object for further
                                     // parsing
            list.add(new Student(scnr.nextLine()));
        }
        scnr.close();
        // "sorted" list to remove possible null reference exceptions, since it is also needed to
        // print out at start
        sortedList = list;
    }



    /**
     * selectSort method takes in the user command and chooses which sorting methods to use based on
     * the input
     * 
     * @param input - command for choosing sort method
     */
    public void selectSort(String input) {
        // remove any whitespace and uniforms input
        String temp = input.replaceAll("\\s", "").toLowerCase();
        try {
            indexToSort = Integer.parseInt(temp.substring(1));// throws multiple exceptions based on
                                                              // the wrong user inputs
            if (indexToSort > list.get(0).getScoreAmount()) { // checks if the index would cause a
                                                              // index out of bounds exception
                throw new ArrayIndexOutOfBoundsException();// throws and handles the exception now
                                                           // if it would occur later on
            }
        } catch (StringIndexOutOfBoundsException Se) {// for when the user inputs less than 2
                                                      // characters
            System.out.println(
                            "WARNING: At least a single character followed by a single number is expected.");
            return; // returns the method after exception found so that the rest of the code
                    // won't execute
        } catch (NumberFormatException Ne) { // for unrecognizable commands
            System.out.println(
                            "WARNING: Command does not follow the format of Choice+Number(i.e. A0)");
            return;
        } catch (ArrayIndexOutOfBoundsException Ae) { // when the index entered would cause an
                                                      // exception
            System.out.println(
                            "WARNING: The number entered exceeds the number of available scores.");
            return;
        }

        sortedList = list;// refreshes sortedList back into original if it was sorted before
        switch (temp.charAt(0)) {
            case 'o': // optimal sorting
                HeapSort heapSort = new HeapSort(indexToSort);
                heapSort.sort(sortedList);//calls HeapSort's sort method
                displayData();
                break;

            case 'a':// adaptive sorting
                InsertionSort insertionSort = new InsertionSort(indexToSort);
                insertionSort.sort(sortedList);//calls InsertionSort's sort method
                displayData();
                break;

            case 'f':// fewer swaps sorting
                SelectionSort selectionSort = new SelectionSort(indexToSort);
                selectionSort.sort(sortedList);//calls SelectinSort's sort method
                displayData();
                break;

            default:
                System.out.println("Command unrecognizable. (" + input.charAt(0)
                                + "). Please enter O,A or F for first character.");
        }
    }

    /**
     * displayData method prints out the whole arraylist of student objects which eventually be one
     * line of output for each object
     */
    public void displayData() {
        System.out.println();
        for (Student s : sortedList) {
            s.print();
        }
    }


}
