import javax.swing.*;
import java.awt.*;

/**
 * This class creates the main frame where texas Yahtzee is being played
 *
 * CPSC 224-01, Spring 2020
 * Texas Yahtzee
 * No sources to cite
 *
 * @version v1.0 4/10/20
 * @author Texas Yahtzee Team
 */
public class TexasYahtzee {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new TexasYahtzeeGUI("Texas Yahtzee");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

