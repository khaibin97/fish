import java.util.Scanner;

public class Main { 
    public static void main(String[] args) {
        // puzzle initialization
        final int PUZZLE_HEIGHT = 5; // number of disks in the puzzle
        final int PUZZLE_WIDTH = 3; // number of rods in the puzzle
        final String ROD_LETTERS = "QWERTYUIOP".substring(0, PUZZLE_WIDTH); // labels for rods
        
        Scanner in = new Scanner(System.in);
        TowerOfHanoi hanoi = new TowerOfHanoi(PUZZLE_WIDTH, PUZZLE_HEIGHT);
        String rodLabelSpaceing = ""; // used to space ROD X labels
        for(int i=0;i<PUZZLE_HEIGHT-2;i++) rodLabelSpaceing += " ";
        boolean isDone = false;
        String input = null;
        
// SAVE FOR STEP 5:
        // prompt user to see puzzle solution
        System.out.print("[P]lay Puzzle or [S]ee Solution: ");
        input = in.nextLine();
        if(input.length() > 0 && input.toLowerCase().charAt(0) == 's') {
            System.out.println(hanoi.toString());
            hanoi.solve(PUZZLE_HEIGHT, 0, PUZZLE_WIDTH-1, 1);
            isDone = true;
            System.out.println("Puzzle Solved.");
        }
 
        // allow user to play with the puzzle
        while (!isDone) {
            // display rod labels
            System.out.println();
            for(int i=0;i<ROD_LETTERS.length();i++)
                System.out.print(rodLabelSpaceing + "ROD " + ROD_LETTERS.charAt(i) + rodLabelSpaceing);
            // display current state of puzzle
            System.out.print("\n\n" + hanoi.toString());
            // prompt player to enter their move
            System.out.print("\nEnter a two letter move (source rod then destination rod), or zz to quit: ");
            input = in.nextLine().toLowerCase();
 
            if (input.length() != 2) System.out.println("WARNING: A move should consist of two letters.");
            else if(input.equals("zz")) isDone = true;
            else {
                // convert input letters into rod indexes
                int src = ROD_LETTERS.indexOf(input.toUpperCase().charAt(0));
                int dst = ROD_LETTERS.indexOf(input.toUpperCase().charAt(1));
                // move disks between those rods
                if(src != -1 && dst != -1)
                    hanoi.moveDisk(src,dst);
                else
                    System.out.println("WARNING: Valid rod letters include only: " + ROD_LETTERS);
                // check whether the puzzle has been solved yet
                if(hanoi.isSolved()) {
                    System.out.println("\nCongratulations, you solved the puzzle!\n");
                    System.out.print(hanoi.toString());                    
                    isDone = true;
                }
            }
        }
    }
}