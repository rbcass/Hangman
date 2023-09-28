import javax.swing.*;
import java.awt.*;


//drawing each hangman element with conditionals!
/**
 * Add the {@link HangMan} to this {@link JPanel}
 */
public class HangManPanel extends JPanel {
    HangMan hangMan = new HangMan();

    // Overrides method from JComponent
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3)); // makes the lines thicker
        g.setFont(new Font(getFont().getName(), Font.PLAIN, 35));
        if(GUI.wrongGuessCounter >= 1){
            hangMan.drawRope(g2);
        }
        if(GUI.wrongGuessCounter >=2){
            hangMan.drawHead(g2);
        }
        if(GUI.wrongGuessCounter >=3){
            hangMan.drawBody(g2);
        }
        if(GUI.wrongGuessCounter >=4){
            hangMan.drawLeftArm(g2);
        }
        if(GUI.wrongGuessCounter >=5){
            hangMan.drawRightArm(g2);
        }
        if(GUI.wrongGuessCounter >=6){
            hangMan.drawLeftLeg(g2);
        }
        if(GUI.wrongGuessCounter >=7){
            hangMan.drawRightLeg(g2);
        }
        if(GUI.gameOver){
            g.drawString("GAME OVER",100,200);
        }
        if(GUI.win){
            g.drawString("YOU WIN",120,200);
        }
        // important so the method gets repeated
        repaint();
    }
}
