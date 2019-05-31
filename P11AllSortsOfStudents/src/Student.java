
/**
 * Student Class handles each individual student object, which is essentially a single string Parses
 * the string to return data based on what would be needed to sort
 * 
 * @author Khai Bin Woon
 *
 */
public class Student {
    private String input; // original user input
    private String[] student; // String array for .split() methods
    private final String studentName; // To store the studentName as a constant, so that they wont
                                      // be changed when comparing data

    /**
     * Student constructor stores the user input and parses the data for future uses
     * 
     * @param input - based on the user's input
     */
    public Student(String input) {
        this.input = input;
        student = input.split(":");
        studentName = student[0].trim();// first element of the array would be the name
        parseData();
    }

    /**
     * parseData method converts the input string into arrays so that it can be individually
     * compared with other elements in other Student objects Also checks if all the scores are
     * numbers only
     */
    public void parseData() {
        student = student[1].split(",");// overwrites itself since the name is already stored in a
                                        // different field


        for (int i = 0; i < student.length; i++) {
            student[i] = student[i].trim();// removes any excess spaces

            // check if score contains alphabets.
            try {
                Integer.parseInt(student[i]);
            } catch (NumberFormatException e) {
                System.out.println(
                                "WARNING: Alphabets detected in the score section. Program may not run as expected."
                                                + " The wrong value" + student[i] + " at \""
                                                + student[0] + "\" is defaulted to 0.");
                student[i] = "0";
                // score will be set to 0 so that parseInt won't fail in sorting methods.
            }
        }

    }

    /**
     * getValue returns the specific element for comparison in the sorting methods
     * 
     * @param index - this index is based on the full string, 0 is name and 1 onwards is score
     * @return A specified String
     */
    public String getValue(int index) {
        if (index == 0) {
            return studentName.toLowerCase();// returns a new String for case insensitive comparison
        } else {
            return student[index - 1];// index was reduced by 1 since they are for different arrays,
                                      // student[] now only contains score and no names
        }
    }

    /**
     * To find out the number of scores entered for the student
     * 
     * @return the array size and hence the number of scores
     */
    public int getScoreAmount() {
        return student.length;
    }

    /**
     * print() method prints the elements of the array in the right order prints the information
     * into a single line
     */
    public void print() {
        System.out.printf("%-13s:", studentName);// prints the students name
        for (int i = 0; i < student.length; i++) {
            System.out.printf("%3s", student[i]); // prints the revised scores because some might
                                                  // changed to 0
            if (i + 2 > student.length) {
                // last element reached, no need for ","
            } else {
                System.out.print(",");
            }
        }
        System.out.println();// ends the line
    }
}
