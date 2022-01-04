import javax.swing.*;
import java.awt.*;

/**
 * Generates the main menu screen for Texas Yahtzee
 *
 * Programming Assignment #4
 * No sources to cite
 *
 * @version v1.0 4/22/20
 * @author Texas Yahtzee Team
 */
public class TitlePage extends JPanel {
    // Number of sides that the user will choose
    private int numSides;
    private Integer[] numSidesArray;
    private JComboBox<Integer> numSidesBox;

    /**
     * Generate a main menu screen for texas yahtzee
     */
    public TitlePage() {
        setBackground(Color.RED);
        Icon image = new ImageIcon("TexasPic.png");
        numSidesArray = new Integer[] {6, 8, 12};
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        //buttonPanel.setLayout(new GridLayout(3,2));
        //buttonPanel.add(new JLabel("Choose number of sides on dice"));
        JLabel sidesLabel= new JLabel("Choose number of sides on dice   ");
        sidesLabel.setFont(new Font("Serif", Font.BOLD, 30));
        buttonPanel.add(sidesLabel);
        mainPanel.add(new JLabel(image), BorderLayout.CENTER);
        JLabel label = new JLabel("Texas Yahtzee");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 34));
        mainPanel.add(label, BorderLayout.NORTH);
        setDropDownMenus();
        buttonPanel.add(numSidesBox);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.setBackground(Color.PINK);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.PINK);
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Set up a dropdown box for number of sides on the dice
     */
    private void setDropDownMenus() {
        numSidesBox = new JComboBox<>(numSidesArray);
    }

    /**
     *
     * @return the number of sides to be used in the game
     */
    public int getNumSides() {
        return (int) numSidesBox.getSelectedItem();
    }

}
