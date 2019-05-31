//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:   P4SwimSim
// Files:   Main.java, SwimSimulation.java, Fish.java, Food.java,
//          Hook.java
// Course:  CS300
//
// Author:          Khai Bin Woon
// Email:           woon2@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////////////////////////////////////////////////////////////////

/**
 * Fish Class handles fish objects' initializing, randomizing coordinates and
 * movement
 * @author Khai Bin
 * @version 4.1.2
 * @since 2017-05-10
 */
public class Fish {
    private PApplet processing;
    private int x;
    private int y;
    private PImage fishImage;

    /**
     * Default constructor
     * Randomizes x y coordinates and loads a fish image from images folder
     * @param object passes in a reference to a fish object
     */
    public Fish(PApplet object) {
        processing = object;
        x = Utility.randomInt(Main.WIDTH); // randomizes x(and y) for each fish
        y = Utility.randomInt(Main.HEIGHT);
        fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
    }

    /**
     * Constructor overloading
     * Sets specified x y coordinates and loads a fish image from images folder
     * @param object passes in a reference to a fish object
     * @param x passes in a x-coordinate of the fish object
     * @param y passes in a y-coordinate of the fish object
     */
    public Fish(PApplet object, int x, int y) {
        processing = object;
        this.x = x;
        this.y = y;
        fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
    }

    /**
     * update() method
     * moves the fish towards the right
     * then prints fish on screen
     */
    public void update() {
        if (x > Main.WIDTH - 1)
            x = 0; // wraparound
        else
            x = x + 1; // movement

        processing.image(fishImage, x, y);
    }

    /**
     * Checks for distance, if close enough, calls method to reset 
     * the food position
     * @param food passes in a food object to call Food class methods
     */
    public void tryToEat(Food food) {
        if (food.distanceTo(x, y) <= 40)
            food.getEaten();
    }

    /**
     * Calculates a distance between 2 objects, in this case, 
     * used specifically for hook and fish
     * @param x passes in hook x-coordinates
     * @param y passes in hook y-coordinates
     * @return the distance between the objects
     */
    public float distanceTo(int x, int y) {
        return (float) Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2));
    }

    /**
     * Resets the position of the fish to the left edge of the screen,
     * randomizes y-coordinates
     */
    public void getCaught() {
        x = 0; // reinitializes the fish to the left with
        y = Utility.randomInt(Main.HEIGHT);// randomized y-coordinates
    }

}
