import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Generates the main menu screen for Texas Yahtzee
 *
 * Programming Assignment #4
 * No sources to cite
 *
 * @version v1.0 4/22/20
 * @author Texas Yahtzee Team
 */
public class FinalPage extends JPanel {
    // Number of sides that the user will choose
    private int numSides;
    private Integer[] numSidesArray;
    private JComboBox<Integer> numSidesBox;

    /**
     * Generate a main menu screen for texas yahtzee
     *
     * @param playerScore player score
     * @param compScore computer score
     */
    public FinalPage(int playerScore, int compScore) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        mainPanel.add(new JLabel(), BorderLayout.CENTER);
        JLabel label = new JLabel("Texas Yahtzee");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 34));
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.setBackground(Color.CYAN);
        add(mainPanel, BorderLayout.NORTH);
        Winner(playerScore, compScore);
    }

    /**
     * Determines which player has won and which player has lost
     * @param playerScore player's score
     * @param compScore computer's score
     */
    public void Winner(int playerScore, int compScore) {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new GridLayout(3, 1));
        JLabel winner;
        JLabel winnerScore;
        JLabel loserScore;

        if (playerScore > compScore) {
            winner = new JLabel("You Win!");
            winnerScore = new JLabel("Player Score: " + String.valueOf(playerScore));
            loserScore = new JLabel("Computer Score: " + String.valueOf(compScore));
        }
        else {
            winner = new JLabel("Computer Wins...");
            winnerScore = new JLabel("Computer Score: " + String.valueOf(playerScore));
            loserScore = new JLabel("Player Score: " + String.valueOf(compScore));
        }
        winner.setHorizontalAlignment(JLabel.CENTER);
        winnerScore.setHorizontalAlignment(JLabel.CENTER);
        loserScore.setHorizontalAlignment(JLabel.CENTER);
        loserScore.setVerticalAlignment(JLabel.NORTH);
        winner.setFont(new Font("Serif", Font.BOLD, 50));
        tempPanel.add(winner);
        tempPanel.add(winnerScore);
        tempPanel.add(loserScore);
        tempPanel.setBackground(Color.PINK);
        add(tempPanel, BorderLayout.CENTER);

    }




    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        FinalPage finalP = new FinalPage(10, 2);
        frame.add(finalP);
        frame.setVisible(true);
    }
}
