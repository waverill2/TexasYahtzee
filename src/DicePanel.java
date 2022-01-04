import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Generates the dice UI for Texas Yahtzee
 *
 * Texas Yahtzee
 * No sources to cite
 *
 * @version v1.0 4/22/20
 * @author Texas Yahtzee Team
 */
public class DicePanel extends JPanel {
    private JPanel buttonPanel;
    private ArrayList<JToggleButton> userHand;
    private Hand hand;
    private ArrayList<Integer> handInts;
    private ArrayList<Icon> ImageIcons;
    private ArrayList<Boolean> keepDice;
    private boolean wild1;
    private boolean wild2;
    private Integer[] numSidesArray;
    private JComboBox numSidesBox1;
    private JComboBox numSidesBox2;

    /**
     * initialize the DicePage
     *
     * @param numSides  number of sides on each dice
     * @param hand arraylist with dice
     * @param player is either the players hand or computer
     */
    public DicePanel(int numSides, ArrayList<Integer> hand, boolean player, boolean lastTurn) {
        // set specs for DicePage
        numSidesArray = new Integer[numSides];
        for (int i = 1; i <= numSides; i++) {
            numSidesArray[i-1] = i;
        }
        System.out.println(numSidesArray[0]);
        setLayout(new BorderLayout());;
        if (player) {
            Dimension d = new Dimension(400, 200);
            setPreferredSize(d);
            // add(new JLabel("Choose Dice to Keep"), BorderLayout.NORTH);
            JLabel playerDiceLabel = new JLabel("Choose Dice to Keep");
            playerDiceLabel.setFont(new Font("Serif", Font.BOLD, 20));
            add(playerDiceLabel, BorderLayout.NORTH);
        }
        else {
            Dimension d = new Dimension(400, 120);
            setPreferredSize(d);
            //add(new JLabel("Dealer's Dice"), BorderLayout.NORTH);
            JLabel dealerDiceLabel = new JLabel("Dealer's Dice");
            if (lastTurn) {
                dealerDiceLabel = new JLabel("Your Dice");
            }
            dealerDiceLabel.setFont(new Font("Serif", Font.BOLD, 20));
            add(dealerDiceLabel, BorderLayout.NORTH);
                /*
                Dimension d = new Dimension(400, 200);
                setPreferredSize(d);
                add(new JLabel("Your Dice"), BorderLayout.NORTH);*/
        }

        handInts = hand;

        // Label to tell the user to choose dice
        JLabel chooseDice = new JLabel("Choose dice to keep:");
        //add(chooseDice, BorderLayout.NORTH);
        // Initialize variables and load the images of dice
        userHand = new ArrayList<>();
        ImageIcons = new ArrayList<>();
        loadImages(numSides);
        buttonPanel = new JPanel();
        // handInts = new ArrayList<>();
        if (player) {
            setButtonPanel();
        }
        else {
            add(getDice());
        }
    }

    /**
     * load in all the images
     *
     * @param numSides  number of sides on the dice
     */
    private void loadImages(int numSides) {
        for (int i = 1; i <= numSides; i++) {
            ImageIcons.add(new ImageIcon(i + ".png"));
        }
    }

    /**
     * sets the panel with the correct buttons based on which dice are in the user's hand
     */
    public void setButtonPanel() {
        wild1 = false;
        wild2 = false;
        remove(buttonPanel);
        buttonPanel.removeAll();
        userHand.clear();
        JToggleButton button;
        //wild
        for (int i = 0; i < handInts.size(); i++) {
            if ((handInts.get(i)) > 12) {
                JPanel wildPanel = new JPanel();
                wildPanel.setLayout(new GridLayout(2, 1));
                JLabel label = new JLabel("WILD DICE");

                if (i == 0) {
                    wild1 = true;
                    numSidesBox1 = new JComboBox<>(numSidesArray);
                    numSidesBox1 = new JComboBox<>(numSidesArray);
                    wildPanel.add(numSidesBox1);
                }
                else {
                    wild2 = true;
                    numSidesBox2 = new JComboBox<>(numSidesArray);
                    numSidesBox2 = new JComboBox<>(numSidesArray);
                    wildPanel.add(numSidesBox2);
                }
                label.setFont(new Font("Serif", Font.BOLD, 30));
                userHand.add(new JToggleButton("WILD", true));
                wildPanel.add(label);

                buttonPanel.add(wildPanel);
            }
            else {
                button = new JToggleButton(ImageIcons.get(handInts.get(i) - 1));

                userHand.add(button);
                buttonPanel.add(userHand.get(i));
            }
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * finds if the user wants to keep the dice at a specified index
     *
     * @param index  index of the dice we are checking
     * @return  true if the die is selected, false if not
     */
    public boolean getBoolValue(int index) {
        return userHand.get(index).isSelected();
    }

    /**
     * Get the images of dice in the hand
     *
     * @return a JPanel with images of dice
     */
    public JPanel getDice() {
        JPanel fullPanel = new JPanel();
        fullPanel.setLayout(new BorderLayout());
        JPanel dicePanel = new JPanel();
        JLabel die;
        for (Integer handInt : handInts) {
            die = new JLabel(ImageIcons.get(handInt - 1));
            dicePanel.add(die);
        }
        fullPanel.add(dicePanel, BorderLayout.CENTER);
        return fullPanel;
    }

    /**
     *
     * @return boolean value of wild1
     */
    public boolean wildDice1() {
        return wild1;
    }

    /**
     *
     * @return boolean value of wild2
     */
    public boolean wildDice2() {
        return wild2;
    }


    /**
     *
     * @return number selected from wild dice
     */
    public int getWildValue1() {
        return (int) numSidesBox1.getSelectedItem();
    }

    /**
     *
     * @return number selected from wild dice
     */
    public int getWildValue2() {
        return (int) numSidesBox2.getSelectedItem();
    }
}