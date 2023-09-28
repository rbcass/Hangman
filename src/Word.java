import java.util.ArrayList;

public class Word {
    // the selected word
    private final String word;
    // the list of letters from the selected word
    private final ArrayList<String> letters = new ArrayList<>();

    public Word(String word){
        this.word = word;
        splitWord();
    }

    /**
     * Split the word in to each of the letters
     * and add it to the {@link ArrayList} of {@link String}
     */
    private void splitWord(){
        for (Character c : word.toCharArray()){
            String l = c.toString().toUpperCase();
            letters.add(l);
        }
    }

    /**
     * Get the list with the letters
     * @return {@link ArrayList}
     */
    public ArrayList<String> getLetters(){
        return letters;
    }
}
