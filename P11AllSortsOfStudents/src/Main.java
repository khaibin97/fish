//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P11AllSortsOfStudents
// Files: Main.java
// Course: CS300
//
// Author: Khai Bin Woon
// Email: woon2@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.Scanner;

/**
 * Driver Class
 * 
 * @author Khai Bin Woon
 *
 */
public class Main {

    public static void main(String[] args) {
        boolean loop = true;
        Scanner scnr = new Scanner(System.in);
        Database data = null;
        String command;

        while (loop) {

            System.out.print("\nEnter the name of your student data file: ");
            data = new Database(scnr.nextLine());

            try {
                data.displayData(); // null pointer when file is not loaded
                loop = false; // exits loop if no exception is thrown
            } catch (NullPointerException e) {
                System.out.println("Please try again."); // message shown when file is not loaded
            }
        }

        loop = true; // new loop
        while (loop) {
            System.out.print("=======================Student Database Loaded=======================\n"
                             + "Pick a choice below followed by the number you want to sort\n"
                             + "0 for alphabetical, 1 to n for the scores.\n"
                             + "Enter 'O' for Optimal sorting for any data(Heap sort)\n"
                             + "Enter 'A' for Adaptive sorting for nearly sorted data(Insertion sort)\n"
                             + "Enter 'F' for Fewest swap sorting to save memory(Selection sort)\n"
                             + "Enter 'Q' to quit the program\n"
                             + "======================================================================\n"
                             + "Please enter your command: ");
            command = scnr.nextLine();

            // check if user entered the quit command
            if (!command.equals("") && command.trim().toLowerCase().substring(0, 1).equals("q")) {
                loop = false;
                System.out.println("Program Terminated.");
            } else {
                data.selectSort(command); // sorts the loaded file based on user input
            }
        }
    }

}
