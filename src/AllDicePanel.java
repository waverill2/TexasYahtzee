import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * makes a panel with all of the dice
 *
 * CPSC 224-01, Spring 2020
 *
 * No sources to cite
 *
 * @version v1.0 4/16/20
 * @author Texas Yahtzee Team
 */
public class AllDicePanel extends JPanel {
    ArrayList<Icon> ImageIcons;
    ArrayList<Integer> playerHandInts;
    ArrayList<Integer> compHandInts;
    ArrayList<Integer> dealerHandInts;

    /**
     * creates a panel with the computer's, player's and dealer's dice
     *
     * @param playerHand  the player's hand
     * @param compHand  the computer's hand
     * @param dealerHand  the dealer's hand
     */
    public AllDicePanel(Hand playerHand, Hand compHand, DealerHand dealerHand) {
        setBackground(Color.RED);
        ImageIcons = new ArrayList<>();
        getHandInts(playerHand, compHand, dealerHand);
        loadImages();
        add(getDice());
    }

    /**
     * get the integer values of all hands
     *
     * @param playerHand  the player's hand
     * @param compHand  the computer's hand
     * @param dealerHand  the dealer's hand
     */
    private void getHandInts(Hand playerHand, Hand compHand, DealerHand dealerHand) {
        playerHandInts = playerHand.getallDice();
        compHandInts = compHand.getallDice();
        dealerHandInts = dealerHand.getallDice();
    }

    /**
     * load in all the images
     *
     */
    private void loadImages() {
        for (int i = 1; i <= 12; i++) {
            ImageIcons.add(new ImageIcon(i + ".png"));
        }
    }


    /**
     * Set the panel
     *
     * @return  panel organized with dice
     */
    public JPanel getDice() {
        JPanel fullPanel = new JPanel();
        fullPanel.setLayout(new GridLayout(3, 1));
        JPanel playerDicePanel = new JPanel();
        JPanel compDicePanel = new JPanel();
        JPanel dealerDicePanel = new JPanel();

        JLabel die;
        for (Integer player : playerHandInts) {
            die = new JLabel(ImageIcons.get(player - 1));
            playerDicePanel.add(die);
        }
        playerDicePanel.setBackground(Color.BLUE);
        for (Integer comp : compHandInts) {
            die = new JLabel(ImageIcons.get(comp - 1));
            compDicePanel.add(die);
        }
        compDicePanel.setBackground(Color.BLUE);
        for (Integer dealer : dealerHandInts) {
            die = new JLabel(ImageIcons.get(dealer - 1));
            dealerDicePanel.add(die);
        }
        dealerDicePanel.setBackground(Color.RED);
        fullPanel.add(compDicePanel);
        fullPanel.add(dealerDicePanel);
        fullPanel.add(playerDicePanel);
        return fullPanel;
    }
}
