import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class develops a panel that shows the scoring possibilities in Texas Yahtzee
 * CPSC 224-01, Spring 2020
 * Final Project
 * No sources to cite
 *
 * @version v1.0 4/16/20
 * @author Texas Yahtzee Team
 */
public class ScoreCard extends JPanel {

    // Main panel that will hold all the scores and categories
    JPanel ScorePanel;
    // Array list holding all scores as labels for each category
    ArrayList<JLabel> scoreLabels;
    ArrayList<JLabel> nameLabels;
    // Array list holding all scores for each category
    // ArrayList<Integer> score;

    /**
     * Initializes the ScoreCardUI object with all scorecard values.
     *
     */
    public ScoreCard() {
        // Set up the background color and layout for the main panel
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 400));
        ScorePanel = new JPanel();
        ScorePanel.setBackground(Color.WHITE);
        ScorePanel.setLayout(new GridLayout(0,2));
        nameLabels = new ArrayList<>();
        scoreLabels = new ArrayList<>();
        addNameLabels();
        addScoreLabels();
        writeToCard();
    }

    /**
     * Adds label for each category (below the upper scorecard) to nameLabels
     */
    private void addNameLabels() {
        nameLabels.add(new JLabel(" High Dice: "));
        nameLabels.add(new JLabel(" Pair: "));
        nameLabels.add(new JLabel(" 3 of a Kind: "));
        nameLabels.add(new JLabel(" 4 of a Kind: "));
        nameLabels.add(new JLabel(" Full House"));
        nameLabels.add(new JLabel(" Small Straight"));
        nameLabels.add(new JLabel(" Large Straight"));
        nameLabels.add(new JLabel(" Texas Yahtzee"));
    }

    /**
     * Adds label for each category (below the upper scorecard) to nameLabels
     */
    private void addScoreLabels() {
        scoreLabels.add(new JLabel("value of highest dice     "));
        scoreLabels.add(new JLabel("12 + value of 1 paired dice     "));
        scoreLabels.add(new JLabel("24 + value of 1 grouped dice     "));
        scoreLabels.add(new JLabel("36 + value of 1 grouped dice     "));
        scoreLabels.add(new JLabel("40     "));
        scoreLabels.add(new JLabel("35     "));
        scoreLabels.add(new JLabel("50     "));
        scoreLabels.add(new JLabel("100     "));
    }

    /**
     * Write all the categories and scores to the scorecard panel
     */
    private void writeToCard() {
        for(int i = 0; i < nameLabels.size(); i++) {
            ScorePanel.add(nameLabels.get(i));
            scoreLabels.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
            ScorePanel.add(scoreLabels.get(i));
        }
        add(ScorePanel, BorderLayout.CENTER);
        add(new JLabel("Scorecard:"), BorderLayout.NORTH);
    }

    /**
     * creates a frame to show the scorecard that has been created
     */
    public void showScorecard() {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(350, 600));
        JOptionPane.showMessageDialog(frame, this, "Scorecard", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        ScoreCard sc = new ScoreCard();
        sc.showScorecard();
    }

}
