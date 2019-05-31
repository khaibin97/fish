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
 * Food Class handles food objects' initializing, randomizing coordinates and
 * movement
 * Also calculates a distance to a food
 * @author Khai Bin
 * @version 4.1.2
 * @since 2017-05-10
 */
public class Food {
    private PApplet processing;
    private int x;
    private int y;
    private PImage foodImage;

    /**
     * Default constructor
     * Randomizes x y coordinates and loads a food image from images folder
     * @param object passes in a reference to a food object
     */
    public Food(PApplet object) {
        processing = object;
        x = Utility.randomInt(Main.WIDTH); // generate coordinates
        y = Utility.randomInt(Main.HEIGHT);
        foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
    }

    /**
     * Constructor overloading
     * Sets specified x y coordinates and loads a food image from images folder
     * @param object passes in a reference to a food object
     * @param x passes in a x-coordinate of the food object
     * @param y passes in a y-coordinate of the food object
     */
    public Food(PApplet object, int x, int y) {
        processing = object;
        this.x = x; // generate coordinates
        this.y = y;
        foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
    }

    /**
     * update() method
     * moves the food diagonally downwards towards the left
     * then prints food on screen
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


        processing.image(foodImage, x, y);
    }

    /**
     * Calculates a distance between 2 objects, in this case, 
     * used specifically for food and fish
     * @param x passes in fish x-coordinates
     * @param y passes in fish y-coordinates
     * @return the distance between the objects
     */
    public float distanceTo(int x, int y) {
        // distance formula
        return (float) Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2));
    }

    /**
     * Resets the position of the food to the top of the screen,
     * randomizes x-coordinates
     */
    public void getEaten() {
        x = Utility.randomInt(Main.WIDTH);// randomize new food
        y = 0;// start from top
    }

}
