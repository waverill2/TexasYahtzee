import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class plays a game of Texas Yahtzee
 *
 * CPSC 224-01, Spring 2020
 *
 * No sources to cite
 *
 * @version v1.0 4/12/20
 * @author Texas Yahtzee Team
 */
public class PlayTexasYahtzee {

    private Turn turn;
    private Hand computerHand;
    private Hand playerHand;
    private DealerHand dealerHand;
    private boolean dealerRerolled;

    private int playerScore;
    private int computerScore;
    private int numSides;
    //private Hand playerHand;
   // private Hand computerHand;
    private int playerTurnScore;
    private int computerTurnScore;
    private Scores scores;
    private TurnScore turnScore;
    private JPanel mainPanel;

    /**
     * An object that plays a game of Texas Yahtzee
     * @param sides Number of sides user has chosen
     */
    public PlayTexasYahtzee(int sides)
    {
        mainPanel = new JPanel();
        playerScore = 0;
        computerScore = 0;
        numSides = sides;
        //playerHand = new Hand(true, 6);
        playerTurnScore = 0;
        computerTurnScore = 0;
        playerHand = new Hand(true, numSides, true);
        computerHand = new Hand(true, numSides, false);
        turnScore = new TurnScore(numSides);
        scores = turnScore.getScoreObject();
        dealerRerolled = false;
    }

    /**
     * runs the final roll in a turn of Texas Yahtzee
     */
    public void playTexasYahtzee()
    {

                turnScore.setHands(playerHand, computerHand, dealerHand);
                computerTurnScore = turnScore.getCompScore();
                scores.printSortedHand("Computer Hand");


                playerTurnScore = turnScore.getPlayerScore();
                System.out.println();

                if (computerTurnScore > playerTurnScore)
                    computerScore += computerTurnScore - playerTurnScore;
                else
                    playerScore += playerTurnScore - computerTurnScore;

                System.out.println("Player Scored: " + playerTurnScore);
                System.out.println("Computer Scored: " + computerTurnScore);

                System.out.println();

                System.out.println("Player Total Score: " + playerScore);
                System.out.println("Computer Total Score: " + computerScore);
                System.out.println("-----------------");
                System.out.println();

                turnScore.showScoreFrame();
    }

    /**
     * Initializes values for the next turn
     */
    private void newTurn() {
        mainPanel.removeAll();
        playerTurnScore = 0;
        computerTurnScore = 0;
        computerHand.modifyHand(true, true);
        dealerHand = new DealerHand(numSides);
        turn = new Turn(playerHand, dealerHand, numSides);
        turn.setPlayTexasYahtzee(this);
    }

    public void rollDealerHand() {
        dealerHand.rollAllDice();
    }

    /**
     * Sets up a panel for a new turn
     */
    public void getTurnPanel() {
        newTurn();
        turn.playRound();
        mainPanel.add(turn.getTurnPanel());
    }

    /**
     *
     * @return the player's total score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     *
     * @return the computer's total score
     */
    public int getComputerScore() {
        return computerScore;
    }

    /**
     *
     * @return the JPanel with texas yahtzee content
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    /**
     * set up the turn class for a new turn
     */
    public void nextTurn() {
        turn.nextTurn();
    }

    /**
     * run the final turn
     */
    public void finalTurn() {
        turn.finalTurn();
    }

    /**
     * set the values that determine which dice are kept based on which dice are chosen
     */
    public void setBooleans() {
        turn.setBooleans();
    }

    /**
     * Sets boolean value true of dealer hand has to be re rolled
     */
    public void setDealerRerolled() {
        dealerRerolled = true;
    }

    /**
     *
     * @return new dealer hand
     */
    public boolean getPlayerRerolled() {
        return dealerRerolled;
    }

    // TEST
    public static void main(String[] args) {
        PlayTexasYahtzee play = new PlayTexasYahtzee(6);
        play.playTexasYahtzee();
        play.playTexasYahtzee();
    }
}
