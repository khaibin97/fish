//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P4SwimSim
// Files: Main.java, SwimSimulation.java, Fish.java, Food.java,
// Hook.java
// Course: CS300
//
// Author: Khai Bin Woon
// Email: woon2@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////////////////////////////////////////////////////////////////

/**
 * Fish Class handles fish objects' initializing, randomizing coordinates and movement
 * 
 * @author Khai Bin
 * @version 4.1.2
 * @since 2017-05-10
 */
public class Fish extends SimObject {

    /**
     * Default constructor Randomizes x y coordinates and loads a fish image from images folder
     */
    public Fish() {
        super("images" + java.io.File.separator + "FISH.png");
    }

    /**
     * Constructor overloading Sets specified x y coordinates and loads a fish image from images
     * folder
     * 
     * @param x passes in a x-coordinate of the fish object
     * @param y passes in a y-coordinate of the fish object
     */
    public Fish(int x, int y) {
        super("images" + java.io.File.separator + "FISH.png", x, y);
    }

    /**
     * update() method moves the fish towards the right then prints fish on screen
     */
    public void update() {
        if (x > Main.WIDTH - 1)
            x = 0; // wraparound
        else
            x = x + 1; // movement

        object.image(image, x, y);
    }

    public void tryToInteract(SimObject other) {
        if (other instanceof Food) {
            if (other.distanceTo(x, y) <= 40) {
                ((Food) other).getEaten();
            }
        }
    }

    /**
     * Resets the position of the fish to the left edge of the screen, randomizes y-coordinates
     */
    public void getCaught() {
        x = 0; // reinitializes the fish to the left with
        y = Utility.randomInt(Main.HEIGHT);// randomized y-coordinates
    }

}
