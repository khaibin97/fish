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

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * SwimSimulation class initializes object positions and handle errors Positions can be randomly
 * generated or specified from .ssd and .sff files
 * 
 * @author Khai Bin
 * @version 4.1.2
 * @since 2017-05-10
 */
public class SwimSimulation {
    private PApplet processing;
    private Fish[] fish = new Fish[Main.FISHNUM];
    private Food[] food = new Food[Main.FOODNUM];
    private Hook hook;
    private Scanner scnr;

    /**
     * Class Constructor Gets a PApplet object reference Opens a file to get file path, Reads that
     * .sdd file (to generate and initialize object arrays (in readFile();)) Error Handling with
     * specific messages
     * 
     * @param object passes references from a PApplet object which 'contains' information from
     *        SwimSim.jar
     */
    public SwimSimulation(PApplet object) {
        processing = object; // pass the related object's reference

        openFile("FileOptions.ssf");
        String fileName = genFilePath(); // reads the opened file and returns a random file path

        openFile(fileName);// opens the .sdd file

        try {

            readFile();

        } catch (Exception e) {
            String error = e.getMessage();

            if (error != null && !error.isEmpty())
                System.out.println(e.getMessage());// prints thrown error messages
            else
                System.out.println("WARNING: Failed to load objects and positions from file.");

            generateObjects(); // if exception was caught, use default values
        }

        closeFile();
    }

    /**
     * update Method For each specific object, call their own Class' update(); Also calls
     * tryTo<VERB>(); methods to check for overlapping objects i.e. hook catch fish, fish eat food
     */
    public void update() {

        processing.background(0, 255, 255); // fill tank (empty)

        for (int i = 0; i < food.length; i++) {// updates all food
            food[i].update();
        }
        for (int i = 0; i < fish.length; i++) { // updates all fish
            fish[i].update();

            hook.tryToCatch(fish[i]);// hook tries to catch all fish
            for (int j = 0; j < food.length; j++) { // tries to eat all food
                fish[i].tryToEat(food[j]);
            }

        }

        hook.update();// move hook
    }

    /**
     * handleClick method passes on the value from Main Class
     * 
     * @param mouseX coordinates
     * @param mouseY coordinates
     */
    public void handleClick(int mouseX, int mouseY) {
        hook.handleClick(mouseX, mouseY);
    }

    /**
     * openFile() method Opens a file to 'read' Handles an error for invalid file path
     * 
     * @param name File path i.e. "./FileOptions.ssf"
     */
    private void openFile(String name) {
        try {
            scnr = new Scanner(new File(name));
        } catch (Exception e) {
            System.out.println("WARNING: Could not find or open the " + name + " file.");
            generateObjects();// if exception was caught, use default values
        }
    }

    /**
     * closeFile() method closes file
     */
    private void closeFile() {
        scnr.close();
    }

    /**
     * genFilePath() method returns a random file path Reads the text line by line, rectify mistakes
     * in file path split them into individual Strings and store in array
     * 
     * @return a random file path selected from the array
     */
    private String genFilePath() {
        ArrayList<String> filePath = new ArrayList<String>();

        while (scnr.hasNextLine()) {
            String[] temp = scnr.nextLine().replace('\\', File.separatorChar)
                            .replace('/', File.separatorChar).split(";");
            for (String s : temp) {
                s = s.trim();
                filePath.add(s);
            }
        }

        return filePath.get(Utility.randomInt(filePath.size()));
    }

    /**
     * readFile() method Reads the file and proceed to parse the information read Identify the
     * object to store data into and create the object array Store the position coordinates into
     * their relative array calls generateRandomObject() to initialize the object array created
     * 
     * @throws Exception for wrong formatting of contents of .ssd file with specific error message
     */
    private void readFile() throws Exception {
        String objectName = "";
        int[] foodX = null;
        int[] foodY = null;
        int[] fishX = null;
        int[] fishY = null;
        int[] hook = new int[2];
        String temp1;
        String[] temp2;
        int foodCounter = 0;
        int fishCounter = 0;

        while (scnr.hasNextLine()) {
            temp1 = scnr.nextLine();

            // To parse for object name and number
            if (temp1.contains(":")) {
                temp2 = temp1.toUpperCase().split(":");
                objectName = temp2[0].trim();
                temp2[1] = temp2[1].trim();

                if (temp1.trim().isEmpty() || temp2[0].isEmpty() || temp2[1].isEmpty())// error
                                                                                       // checking
                    throw new Exception(
                                    "WARNING: Missing specification for the number and initial positions of fishes,"
                                                    + " foods, or hook.");

                // initialize array sizes for objects and x y coordinates.
                if (objectName.equals("FOOD")) {
                    this.food = new Food[Integer.parseInt(temp2[1])];
                    foodX = new int[Integer.parseInt(temp2[1])];
                    foodY = new int[Integer.parseInt(temp2[1])];
                } else if (objectName.equals("FISH")) {
                    this.fish = new Fish[Integer.parseInt(temp2[1])];
                    fishX = new int[Integer.parseInt(temp2[1])];
                    fishY = new int[Integer.parseInt(temp2[1])];
                }
            }

            // To parse for x and y coordinates
            if (temp1.contains(",")) {
                temp2 = temp1.toLowerCase().split(",");
                temp2[0] = temp2[0].trim();
                temp2[1] = temp2[1].trim();
                if (objectName.equals("FOOD")) {
                    foodX[foodCounter] = Integer.parseInt(temp2[0]);
                    foodY[foodCounter] = Integer.parseInt(temp2[1]);
                    foodCounter++;
                } else if (objectName.equals("FISH")) {
                    fishX[fishCounter] = Integer.parseInt(temp2[0]);
                    fishY[fishCounter] = Integer.parseInt(temp2[1]);
                    fishCounter++;
                } else if (objectName.equals("HOOK")) {
                    hook[0] = Integer.parseInt(temp2[0]);
                    hook[1] = Integer.parseInt(temp2[1]);
                }
            }

        }
        if (foodCounter != food.length || fishCounter != fish.length)//check for errors
            throw new Exception("WARNING: Number of " + objectName + " does not match number of "
                            + objectName + " positions.");

        generateObjects(fishX, fishY, foodX, foodY, hook);// generate objects with data from .ssd
                                                          // file

    }
    
    /**
     * generateObjects() method initializes the object array references
     */
    private void generateObjects() {
        fish = new Fish[Main.FISHNUM]; //initializes the size of array
        food = new Food[Main.FOODNUM];

        hook = new Hook(processing);
        for (int i = 0; i < fish.length; i++)// create a reference point for each fish object
            fish[i] = new Fish(processing);
        for (int i = 0; i < food.length; i++)// same as fish
            food[i] = new Food(processing);
        // only 1 hook
    }

    /**
     * method overloading
     * this generateObjects()method initializes the object array references with the data
     * obtained from .ssd file
     * @param fishX coordinates array of fish from .ssd
     * @param fishY coordinates array of fish from .ssd
     * @param foodX coordinates array of food from .ssd
     * @param foodY coordinates array of food from .ssd
     * @param hook coordinates array of hook from .ssd
     */
    private void generateObjects(int[] fishX, int[] fishY, int[] foodX, int[] foodY, int[] hook) {
        
        this.hook = new Hook(processing, hook[0], hook[1]);

        for (int i = 0; i < fish.length; i++)// create a reference point for each fish object
            fish[i] = new Fish(processing, fishX[i], fishY[i]);
        for (int i = 0; i < food.length; i++)// same as fish
            food[i] = new Food(processing, foodX[i], foodY[i]);

    }
}
