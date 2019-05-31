//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P7GeneratingPhilosophy
// Files: Main.java, EvenNumberGenerator.java, NumberGenerator.java,
// Generator.java
// Course: CS300 Fall 2017
//
// Author: Khai Bin Woon
// Email: woon2@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;
import java.util.function.Function;

/**
 * P7GeneratingPhilosophy utilizes the given NextWikiLinkFunction to reach the /wiki/Philosophy
 * page. The generation starts with the user input and stops if reaches the page, or if there is no
 * link to be found.
 * 
 * @author Khai Bin
 * @version 1.1.2
 * @since 11-1-2017
 */
public class Main {

    /**
     * Main function
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String input;
        int counter = 0;

        System.out.print("Please enter a Wikipedia topic: ");
        input = scnr.nextLine();
        input = "/wiki/" + input.trim().replaceAll(" ", "_");// converts user input

        try {
            // for each loop generates a new Generator object for each iteration
            for (String i : new Generator<String>(100, input, new NextWikiLinkFunction())) {
                System.out.printf("%d: %s%n", counter++, i); // post-increment counter
                if (i.contains("Philosophy") || i.contains("FAILED")) {
                    throw new Exception(); // exits the for each loop altogether
                }
            }
        } catch (Exception e) {
        }
    }
}


/**
 * Class DoubleFunction defines apply method multiplies a number by 2
 * 
 * @author Khai Bin
 *
 */
class DoubleFunction implements Function<Integer, Integer> {

    /**
     * apply method takes in an Integer and multiplies it by 2 before returning another Integer
     * 
     * @param arg0 - Integer value passed in
     */
    @Override
    public Integer apply(Integer arg0) {
        return 2 * arg0;
    }
}


/**
 * AddExclamationFunction class defines apply method returns a string with ! added to the end
 * 
 * @author Khai Bin
 *
 */
class AddExclamationFunction implements Function<String, String> {

    /**
     * apply method adds a "!" string to the end of another string and returns it
     * 
     * @param t - A string passed in to be added a !
     */
    @Override
    public String apply(String t) {
        return (t + "!");
    }
}
