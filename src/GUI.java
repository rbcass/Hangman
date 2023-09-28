import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JPanel implements ActionListener {
    // size for the window
    private final int BOARD_WIDTH = 700, BOARD_HEIGHT = 500;
    private final Font gameFont, incorrectGuessesFont;
    private TextField guessTextField;
    private JButton submit;
    private JLabel incorrect;
    private String guess;
    private Word word;
    private final JPanel textPanel = new JPanel();
    private final JPanel letterPanel = new JPanel();
    private final GridBagConstraints c = new GridBagConstraints();
    private final ArrayList<String> wrongGuesses = new ArrayList<>();
    private final ArrayList<JLabel> letters = new ArrayList<>();
    public static boolean gameOver = false;
    public static boolean win = false;
    public static int wrongGuessCounter = 0;


    public GUI() {

        // change the size of the Font
        gameFont = new Font(getFont().getName(), Font.PLAIN, 35);
        incorrectGuessesFont = new Font(getFont().getName(), Font.PLAIN, 20);

        // set the size for the window
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        // create a layout for the different panels
    GridLayout layout = new GridLayout(2, 1);
        setLayout(layout);
        // create the frame for the game
        JFrame window = new JFrame("HANGMAN");
        // end the application when window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add this panel to the window
        window.add(this);
        // pack the window with the information of the GamePanel
        window.pack();
        // place the window in the middle of the screen
        window.setLocationRelativeTo(null);

        window.setVisible(true);
        window.setResizable(false);

        textPanel.setLayout(new GridBagLayout());

        add(textPanel);
        HangManPanel hangManPanel = new HangManPanel();
        add(hangManPanel);

        addUI();
    }

    /**
     * Add the {@link Word} to a new {@link JPanel}
     * so it can be drawn onscreen independent of the
     * other components
     */
    private void setWord() {
        word = new Word(Main.getWord());
        // set the label to "-" and the name to
        // the real letter, so that the letter
        // isn't showing in the beginning
        for (String l : word.getLetters()) {
            JLabel t = new JLabel("-");
            t.setName(l);
            t.setFont(gameFont);
            letters.add(t);
        }
        for (JLabel l : letters) {
            letterPanel.add(l);
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        textPanel.add(letterPanel, c);
    }

    /**
     * Add the {@link JLabel},{@link JButton} and {@link JTextField}
     * to the {@link JPanel}
     */
    private void addUI() {
        JLabel guess = new JLabel("Guess: ");
        guess.setFont(gameFont);
        c.weightx = 1;
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        textPanel.add(guess, c);

        guessTextField = new TextField();
        guessTextField.setFont(gameFont);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        textPanel.add(guessTextField, c);

        submit = new JButton("Submit Guess");
        submit.setFont(gameFont);
        // set Focusable to false so there is no selection box
        // showing around the button when it is pressed
        submit.setFocusable(false);
        submit.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        textPanel.add(submit, c);

        incorrect = new JLabel("Incorrect Guesses: ");
        incorrect.setFont(incorrectGuessesFont);
        c.gridwidth = 3;
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        textPanel.add(incorrect, c);

        setWord();
    }
    /**
     * Check if the guess is right or not
     * make the letter visible if true
     */
    private void checkGuess() {
        boolean check = false;
        for (JLabel l : letters) {
            if (l.getName().equals(guess)) {
                l.setText(l.getName()); // show the rel letter by setting the text to the name of the component
                word.getLetters().remove(l.getName()); // remove the letter from the original word list
                check = true;
            }
        }
        // add the wrong letter to the list
        if (!check) {
            wrongGuesses.add(guess);
            incorrect.setText("Incorrect Guesses: " + wrongGuesses);
            wrongGuessCounter++;
            if (wrongGuessCounter == 7) {
                gameOver = true;
            }
        }
        // check if the word list is empty to see if the player has guessed the word
        if(word.getLetters().isEmpty()){
            win = true;
        }
        // disable the submit button and text-field if game is over
        if(win || gameOver){
            submit.setEnabled(false);
            guessTextField.setFocusable(false);
            guessTextField.setEditable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // check if submit is pressed
        if (e.getSource() == submit) {
            // set the letter
            guess = guessTextField.getText();
            guess = guess.toUpperCase();

            if (wrongGuesses.contains(guess)) {  // check if guess was already tried
                System.out.println("ALREADY TRIED");
            } else if (guess.toCharArray().length > 1) {       // check if guess is longer than one letter
                System.out.println("PLEASE INSERT ONLY ONE LETTER");
            } else if (guess.matches("^.*[^A-Z].*$")) {     // check if it contains non-alphabetic chars
                System.out.println("PLEASE INSERT ONLY LETTERS");
            } else if (guess.equals("")) {
                System.out.println("PLEASE INSERT A LETTER");
            } else {
                checkGuess();
            }
            // empty the text-field
            guessTextField.setText("");
        }
    }
}
