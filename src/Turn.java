import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class administers a turn of Texas Yahtzee
 *
 * CPSC 224-01, Spring 2020
 *
 * No sources to cite
 *
 * @version v1.0 4/12/20
 * @author Texas Yahtzee Team
 */
public class Turn {

    private Hand playerHand;
    private DealerHand dealerHand;
    private int turn;
    private boolean dice1;
    private boolean dice2;
    private DicePanel playerPanel;
    private DicePanel dealerPanel;
    private JPanel turnPanel;
    private JPanel wholeDealerPanel;
    private int numSides;
    private PlayTexasYahtzee playTexasYahtzee;
    private boolean DealerRerolled;

    /**
     * Creates and initializes values for the turn object
     *
     * @param playerHand The player's hand
     * @param dealerHand The dealer's hand
     * @param numSides number of sides on the dice
     */
    public Turn(Hand playerHand, DealerHand dealerHand, int numSides) {
        this.numSides = numSides;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        turnPanel = new JPanel();
        wholeDealerPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(3, 2));
        wholeDealerPanel.setLayout(new GridLayout(2, 2));
        DealerRerolled = false;
    }

    public void setPlayTexasYahtzee(PlayTexasYahtzee pty) {
        playTexasYahtzee = pty;
    }

    /**
     * plays a round of texas yahtzee
     */
    public void playRound() {
        playerHand.modifyHand(true, true);
        turn = 0;
        rollNext();
        addDice();
    }

    /*
    public void runTurn() {

        rollNext();
        Scanner decision = new Scanner(System.in);
        playerHand.printHand();

        System.out.println("Enter a 'y' or 'n' to keep dice ");
        String keeping = decision.nextLine();
        setBooleans(keeping.charAt(0) == 'n', keeping.charAt(1) == 'n');
        playerHand.modifyHand(dice1, dice2);

        turn++;
    }*/

    /**
     * runs one roll in texas yahtzee
     */
    public void runTurn() {
        rollNext();
        // playerHand.printHand();
        playerHand.modifyHand(dice1, dice2);
        if (playerPanel.wildDice1()) {
            playerHand.setDice(0, playerPanel.getWildValue1());
        }
        if (playerPanel.wildDice2()) {
            playerHand.setDice(1, playerPanel.getWildValue2());
        }
    }

    /**
     * roll the dealer's next dice
     */
    public void rollNext() {
        // dealerHand.printHand();
        dealerHand.rollDice(turn+1);
        // dealerHand.printHand();
        turn +=1;
    }

    /**
     * Add the images/buttons of the dice in play
     */
    private void addDice() {
        // System.out.println(getPlayerHand());
        turnPanel.removeAll();
        wholeDealerPanel.removeAll();
        playerPanel = new DicePanel(numSides, getPlayerHand(), true, false);
        dealerPanel = new DicePanel(numSides, getDealerHand(), false, false);
        wholeDealerPanel.add(dealerPanel);
        turnPanel.add(wholeDealerPanel, BorderLayout.NORTH);
        turnPanel.add(playerPanel, BorderLayout.SOUTH);
        /**
         *
         *
         *
         * If button has not been pressed, set reroll button
         */

        if (!playTexasYahtzee.getPlayerRerolled() && dealerHand.WeNeedSideUp(2) > 0) {
            rerollDealer();
        }
    }

    /**
     * Add the images of all the dice for the final hand in a turn
     */
    private void addDiceFinal() {
        // System.out.println(getPlayerHand());
        //Get the components in the panel
        Component[] componentList = dealerPanel.getComponents();

//Loop through the components
  /*      for(Component c : componentList){

            //Find the components you want to remove
            if(c instanceof JButton){

                //Remove it
                wholeDealerPanel.remove(c);
            }
        }*/
        turnPanel.removeAll();
        playerPanel = new DicePanel(numSides, getPlayerHand(), false, true);

        turnPanel.add(dealerPanel, BorderLayout.NORTH);
        turnPanel.add(playerPanel, BorderLayout.SOUTH);
        turnPanel.validate();
        turnPanel.repaint();
    }

    /**
     * Set booleans for which dice to keep
     */
    public void setBooleans() {
        dice1 = !playerPanel.getBoolValue(0);
        dice2 = !playerPanel.getBoolValue(1);
    }

    /**
     * Play another turn and add panels to display new hands
     */
    public void nextTurn() {
        runTurn();
        addDice();
    }

    /**
     * Set the very final panel in a turn
     */
    public void finalTurn() {
        playerHand.modifyHandFinal(dice1,dice2);
        if (playerPanel.wildDice1()) {
            playerHand.setDice(0, playerPanel.getWildValue1());
        }
        if (playerPanel.wildDice2()) {
            playerHand.setDice(1, playerPanel.getWildValue2());
        }
        addDiceFinal();
    }

    /**
     *
     * @return the integer values of the player's hand
     */
    public ArrayList<Integer> getPlayerHand() {
        return playerHand.getallDice();
    }

    /**
     *
     * @return the integer values of the dealer's hand
     */
    public ArrayList<Integer> getDealerHand() {
        return dealerHand.getallDice();
    }

    /**
     *
     * @return the panel with this turns objects
     */
    public JPanel getTurnPanel() {
        return turnPanel;
    }

    /**
     *
     * sets the reroll button for dealer
     */
    public void rerollDealer() {

        JButton reroll = new JButton("Re-roll Dealer's dice");
        //reroll.setPreferredSize(new Dimension(1, 1));
        //reroll.setVerticalAlignment(SwingConstants.NORTH);
        reroll.setPreferredSize(new Dimension(4, 4));
        //reroll.setBounds(200, 500, 40, 40);
        wholeDealerPanel.add(reroll);
        reroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playTexasYahtzee.setDealerRerolled();
                //dealers dice are rerolled
                dealerHand.rollAllDice();
                // dealerPanel.remove(reroll);
                turnPanel.remove(wholeDealerPanel);
                turnPanel.remove(playerPanel);
                wholeDealerPanel.remove(reroll);
                wholeDealerPanel.remove(dealerPanel);
                dealerPanel = new DicePanel(numSides, getDealerHand(), false, false);
                wholeDealerPanel.add(dealerPanel);
                turnPanel.add(dealerPanel);
                turnPanel.add(playerPanel);
                turnPanel.validate();
                turnPanel.repaint();
            }
        });
    }
}
