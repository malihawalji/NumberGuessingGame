package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Font.BOLD;

public class NumGameUI implements ActionListener {

    protected JPanel panel;
    protected JFrame frame;
    protected JButton hardLvl;
    protected JButton mediumLvl;
    protected JButton easyLvl;
    protected int number;
    protected JLabel welcome;
    protected JLabel rules;
    protected String input;
    protected int tries;
    protected int i;
    protected int guess;

    public NumGameUI() {
        frame = new JFrame("Number Guessing Game");
        frame.setSize(500, 400);
        panel = new JPanel();
        panel.setSize(500, 400);
        panel.setBackground(new Color(23, 177, 224, 255));
        frame.add(panel, BorderLayout.CENTER);
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        };
        setUp();
        frame.setVisible(true);
    }

    public void setUp() {
        welcome = new JLabel("Welcome to the Number Guessing Game!");
        welcome.setFont(new Font("ComicSans", BOLD, 20));
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        rules = new JLabel("Choose your difficulty level:");
        rules.setFont(new Font("ComicSans", BOLD, 14));
        rules.setAlignmentX(CENTER_ALIGNMENT);
        hardLvl = new JButton("Hard");
        hardLvl.setAlignmentX(CENTER_ALIGNMENT);
        mediumLvl = new JButton("Medium");
        mediumLvl.setAlignmentX(CENTER_ALIGNMENT);
        easyLvl = new JButton("Easy");
        easyLvl.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(welcome);
        panel.add(rules);
        hardLvl.addActionListener(this);
        hardLvl.setActionCommand("hard");
        mediumLvl.addActionListener(this);
        mediumLvl.setActionCommand("medium");
        easyLvl.addActionListener(this);
        easyLvl.setActionCommand("easy");
        panel.add(hardLvl);
        panel.add(mediumLvl);
        panel.add(easyLvl);
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "hard" -> getNumberHard();
            case "medium" -> getNumberMedium();
            case "easy" -> getNumberEasy();
        }
    }

    public void getNumberEasy() {
        number = 1 + (int) (25 * Math.random());
        tries = 6;
        JOptionPane.showMessageDialog(panel, "Guess from 1-25, you have 6 tries");
        startGame();
    }

    public void getNumberMedium() {
        number = 1 + (int) (50 * Math.random());
        tries = 5;
        JOptionPane.showMessageDialog(panel, "Guess from 1-50, you have 5 tries");
        startGame();
    }

    public void getNumberHard() {
        number = 1 + (int) (100 * Math.random());
        tries = 4;
        JOptionPane.showMessageDialog(panel, "Guess from 1-100, you have 4 tries");
        startGame();
    }

    public void startGame() {
        for (i = 0; i < tries; i++) {
            input = JOptionPane.showInputDialog("Enter in your guess!");
            guess = Integer.parseInt(input);

            if (number == guess) {
                JOptionPane.showMessageDialog(panel, "that is correct!");
                playAgain();
                break;
            } else if (number > guess
                    && i != tries - 1) {
                JOptionPane.showMessageDialog(panel, "The number is greater than " + guess);
            } else if (number < guess
                    && i != tries - 1) {
                JOptionPane.showMessageDialog(panel, "The number is less than " + guess);
            }
        }

        if (i == tries) {
            JOptionPane.showMessageDialog(panel, "You are out of tries :( \n The number was " + number);
            playAgain();
        }
    }

    public void playAgain() {
        int result = JOptionPane.showConfirmDialog(panel, "play again?", null,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            String difficultyLevel = JOptionPane.showInputDialog("Enter your difficulty level, Hard, Medium, Easy");
            switch (difficultyLevel.toLowerCase()) {
                case "easy" -> getNumberEasy();
                case "medium" -> getNumberMedium();
                case "hard" -> getNumberHard();
            }
        } else if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

}
