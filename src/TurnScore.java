import javax.swing.*;
import java.awt.*;

/**
 * This class sets and shows the frame that shows the final scoring summary for a turn; it is an extension
 * of ScoreCard, which is a JPanel
 * CPSC 224-01, Spring 2020
 * Final Project
 * No sources to cite
 *
 * @version v1.0 4/16/20
 * @author Texas Yahtzee Team
 */
public class TurnScore extends ScoreCard {

    private Scores scores;
    private Hand playerHand;
    private Hand compHand;
    private DealerHand dealerHand;

    /**
     * initialize the turn score object
     * @param numSides Number of sides chosen by user
     */
    public TurnScore(int numSides) {
        scores = new Scores(numSides);
        setLayout(new BorderLayout());
    }

    /**
     * @return  score of the player's hand
     */
    public int getPlayerScore() {
        return scores.getScore(playerHand, dealerHand);
    }

    /**
     * @return  score of the computer's hand
     */
    public int getCompScore() {
        return scores.getScore(compHand, dealerHand);
    }

    /**
     * set all the components for the score frame
     */
    private void setPanel() {
        JPanel playerPanel = new JPanel();
        JPanel compPanel = new JPanel();

        JLabel scoreLabelP = new JLabel("Score: " + getPlayerScore());
        scoreLabelP.setFont(new Font("Serif", Font.BOLD, 16));
        //playerPanel.add(new JLabel("Player Hand: " + scores.getScoreType()));
        JLabel playerScoreType = new JLabel("Player Hand: "  + scores.getScoreType() + " --");
        playerScoreType.setFont(new Font("Serif", Font.BOLD, 16));
        playerPanel.add(playerScoreType);
        playerPanel.add(scoreLabelP);


        JLabel scoreLabelC = new JLabel("Score: " + getCompScore());
        scoreLabelC.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel computerScoreType = new JLabel("Computer Hand: " + scores.getScoreType() + " --");
        //compPanel.add(new JLabel("Computer Hand: " + scores.getScoreType()));
        computerScoreType.setFont(new Font("Serif", Font.BOLD, 16));
        compPanel.add(computerScoreType);
        compPanel.add(scoreLabelC);
        add(playerPanel, BorderLayout.SOUTH);
        add(compPanel, BorderLayout.NORTH);
        add(new AllDicePanel(playerHand, compHand, dealerHand), BorderLayout.CENTER);
    }

    /**
     * show the score frame (at the end of a turn)
     */
    public void showScoreFrame() {
        removeAll();
        setPanel();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(350, 600));
        JOptionPane.showMessageDialog(frame, this, "Score", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * set the hands to be scored
     *
     * @param playerHand  the player's hand
     * @param compHand  the computer's hand
     * @param dealerHand  the dealer's hand
     */
    public void setHands(Hand playerHand, Hand compHand, DealerHand dealerHand) {
        this.playerHand = playerHand;
        this.compHand = compHand;
        this.dealerHand = dealerHand;
    }

    /**
     *
     * @return the score object; which has both players' scores as well as the name of their hand
     */
    public Scores getScoreObject() {
        return scores;
    }
}
