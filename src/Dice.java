import java.util.Random;

/**
 * This class creates Dice objects that can be used for
 * any dice related game
 * CPSC 224-01, Spring 2020
 * Programming Assignment #3
 * No sources to cite
 *
 * @version v2.0 2/13/20
 * @author Texas Yahtzee Team
 */
public class Dice {
    private int sideUp;
    private int numSides;
    private boolean wild;
    private int wildDiceChance;

    /**
     * Generates a Dice object with a specified number of sides
     *
     * @param sides  number of sides for each die
     *
     */
    public Dice(int sides, boolean wild) {
        numSides = sides;
        // Get a wild dice every 
        wildDiceChance = 20;
        sideUp = -1;
        this.wild = wild;
    }

    /**
     * Generates a Dice object with 6 sides
     */
    public Dice() {
        numSides = 6;
    }

    /**
     Rolls the dice; a random number is generated for its value
     *
     * @return faceValue  value on the face up on our virtual die
     **/
    private int newSideUp()
    {
        rollDie();
        return sideUp;
    }

    /**
     Returns the current face value of a die
     *
     * @return faceValue  value on the face up on our virtual die
     **/
    public int getSideUp() {
        return sideUp;
    }

    /**
     Rolls and returns the value of a die
     *
     * @return RollDie()  returns the face value of a rolled die
     **/
    public int getNewSideUp() {
        return newSideUp();
    }

    /**
     * Rolls the die
     */
    public void rollDie() {
        // Mechanism for wild dice
        if (new Random().nextInt(wildDiceChance) == 1 && wild) {
            sideUp = 13;
        }
        else {
            sideUp = new Random().nextInt(numSides) + 1;
        }
    }

    /**
     * Rolls the die
     */
    public void rollDieFinal() {
        sideUp = new Random().nextInt(numSides) + 1;
    }

    public void setValue(int value) {
        sideUp = value;
    }
}
