import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        boolean loop = true;


        while (loop) {
            printCommand();
            String temp = scnr.nextLine();
            String[] input = temp.trim().split(" ");
            
            int index = 2;
            String meaning = "";
            while (index < input.length) {
                meaning = meaning + input[index++] + " ";
            }

            switch (input[0]) {
                case "I":
                case "i":
                    try {
                        dictionary.insert(input[1], meaning);
                    } catch (IllegalArgumentException e) {
                        System.out.println("WARNING: failed to insert duplicate word: "+ input[1] +".");
                    }
                    break;

                case "L":
                case "l":
                    String word = input[1].toLowerCase();
                    String mean = dictionary.lookup(word);
                    if (mean == null) {
                        System.out.println("No definition found for the word " + word + ".");
                    } else {
                        System.out.println(word + " - " + mean);
                    }
                    break;

                case "A":
                case "a":
                    if (dictionary.getAllWords() != null) {
                        ArrayList<String> temp1 = dictionary.getAllWords();
                        for (int i = temp1.size()-2 ; i>=0; i--) {
                            temp1.set(i, temp1.get(i)+", ");
                        }
                        for (String s : temp1) {
                            System.out.print(s);
                        }
                        System.out.println();
                    } else {
                        System.out.println("Dictionary is empty.");                        
                    }
                    break;

                case "C":
                case "c":
                    System.out.println(dictionary.getWordCount());
                    break;

                case "Q":
                case "q":
                    loop = false;
                    break;

                default:
                    System.out.println("WARNING: Unrecognized command.");
            }
        }
    }

    private static void printCommand() {
        System.out.print("==================Dictionary =================\r\n" + 
                        "Enter 'I' to Insert a definition in the dictionary\r\n" + 
                        "Enter 'L' to Lookup a definition in the dictionary\r\n" + 
                        "Enter 'A' to print All the words in the dictionary\r\n" + 
                        "Enter 'C' to print the Count of all words in the dictionary\r\n" + 
                        "Enter 'Q' to quit the program\r\n" + 
                        "===========================================\r\n" + 
                        "Please enter your command:");
    }

}
