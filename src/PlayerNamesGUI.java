import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerNamesGUI extends JFrame {
    private JTextField[] playerFields;
    private JButton startButton;
    private int numPlayers;

    public PlayerNamesGUI(int numPlayers) {
        this.numPlayers = numPlayers;

        setTitle("Snakes and Ladders");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(numPlayers + 1, 2));

        playerFields = new JTextField[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            JLabel label = new JLabel("Enter name for Player " + (i + 1) + ": ");
            panel.add(label);

            playerFields[i] = new JTextField();
            panel.add(playerFields[i]);
        }

        startButton = new JButton("Start Game");
        panel.add(startButton);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] playerNames = new String[numPlayers];
                for (int i = 0; i < numPlayers; i++) {
                    playerNames[i] = playerFields[i].getText();
                }
                dispose();
                new SnLGameGUI(playerNames);
            }
        });

        setVisible(true);
    }
}
