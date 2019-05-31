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
import java.util.function.Function;

/**
 * Generator class generates a sequence of generic type T with the first iteration and the number of
 * terms specified through constructor initialization
 * 
 * @author Khai Bin
 *
 * @param <T> - generic type of T, which would most likely be String
 */
public class Generator<T> implements Iterable<T>, Iterator<T> {
    private int numberOfIteration;// stores the amount generated
    private T firstIteration;// the starting number of the sequence
    private int currentNumber;// a counter field
    private Function<T, T> function;// an object to utilize methods of Function class

    /**
     * Initializes a new Generator to return generic type object each time it's next() method is
     * called. Subsequent objects returned in this way will call apply() to be modified.
     * <p>
     * After numberOfIteration numbers have been generated and returned from this next() method, the
     * generator will end: its hasNext() method will return false, and its next() method will throw
     * a NoSuchElementException when called after this point
     * 
     * @param numberOfIteration - the number of iterations it can go through
     * @param firstIteration - the first iteration that will be generated
     * @throws IllegalArgumentException - when numberOfInteration is negative
     */
    public Generator(int numberOfIteration, T firstIteration, Function<T, T> function)
                    throws IllegalArgumentException {
        if (numberOfIteration > 0) {// checks for negative
            this.numberOfIteration = numberOfIteration;
            currentNumber = numberOfIteration;
        } else {
            throw new IllegalArgumentException("WARNING: Negative number detected.");
        }
        this.firstIteration = firstIteration;
        this.function = function;
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
     * The apply() method of its related class' apply() method is called.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T oldCurrent;
        oldCurrent = firstIteration;
        firstIteration = function.apply(oldCurrent);// modifies the element for next element of the
                                             // sequence
        currentNumber--;
        return oldCurrent;
    }

    /**
     * Makes an Iterable compatible with an Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return this;
    }

}
