import java.util.Arrays;

/**
 * This class calculates scores in Texas Yahtzee
 *
 * CPSC 224-01, Spring 2020
 *
 * No sources to cite
 *
 * @version v1.0 4/12/20
 * @author Texas Yahtzee Team
 */
public class Scores {

    private int playerAndDealerHand[];
    private int numSides;
    private String scoreType;

    /**
     * Set up Scores game object
     * @param numSides Number of sides chosen by user
     */
    public Scores(int numSides)
    {
        playerAndDealerHand = new int[5];
        for (int i = 0; i < 5; i++)
        {
            playerAndDealerHand[i] = 0;
        }
        this.numSides = numSides;
    }

    /**
     * Gets all the dice values from the player and the dealer to form a 5 dice hand
     *
     * @param playerHand  The two dice that the player has
     * @param dealerHand  The three dice that the dealer has rolled
     */
    public void setPlayerAndDealerHand(Hand playerHand, DealerHand dealerHand)
    {
        playerAndDealerHand[0]  = playerHand.WeNeedSideUp(0);
        playerAndDealerHand[1]  = playerHand.WeNeedSideUp(1);
        playerAndDealerHand[2]  = dealerHand.WeNeedSideUp(0);
        playerAndDealerHand[3]  = dealerHand.WeNeedSideUp(1);
        playerAndDealerHand[4]  = dealerHand.WeNeedSideUp(2);
    }

    /**
     * Find any repeating dice
     *
     * @return maxOfAKindFound  the number of dice with the mode value
     */
    public int setMaxOfAKindFound() {

        int maxOfAKindFound = 0;
        int currentCount;
        for (int dieValue = 1; dieValue <= numSides; dieValue++) {
            currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++) {
                if (playerAndDealerHand[diePosition] == dieValue)
                    currentCount++;
            }
            if (currentCount >= maxOfAKindFound)
                maxOfAKindFound = currentCount;
        }
        return maxOfAKindFound;
    }

    /**
     * Discovers if a full house is found
     *
     * @return true if a full house is found, false else
     */
    public boolean setFullHouseFound()
    {
        boolean fullHouseFound = false;
        boolean found3k = false;
        boolean found2k = false;
        int currentCount;
        for (int dieValue = 1; dieValue <= numSides; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++)
            {
                if (playerAndDealerHand[diePosition] == dieValue)
                    currentCount++;
            }
            if (currentCount == 2)
                found2k = true;
            if (currentCount == 3)
                found3k = true;
        }
        if (found2k && found3k)
            fullHouseFound = true;

        return fullHouseFound;
    }

    /**
     * Finds the length of the longest straight in the user's hand
     *
     * @return the length of the longest straight
     */
    public int setMaxStraightFound()
    {
        int maxStraightFound = 0;
        int curLength = 1;
        for (int counter = 0; counter < 4; counter++)
        {
            if (playerAndDealerHand[counter] + 1 == playerAndDealerHand[counter+1])
                curLength++;
            else if (playerAndDealerHand[counter] + 1 < playerAndDealerHand[counter+1])
                curLength = 1;
            if (curLength > maxStraightFound)
                maxStraightFound = curLength;
        }
        return maxStraightFound;
    }


    /**
     * finds the value of the maximum dice that's repeated the most
     * PlayerAndDealerHand must be sorted before use
     *
     * @return value of max dice that is repeated the most
     */
    public int getRepeatedDice()
    {
        int maxCount = 1;
        int temp = playerAndDealerHand[0];
        int count = 1;

        for (int i = 1; i < 5; i++) {
                if (playerAndDealerHand[i] == playerAndDealerHand[i-1]) {
                count++;
            }
            else{
                if (count >= maxCount)
                {
                    maxCount = count;
                    temp = playerAndDealerHand[i-1];
                }
                count = 1;
            }
        }

        if (count >= maxCount)
        {
            maxCount = count;
            temp = playerAndDealerHand[4];
        }
        return temp;
    }


    /**
     * Returns the score of the hand
     *
     * @param playerHand  The player's two dice
     * @param dealerHand  The dealers three dice
     * @return score of the hand
     */
    public int getScore(Hand playerHand, DealerHand dealerHand) {

        setPlayerAndDealerHand(playerHand, dealerHand);
        Arrays.sort(playerAndDealerHand);


        int maxOfAKind = setMaxOfAKindFound();
        boolean fullHouse = setFullHouseFound();
        int maxStraight = setMaxStraightFound();
        int repeatedDice;

        if (fullHouse) {
            scoreType = "Full House";
            return 40;
        } else if (maxStraight > 3) {
            if (maxStraight == 4){
                scoreType = "Short Straight";
                return 35;
            } else if (maxStraight == 5){
                scoreType = "Long Straight";
                return 50;
            }
        } else if (maxOfAKind >=2) {
            if (maxOfAKind == 2) {
                repeatedDice = getRepeatedDice();
                scoreType = "Pair of " + repeatedDice + "s";
                return repeatedDice+12;
            } else if (maxOfAKind == 3) {
                repeatedDice = getRepeatedDice();
                scoreType = "Triple " + repeatedDice + "s";
                return repeatedDice+24;
            } else if (maxOfAKind == 4) {
                repeatedDice = getRepeatedDice();
                scoreType = "Quadruple " + repeatedDice + "s";
                return repeatedDice+36;
            } else if (maxOfAKind == 5) {
                scoreType = "Texas Yahtzee";
               return 100;
            }
        }
        scoreType = "Value of High Dice";
        return playerAndDealerHand[4];
    }

    /**
     * print the sorted hand composed of the player and dealer's dice
     * @param player  The name of the player
     */
    public void printSortedHand(String player)
    {
        System.out.print(player + ": ");
        for (int i = 0; i < 5; i++)
        {
            System.out.print(playerAndDealerHand[i] + " ");
        }
        System.out.println();
    }

    /**
     *
     * @return the type of score (eg. full house or small straight)
     */
    public String getScoreType() {
        return scoreType;
    }

    public String getSortedHand(String player)
    {
        String handString;
        handString = player + ": ";
        for (int i = 0; i < 5; i++)
        {
            handString += playerAndDealerHand[i] + " ";
        }
        return handString;
    }
}
