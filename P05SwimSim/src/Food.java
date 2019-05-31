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
 * Food Class handles food objects' initializing, randomizing coordinates and movement Also
 * calculates a distance to a food
 * 
 * @author Khai Bin
 * @version 4.1.2
 * @since 2017-05-10
 */
public class Food extends SimObject {

    /**
     * Default constructor Randomizes x y coordinates and loads a food image from images folder
     */
    public Food() {
        super("images" + java.io.File.separator + "FOOD.png");
    }

    /**
     * Constructor overloading Sets specified x y coordinates and loads a food image from images
     * folder
     * 
     * @param x passes in a x-coordinate of the food object
     * @param y passes in a y-coordinate of the food object
     */
    public Food(int x, int y) {
        super("images" + java.io.File.separator + "FOOD.png", x, y);
    }

    /**
     * update() method moves the food diagonally downwards towards the left then prints food on
     * screen
     */
    public void update() {
        if (x < 0)
            x = Main.WIDTH - 1; // wraparound for move left
        else
            x = x - 1;
        if (y > Main.HEIGHT - 1)
            y = 0; // wraparound for move down
        else
            y = y + 1;


        object.image(image, x, y);
    }

    /**
     * Resets the position of the food to the top of the screen, randomizes x-coordinates
     */
    public void getEaten() {
        remove = true;
        // x = Utility.randomInt(Main.WIDTH);// randomize new food
        // y = 0;// start from top
    }

}
