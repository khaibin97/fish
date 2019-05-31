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
//////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenNumberGenerator is a class that creates a sequence of even numbers the amount generated and
 * the starting sequence is also needed to be specified
 * 
 * @author Khai Bin
 *
 */
public class EvenNumberGenerator implements Iterable<Integer>, Iterator<Integer> {
    private int numberOfEvens;// stores the amount generated
    private Integer firstEven;// the starting number of the sequence
    private int currentNumber;// a counter field

    /**
     * Initializes a new EvenNumberGenerator to return a single even number each time it's next()
     * method is called. The first even number returned in this way is firstEven. Subsequent even
     * numbers returned in this way will be the smallest even number that is larger than the
     * previous.
     * <p>
     * After numberOfEvens numbers have been generated and returned from this next() method, the
     * generator will end: its hasNext() method will return false, and its next() method will throw
     * a NoSuchElementException when called after this point.
     * 
     * @param numberOfEvens - the number of evens that can be generated
     * @param firstEven - the first and smallest even that will be generated
     * @throws IllegalArgumentException - when numberOfEvens is negative, or when firstEven is not
     *         an even number
     */
    public EvenNumberGenerator(int numberOfEvens, Integer firstEven)
                    throws IllegalArgumentException {
        if (numberOfEvens > 0) { // checks for negative
            this.numberOfEvens = numberOfEvens;
            currentNumber = numberOfEvens; // to count the number of iterations left
        } else { // if negative
            throw new IllegalArgumentException("WARNING: Negative number detected.");
        }
        if (firstEven % 2 == 0) { // checks if its even
            this.firstEven = firstEven;
        } else { // if odd
            throw new IllegalArgumentException("WARNING: Odd number detected.");
        }
    }

    /**
     * hasNext returns true if the iteration goes on
     */
    @Override
    public boolean hasNext() {
        return currentNumber > 0;// checks if there are still iterations left
    }

    /**
     * next() returns the "current" element and modifies the current field for the next iteration.
     * This method returns current even number and sets next even number.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer oldCurrent;
        oldCurrent = firstEven; // set "current"
        firstEven = firstEven + 2;// for next iteration
        currentNumber--;// counter
        return oldCurrent;
    }

    /**
     * Makes an Iterable compatible with an Iterator
     */
    @Override
    public Iterator<Integer> iterator() {
        return this;
    }

}
