//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:P3SwimSim           
// Files:Main.java, SwimSimulation.java, Fish.java, Food.java, Hook.java          
// Course:CS300 Fall 2017          
//
// Author: Khai Bin Woon	          
// Email: woon2@wisc.edu           
// Lecturer's Name: Gary Dahl 
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Main {
    public static int tHeight = 600; //tank height
    public static int tWidth = 800; //tank width
    public static int fishNum = 4; //number of fishes
    public static int foodNum = 6;
    public static int hookNum = 1;
    private static SwimSimulation swimSim;
    
    public static void main(String[]args) {
    	
        Utility.startSimulation();
        
    }
    
    public static void setup(Data data) {
    	
        data.processing.background(0,255,255); // fill tank with water
        data.processing.fill(0);//makes tank cyan 
        
		SwimSimulation sim = new SwimSimulation(data.processing);
        swimSim = sim;
		
    }

    public static void update(Data data) {
        swimSim.update();
      
    }
    
    public static void onClick(Data data, int mouseX, int mouseY)
    {
    	swimSim.handleClick(mouseX, mouseY);
    	//rewrites the hook positions when mouse clicks
    }
    
}
