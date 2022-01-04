import java.util.ArrayList;

/**
 * This class holds dice object in a simulated dealer's hand
 * CPSC 224-01, Spring 2020
 * Texas Yahtzee
 * No sources to cite
 *
 * @version v1.0 4/10/20
 * @author Texas Yahtzee Team
 */
public class DealerHand extends Hand {

    // The dealer's hand
    ArrayList<Dice> hand = new ArrayList<>();

    /**
     * Generates the dealer's hand with a specified number of dice
     * and sides on said dice
     *
     * @param numSides   The number of sides on each die
     */
    public DealerHand(int numSides) {
        super(false, numSides, false);
        hand = GetHand();
    }

    /**
     * Rolls all the dice in the dealers hand; to be used when the user deploys their 1 reroll of the communal dice
     */
    public void rollAllDice() {
        for (Dice dice : hand) {
            dice.rollDie();
        }
    }

    /**
     * Rolls the dice that corresponds to which turn it is
     *
     * @param turn  which turn it is
     */
    public void rollDice(int turn) {
        hand.get(turn-1).rollDie();
    }

    /**
     * Prints the dealer's hand (for testing)
     */
    public void printHand() {
        System.out.print("Dealer Hand: ");
        for (Dice dice : hand) {
            if (dice.getSideUp() > -1) {
                System.out.print(dice.getSideUp() + " ");
            }
        }
        System.out.println();
    }

    // This tests the dealer's hand
    public static void main(String[] args) {
        DealerHand dealer = new DealerHand(6);
        dealer.rollDice(1);
        dealer.printHand();
        System.out.println();
        dealer.rollDice(2);
        dealer.printHand();
        System.out.println();
        dealer.rollDice(3);
        dealer.printHand();
        System.out.println();
        dealer.rollAllDice();
        dealer.printHand();
    }
}
