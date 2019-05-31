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
 * Hook Class handles hook objects' initializing, randomizing coordinates and
 * movement
 * Also checks the distance to a fish and catches it
 * @author Khai Bin
 * @version 4.1.2
 * @since 2017-05-10
 */
public class Hook {
    private PApplet processing;
    private int x;
    private int y;
    private PImage hookImage;
    
    /**
     * Default constructor
     * Randomizes x y coordinates and loads a hook image from images folder
     * @param object passes in a reference to a hook object
     */
    public Hook(PApplet object) {
        processing = object;
        x = Utility.randomInt(Main.WIDTH); // generate hook position
        y = Utility.randomInt(Main.HEIGHT);
        hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
    }
    
    /**
     * Constructor overloading
     * Sets specified x y coordinates and loads a hook image from images folder
     * @param object passes in a reference to a hook object
     * @param x passes in a x-coordinate of the hook object
     * @param y passes in a y-coordinate of the hook object
     */
    public Hook(PApplet object, int x, int y) {
        processing = object;
        this.x = x;
        this.y = y;
        hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
    }

    /**
     * update() method
     * moves the hook one spot up, prints hook with line
     */
    public void update() {
        if (y < 0)
            y = Main.HEIGHT - 1; // wraparound for move up
        else
            y = y - ((Main.HEIGHT + 50 - y) / 50);// formula for hook speed

        processing.image(hookImage, x, y);
        processing.line(x + 4, 0, x + 4, y - 12); // draws hook line
    }

    /**
     * Rewrites the x and y coordinates of the hook
     * @param mouseX passes in the x-coordinates linked all the way from Main
     * @param mouseY passes in the y-coordinates linked all the way from Main
     */
    public void handleClick(int mouseX, int mouseY) {
        x = mouseX; // reinitializes position based on mouse click
        y = mouseY;
    }

    /**
     * Checks for distance, if close enough, calls method to reset 
     * the fish position
     * @param fish passes in a fish object to call Fish class methods
     */
    public void tryToCatch(Fish fish) {
        if (fish.distanceTo(x, y) <= 40)
            fish.getCaught();//resets fish position
    }
}
