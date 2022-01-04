import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Creates a panel with an image on it
 *
 * Texas Yahtzee
 * Sources: Horstmann examples
 *
 * @version v1.0 3/26/20
 * @author Simon Forinash
 */
public class ImagePanel extends JPanel{

    private BufferedImage image;

    /**
     * reads in the desired image based on its name
     *
     * @param picture  string for the name of an image
     */
    public ImagePanel(String picture) {
        // try/catch just in case picture is not an image in the directory
        try {
            image = ImageIO.read(new File(picture));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    /**
     * draws the image to the panel
     *
     * @param g  allows user to draw image
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, this.getWidth()/2 - this.getWidth()/6, 0, this.getWidth()/3,
                this.getHeight(), this);
    }
}