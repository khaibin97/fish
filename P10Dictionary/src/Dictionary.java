import java.util.ArrayList;

public class Dictionary {
    
    private DefinitionNode root; // the root definition in the BST
    
    /**
     * Inserts a new word along with its meaning into the dictionary.
     * @param word The word to be inserted
     * @param meaning The meaning of the word being inserted
     * @throws IllegalArgumentException when the word is already in this dictionary
     */
    public void insert(String word, String meaning) throws IllegalArgumentException {
        // implement using the DefinitionNode's insertHelper() method
        DefinitionNode newNode = new DefinitionNode(word, meaning);
        if (root == null) {
            root = newNode;
        } else {
            root.insertHelper(newNode);
        }
    }
     
    /**
     * Searches for the definition of a word, and then return that word's meaning.
     * @param word The word that is being searched for
     * @return The meaning of the word, or null if the word cannot be found.
     */
    public String lookup(String word) {
        // implement using the DefinitionNode's lookupHelper() method
        String temp = null;
        if (root != null) {
            temp = root.lookupHelper(word);
        }
        return temp;
     }
     
    /**
     * Computes the number of words that are currently in this dictionary.
     * @return That number of words
     */
    public int getWordCount() {
        // implement using the DefinitionNode's getWordCountHelper() method
        if (root != null) {
            return root.getWordCountHelper();
        } else {
            return 0;
        }
    }
     
    /**
     * Computes a list of all of the words that are currently in this dictionary.
     * @return That list of all the words
     */
    public ArrayList<String> getAllWords() {
        // implement using the DefinitionNode's getAllWordsHelper() method
        if (root!= null) {
            return root.getAllWordsHelper();
        } else {
            return null;
        }
        
    }
}
