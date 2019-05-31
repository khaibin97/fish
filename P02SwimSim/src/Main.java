//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:P2SwimSim           
// Files:Main.java           
// Course:CS300 Fall 2017          
//
// Author: Khai Bin Woon	          
// Email: woon2@wisc.edu           
// Lecturer's Name: Gary Dahl 
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Adam Azmil   
// Partner Email: binmohammeda@wisc.edu  
// Lecturer's Name: Gary Dahl 
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
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
    public static char water='~';
    public static String food = "*";
    public static String hook = "J";
    public static String fish = "><(('>";
    
    public static void main(String[]args) {
        
        Utility.startSimulation();
        /*Data data = new Data();
        Main.setup(data);
        while(true)
            Main.update(data);*/
        
    }
    
    public static void setup(Data data) {
    	
        data.tank= new char [tHeight][tWidth];
        fillTank(data.tank,water);
        data.processing.background(0,255,255); // fill tank with water
        data.processing.fill(0);/*makes tank cyan /** Placed in setup as it is part of setting up the 
        											* tank. And it is called early, and only needed to
        											* be called once.
        											*/
										
        //Initialise and generate fish,food,and hook positions
        data.fishPositions = new int [fishNum][2];
        data.foodPositions = new int [foodNum][2];
        data.hookPositions = new int [hookNum][2];
        data.fishPositions = generateRandomPositions(data.fishPositions.length,tWidth,tHeight);
        data.foodPositions = generateRandomPositions(data.foodPositions.length,tWidth,tHeight);
        data.hookPositions = generateRandomPositions(data.hookPositions.length,tWidth,tHeight);       
   
    }

    public static void update(Data data) {
        
        moveAllObjects(data.fishPositions,1,0,tWidth,tHeight); // make it swim
        moveAllObjects(data.foodPositions,-1,1,tWidth,tHeight); //move food
        moveAllObjects(data.hookPositions,0,(-(tHeight+50-data.hookPositions[0][1])/50)//speed factor
        				,tWidth,tHeight); //move hook
        
        data.processing.background(0,255,255);//fill tank (empty)
        
        //Places object correspond to its number
        for ( int i = 0 ; i < data.foodPositions.length ; i++) { //repeat for number of food
            placeObjectInTank(food,data.processing,data.foodPositions[i][0],data.foodPositions[i][1]);
        }
        for ( int i = 0 ; i < data.hookPositions.length ; i++) { //repeat for number of hook
            placeObjectInTank(hook,data.processing,data.hookPositions[i][0],data.hookPositions[i][1]);
        }
        for ( int i = 0 ; i < data.fishPositions.length ; i++) { //repeat for number of fish
            placeObjectInTank(fish,data.processing,data.fishPositions[i][0],data.fishPositions[i][1]);
        }      
        
    }

    //obsolete method
    public static void fillTank(char[][] tank, char water) {
        for(int height=0 ; height<tank.length ; height++) {
            for(int width=0 ; width<tank[0].length ; width ++) {
                tank[height][width]=water;
                
            }
        }
    }
    //obsolete method
    public static void renderTank(char[][] tank) {
        for(int height=0; height<tank.length;height++) {
            for(int width=0; width<tank[0].length; width ++) {
                System.out.print(tank[height][width]);
            }
            System.out.println();
        }
        /*Utility.pause(200);
        System.out.println("\n\n\n");*/
    }
    
    public static int[][] generateRandomPositions(int number, int width, int height) {
        int pos[][] = new int[number][2];
        
        for ( int i = 0 ; i < number ; i++ ) {
            pos[i][0] = Utility.randomInt(width);   // x-coordinate
        }        
        for ( int j = 0 ; j < number ; j++ ) {
            pos[j][1] = Utility.randomInt(height);  // y-coordinate
        }

        return pos;    
    }
    
    //obsolete method
    public static void placeFishInTank (char[][] tank , int x , int y) {
            tank [y][x--] = '>';               // >
        if ( x < 0) { x = x + tank[0].length; }//for wraparound effect
            tank [y][x--] = '\'';              // '>
        if ( x < 0) { x = x + tank[0].length; }
            tank [y][x--] = '(';               // ('>
        if ( x < 0) { x = x + tank[0].length; }
            tank [y][x--] = '(';               // (('>
        if ( x < 0) { x = x + tank[0].length; }
            tank [y][x--] = '<';               // <(('>
        if ( x < 0) { x = x + tank[0].length; }
            tank [y][x--] = '>';               // ><(('>
    }
    //obsolete method
    public static void moveAllFish (int[][] pos, int width, int height) {
        for ( int j = 0 ; j < pos.length ; j++ ) {
            if ( (pos[j][0]+1) >= width)
                pos[j][0] = pos[j][0] - width + 1 ;
            else
                pos[j][0] = pos[j][0] + 1;
        }
    }
    
    
    public static void placeObjectInTank(String object, PApplet processing, int column, int row) {
        
        PImage fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
        PImage hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
        PImage foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
        
        //checks the string "object" with fish,food,hook strings for its corresponding image
        if (object == fish)
        	processing.image(fishImage, column, row);
        if (object == hook) {
        	processing.image(hookImage, column, row);
        	processing.line(column+4, 0, column+4, row-12);
        }
        if (object == food)
        	processing.image(foodImage, column, row);

    }
    
    public static void moveAllObjects(int[][]positions, int dx, int dy, int width, int height) {
        for ( int k = 0 ; k < positions.length ; k++ ) {
            if (dx>0) {      //wrap around for moving right
                if ( (positions[k][0] + dx) >= width)           
                    positions[k][0] = 0;
                else     
                    positions[k][0] = positions[k][0] + dx;
            }
            if (dx<0) {     //wrap around for moving left
                if ( (positions[k][0]) <= 0)
                    positions[k][0] = width - 1 ;
                else
                    positions[k][0] = positions[k][0] + dx;
            }
        }
        
        for ( int j = 0 ; j < positions.length ; j++ ) {
            if (dy>0) {      //wrap around for moving down
                if ( (positions[j][1] + dy) >= height)           
                    positions[j][1] = 0;
                else     
                    positions[j][1] = positions[j][1] + dy;
            }
            if (dy<0) {     //wrap around for moving up
                if ( (positions[j][1]) <= 0)
                    positions[j][1] = height - 1;
                else
                    positions[j][1] = positions[j][1] + dy;
            }
        }
    }
    
    public static void onClick(Data data, int mouseX, int mouseY)
    {
    	//rewrites the hook positions when mouse clicks
    	data.hookPositions[0][0] = mouseX;
    	data.hookPositions[0][1] = mouseY;
    }
    
}
