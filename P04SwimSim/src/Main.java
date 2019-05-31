//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:   P4SwimSim
// Files:   Main.java, SwimSimulation.java, Fish.java, Food.java, Hook.java
// Course:  CS300 Fall 2017
//
// Author:          Khai Bin Woon
// Email:           woon2@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * P4SwimSim simulates a fish swimming with wrap around
 * 
 * A fish can eat food and a new food is added A hook can catch fish and a new fish is added
 * Positions of fish, food, hook can be randomly generated or read from a .ssd file A .ssd file is
 * also randomly choosen from available options specified in a .ssf file
 * 
 * @author Khai Bin
 * @version 4.1.2
 * @since 2017-05-10
 */
public class Main {
    public static final int HEIGHT = 600; // tank height
    public static final int WIDTH = 800; // tank width
    public static final int FISHNUM = 4; // default number of fishes
    public static final int FOODNUM = 6; // & etc.
    public static final int HOOKNUM = 1;
    private static SwimSimulation swimSim;

    /**
     * Main Class Starts simulation
     */
    public static void main(String[] args) {
        Utility.startSimulation();
    }

    /**
     * setup Method Creates a 'tank' to print objects on Creates an object reference to store
     * various data from data.processing
     * 
     * @param data passes references from Data inside SwimSim.jar
     */
    public static void setup(Data data) {

        data.processing.background(0, 255, 255); // fill tank with water
        data.processing.fill(0);// makes tank cyan

        SwimSimulation sim = new SwimSimulation(data.processing);
        swimSim = sim; // pass-by reference
    }

    /**
     * update Method Calls the update() in SwimSimulation Class
     */
    public static void update(Data data) {
        swimSim.update();
    }

    /**
     * onClick Method Gets mouse position information from SwimSim.jar Calls handleClick() from
     * SwimSimulation with position information
     */
    public static void onClick(Data data, int mouseX, int mouseY) {
        swimSim.handleClick(mouseX, mouseY);
        // rewrites the hook positions when mouse clicks
    }

}
