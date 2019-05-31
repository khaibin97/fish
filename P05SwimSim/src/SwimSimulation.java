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
    // private PApplet processing;
    private ArrayList<SimObject> tankObject = new ArrayList<SimObject>();
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
        SimObject.setProcessing(object);// pass the related object's reference

        openFile("FileOptions.ssf");

        if (scnr != null) { // choose a random file path only if file is opened
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

    }

    /**
     * update Method For each specific object, call their own Class' update(); Also calls
     * tryTo<VERB>(); methods to check for overlapping objects i.e. hook catch fish, fish eat food
     */
    public void update() {

        SimObject.object.background(0, 255, 255); // fill tank (empty)
        if (Utility.randomInt(100) < 5) // simulates 5.00% probability
            tankObject.add(new Food(Utility.randomInt(Main.WIDTH), 0));


        for (SimObject object : tankObject) {// updates all object
            object.update();

            // filters out hook,food and fish object so they do not interact with themselves
            if (object instanceof Fish)
                for (SimObject object2 : tankObject)
                    if (object2 instanceof Food)
                        object.tryToInteract(object2);
                    else if (object2 instanceof Hook)
                        object2.tryToInteract(object);

        }

        for (int i = tankObject.size() - 1; i >= 0; i--) {
            if (tankObject.get(i).shouldBeRemoved()) {
                tankObject.remove(i);
            }
        }

    }

    /**
     * handleClick method passes on the value from Main Class
     * 
     * @param mouseX coordinates
     * @param mouseY coordinates
     */
    public void handleClick(int mouseX, int mouseY) {
        for (SimObject hook : tankObject)
            if (hook instanceof Hook)
                ((Hook) hook).handleClick(mouseX, mouseY);
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
        int fishNum = 0;
        int foodNum = 0;
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

                if (objectName.equals("FOOD"))
                    foodNum = Integer.parseInt(temp2[1]);// Stores integers to check for errors
                else if (objectName.equals("FISH"))
                    fishNum = Integer.parseInt(temp2[1]);

                if (temp1.trim().isEmpty() || temp2[0].isEmpty() || temp2[1].isEmpty())// error
                                                                                       // checking
                    throw new NullPointerException(
                                    "WARNING: Missing specification for the number and initial positions of fishes,"
                                                    + " foods, or hook.");
            }

            // To parse for x and y coordinates
            if (temp1.contains(",")) {
                temp2 = temp1.toLowerCase().split(",");
                temp2[0] = temp2[0].trim();
                temp2[1] = temp2[1].trim();
                if (objectName.equals("FOOD")) {
                    tankObject.add(new Food(Integer.parseInt(temp2[0]),
                                    Integer.parseInt(temp2[1])));
                    foodCounter++;
                } else if (objectName.equals("FISH")) {
                    tankObject.add(new Fish(Integer.parseInt(temp2[0]),
                                    Integer.parseInt(temp2[1])));
                    fishCounter++;
                } else if (objectName.equals("HOOK")) {
                    tankObject.add(new Hook(Integer.parseInt(temp2[0]),
                                    Integer.parseInt(temp2[1])));
                }
            }

        }
        if (foodCounter != foodNum || fishCounter != fishNum)// check for errors
            throw new ArrayIndexOutOfBoundsException("WARNING: Number of " + objectName
                            + " does not match number of " + objectName + " positions.");

    }

    /**
     * generateObjects() method initializes the object array references
     */
    private void generateObjects() {
        for (int i = 0; i < Main.HOOKNUM; i++)// create a reference point for each fish object
            tankObject.add(new Hook());
        for (int i = 0; i < Main.FISHNUM; i++)// create a reference point for each fish object
            tankObject.add(new Fish());
        for (int i = 0; i < Main.FOODNUM; i++)// same as fish
            tankObject.add(new Food());
    }

}
