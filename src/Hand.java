import java.util.ArrayList;


/**
 * This class holds dice object in a simulated hand
 * CPSC 224-01, Spring 2020
 * Texas Yahtzee
 * No sources to cite
 *
 * @version v1.0 4/10/20
 * @author Texas Yahtzee Team
 */
public class Hand {
    // Creates an arrayList of type Dice to simulate a Yahtzee hand
    private ArrayList<Dice> hand = new ArrayList<Dice>();
    boolean wild;

    /**
     Generates a hand with a specified number of dice
     and sides on said dice
     *
     * @param playerHand  True if the hand is for the player or computer, false if for the dealer
     * @param numSides  The number of sides on each die
     * @param wild Whether wild card was in hand or not
     */
    public Hand(boolean playerHand, int numSides, boolean wild) {
        Dice die;
        int numDice;
        if (playerHand == true) {
            numDice = 2;
        }
        else {
            numDice = 3;
        }
        for (int i = 0; i < numDice; i++) {
            die = new Dice(numSides, wild);
            hand.add(die);
        }
    }

    /**
     Returns the hand of dice
     *
     * @return hand  an ArrayList containing Dice objects
     */
    public ArrayList<Dice> GetHand() {
        return hand;
    }


    /**
     * Rolls all the dice in the hand
     */
    public void rollAllDice() {
        for (Dice dice : hand) {
            dice.rollDie();
        }
    }

    /**
     * modifies the hand based on boloean values; used for player and computer's hand
     *
     * @param reRollDiceOne  if true, the first dice in the hand is rerolled
     * @param reRollDiceTwo if true, the second dice in the hand is rerolled
     */
    public void modifyHand(boolean reRollDiceOne, boolean reRollDiceTwo) {
        if (reRollDiceOne) {
            hand.get(0).rollDie();
        }
        if (reRollDiceTwo) {
            hand.get(1).rollDie();
        }
    }

    /**
     * modifies the hand based on boloean values; used for player's final turn (no wilds)
     *
     * @param reRollDiceOne  if true, the first dice in the hand is rerolled
     * @param reRollDiceTwo if true, the second dice in the hand is rerolled
     */
    public void modifyHandFinal(boolean reRollDiceOne, boolean reRollDiceTwo) {
        if (reRollDiceOne) {
            hand.get(0).rollDieFinal();
        }
        if (reRollDiceTwo) {
            hand.get(1).rollDieFinal();
        }
    }


    /**
     * modifies the hand based on boloean values; made for dealer's hand
     *
     * @param reRollDiceOne  if true, the first dice in the hand is rerolled
     * @param reRollDiceTwo if true, the second dice in the hand is rerolled
     **/
    /*
    public void modifyHand(boolean reRollDiceOne, boolean reRollDiceTwo, boolean reRollDiceThree) {
        if (reRollDiceOne) {
            hand.get(0).rollDie();
        }
        if (reRollDiceTwo) {
            hand.get(1).rollDie();
        }
        if (reRollDiceThree) {
            hand.get(2).rollDie();
        }
    }*/

    /**
     * Prints the hand (for testing)
     */
    public void printHand() {
        System.out.print("Your hand: ");
        for (Dice dice : hand) {
            System.out.print(dice.getSideUp() + " ");
        }
        System.out.println();
    }

    /**
     * Gets the value of a dice in the hand
     *
     * @param index the index of the dice we want
     * @return  the value of the dice at the specified index
     */
    public int WeNeedSideUp(int index)
    {
       return hand.get(index).getSideUp();
    }

    /**
     * Get all of the integer values of the dice in a hand
     *
     * @return  integer values of the dice in a hand
     */
    public ArrayList<Integer> getallDice() {
        ArrayList<Integer> dice = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getSideUp() > -1) {
                dice.add(hand.get(i).getSideUp());
            }
        }
        return dice;
    }

    /**
     * Will place values into hand
     * @param index where to place value in hand
     * @param value dice value to put into hand
     */
    public void setDice(int index, int value) {
        hand.get(index).setValue(value);
    }
    // Tests the hand
    public static void main(String[] args) {
        Hand hand = new Hand(true, 6, true);
        hand.modifyHand(true, true);
        hand.printHand();
    }
}