
public class Main {
    
    public static void main(String[]args) {
        

        int tHeight = 8; //tank height, change here for diff param
        int tWidth = 32; //tank width
        int fishNum = 3; //number of fishes, change here
        int foodNum = 6;
        int hookNum = 1;
        char w='~';
        String food = "*";
        String hook = "J";
        char t[][]= new char[tHeight][tWidth];
        fillTank(t,w);
        
        int fishPos[][] = new int [fishNum][2];
        int foodPos[][] = new int [foodNum][2];
        int hookPos[][] = new int [hookNum][2];
        fishPos = generateRandomPositions(fishPos.length,t[0].length,t.length);//generate fish
        foodPos = generateRandomPositions(foodPos.length,t[0].length,t.length);//generate food
        hookPos = generateRandomPositions(hookPos.length,t[0].length,t.length);//generate hook
        
        for ( int i = 0 ; i < foodPos.length ; i++) { //repeat for number of food
            placeObjectInTank(food,t,foodPos[i][0],foodPos[i][1]);
        }
        for ( int i = 0 ; i < hookPos.length ; i++) { //repeat for number of hook
            placeObjectInTank(hook,t,hookPos[i][0],hookPos[i][1]);
        }
        for ( int i = 0 ; i < fishPos.length ; i++) { //repeat for number of fish
            placeFishInTank(t,fishPos[i][0],fishPos[i][1]);
        }
        renderTank(t);   // show tank
        
        // repeat forever:
        while( true ) {
            moveAllFish(fishPos,t[0].length,t.length); // make it swim
            moveAllObjects(foodPos,-1,1,t[0].length,t.length); //move food
            moveAllObjects(hookPos,0,-1,t[0].length,t.length); //move hook
            fillTank(t,w);
            

            for ( int i = 0 ; i < foodPos.length ; i++) { //repeat for number of food
                placeObjectInTank(food,t,foodPos[i][0],foodPos[i][1]);
            }
            for ( int i = 0 ; i < hookPos.length ; i++) { //repeat for number of hook
                placeObjectInTank(hook,t,hookPos[i][0],hookPos[i][1]);
            }
            for ( int i = 0 ; i < fishPos.length ; i++) { //repeat for number of fish
                placeFishInTank(t,fishPos[i][0],fishPos[i][1]);
            }
            renderTank(t);
            
        }


    }
    
    public static void fillTank(char[][] tank, char water) {
        for(int height=0 ; height<tank.length ; height++) {
            for(int width=0 ; width<tank[0].length ; width ++) {
                tank[height][width]=water;
                
            }
        }
    }
    
    public static void renderTank(char[][] tank) {
        for(int height=0; height<tank.length;height++) {
            for(int width=0; width<tank[0].length; width ++) {
                System.out.print(tank[height][width]);
            }
            System.out.println();
        }
        Utility.pause(200);
        System.out.println("\n\n\n");
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
    
    public static void moveAllFish (int[][] pos, int width, int height) {
        for ( int j = 0 ; j < pos.length ; j++ ) {
            if ( (pos[j][0]+1) >= width)
                pos[j][0] = pos[j][0] - width + 1 ;
            else
                pos[j][0] = pos[j][0] + 1;
        }
    }
    
    
    public static void placeObjectInTank(String object, char[][] tank, int column, int row) {
        // print hook or food
        int loop = object.length() - 1;
        
        while(loop >= 0) {  //"justify" to the right, "prints" rightmost char 1st
            tank [row][column--] = object.charAt(loop--);
        }
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
                    positions[k][0] = width - 1;
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
    
}
