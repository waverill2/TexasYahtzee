import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class runs all of the ui and game engine for Texas Yahtzee
 * CPSC 224-01, Spring 2020
 * Final Project
 * No sources to cite
 *
 * @version v1.0 4/16/20
 * @author Texas Yahtzee Team
 */
public class TexasYahtzeeGUI extends JFrame {
    // Components of the title screen
    private TitlePage startPanel;
    private JButton playButton;
    // number of sides the user chooses for their dice
    private PlayTexasYahtzee game;
    private int numSides;
    private Turn turn;
    private JPanel gamePanel;
    private JButton showScorecard;
    private ScoreCard scoreCard;
    private int userScore;
    private int compScore;
    private int rollCount;
    private JLabel userScoreLabel;
    private JLabel compScoreLabel;
    private int finalScore;
    private JPanel scorePanel;
    private FinalPage finalPage;

    /**
     * initialize a yahtzeeUI object with a specified title
     *
     * @param title  Title of the yahtzeeUI frame
     */
    public TexasYahtzeeGUI(String title) {
        // set specifications for a borderLayout frame
        setTitle(title);
        setBackground(Color.PINK);
        setSize(860, 450);
        setLocation(200, 50);
        setLayout(new BorderLayout());
        userScore = 0;
        compScore = 0;
        scoreCard = new ScoreCard();
        finalScore = 100;
        setUpStartScreen();
    }

    /**
     * Sets up the first panels to be displayed on the menu screen for Yahtzee
     **/
    private void setUpStartScreen() {
        // Initialize the startPanel and playButton
        startPanel = new TitlePage();
        setPlayButton();
        startPanel.add(playButton, BorderLayout.SOUTH);
        // add the start panel to the screen
        add(startPanel);
    }


    /**
     * Sets the location and action of the play button on the start page
     **/
    private void setPlayButton() {
        // initialize play buttons text, color, and action listener
        playButton = new JButton("Play");
        playButton.setBackground(Color.CYAN);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rollCount = 0;
                // gets the number of sides from what the user chose
                numSides = startPanel.getNumSides();

                // remove the start panel and initialize the dice page and line page
                remove(startPanel);

                game = new PlayTexasYahtzee(numSides);
                game.getTurnPanel();
                gamePanel = game.getPanel();
                add(gamePanel);
                // add(game.getPanel());
                setDecisionButton();

                showScorecard = new JButton("Scoring Rules");
                showScorecard.setFont(new Font("Serif", Font.BOLD, 16));
                showScorecard.setForeground(Color.WHITE);


                setShowScorecard();
                validate();
                repaint();
            }
        });
    }

    /**
     * set up the location and action for the decision button
     */
    private void setDecisionButton() {
        // set the name, location, and action


        JButton decisionButton = new JButton("Continue");
        add(decisionButton, BorderLayout.SOUTH);
        decisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rollCount++;
                // removes the current dice from the frame
                remove(gamePanel);
                if (rollCount < 3) {
                    game.setBooleans();
                    game.nextTurn();
                    gamePanel = game.getPanel();
                    add(gamePanel);
                }
                else if (rollCount == 3) {
                    game.setBooleans();
                    game.finalTurn();
                    gamePanel = game.getPanel();
                    game.playTexasYahtzee();
                    userScore = game.getPlayerScore();
                    compScore = game.getComputerScore();
                    userScoreLabel.setText("Player Score: " + userScore);
                    compScoreLabel.setText("Computer Score: " + compScore);
                    add(gamePanel);
                }
                else {

                    if (game.getPlayerScore() >= finalScore) {
                        // removeAll();
                        remove(gamePanel);
                        remove(showScorecard);
                        remove(decisionButton);
                        userScoreLabel.setText("Player Final Score: " + userScore);
                        compScoreLabel.setText("Computer Final Score: " + compScore);
                        remove(scorePanel);
                        finalPage = new FinalPage(game.getPlayerScore(), game.getComputerScore());
                        add(finalPage);
                        newGameButton();
                        // setUpStartScreen();
                    }
                    else if (game.getComputerScore() >= finalScore) {
                        // removeAll();
                        remove(gamePanel);
                        remove(showScorecard);
                        remove(decisionButton);
                        userScoreLabel.setText("Player Final Score: " + userScore);
                        compScoreLabel.setText("Computer Final Score: " + compScore);
                        remove(scorePanel);
                        finalPage = new FinalPage(game.getPlayerScore(), game.getComputerScore());
                        add(finalPage);
                        newGameButton();
                        // setUpStartScreen();
                    }
                    else {
                        rollCount = 0;
                        game.getTurnPanel();
                        gamePanel = game.getPanel();
                        add(gamePanel);
                    }
                }
                // game.playTexasYahtzee();
                validate();
                repaint();

            }
        });
    }

    /**
     * Sets the action and location for the scorecard button
     */
    public void setShowScorecard() {
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2, 1));
        userScoreLabel = new JLabel("Player Score: 0 ");
        userScoreLabel.setFont(new Font("Serif", Font.BOLD, 30));
        // userScoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        compScoreLabel = new JLabel("Computer Score: 0 ");
        compScoreLabel.setFont(new Font("Serif", Font.BOLD, 30));
        // compScoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        scorePanel.add(userScoreLabel);
        scorePanel.add(compScoreLabel);
        add(scorePanel, BorderLayout.EAST);
        showScorecard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Opens a scorecard frame
                scoreCard.showScorecard();
            }
        });
        // add button to the frame
        showScorecard.setPreferredSize(new Dimension(130, 40));
        showScorecard.setBackground(Color.BLACK);
        add(showScorecard, BorderLayout.WEST);
    }

    /**
     * Adds a button tha allows the user to play a new game
     */
    private void newGameButton() {
        JButton newGame = new JButton("New Game");
        finalPage.add(newGame, BorderLayout.SOUTH);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                remove(finalPage);
                setUpStartScreen();
                validate();
                repaint();
            }
        });

    }
}
