import java.awt.*;

/**
 * Draws the components of the {@link HangMan} with this class
 */
public class HangMan {

    public void drawRope(Graphics2D g) {
        g.drawLine(200,0,200,60);
    }

    public void drawHead(Graphics2D g) {
        g.drawOval(175, 60, 50, 50);
    }

    public void drawBody(Graphics2D g) {
        g.drawLine(200, 110, 200, 150);
    }

    public void drawLeftArm(Graphics2D g) {
        g.drawLine(200, 135, 185, 120);
    }

    public void drawRightArm(Graphics2D g) {
        g.drawLine(200, 135, 215, 120);
    }

    public void drawLeftLeg(Graphics2D g) {
        g.drawLine(200, 150, 185, 165);
    }

    public void drawRightLeg(Graphics2D g) {
        g.drawLine(200, 150, 215, 165);
    }

}
