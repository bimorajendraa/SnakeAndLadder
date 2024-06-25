import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnLGUI extends JFrame {
    private JTextField numPlayersField;
    private JButton submitButton;
    private int numPlayers;

    public SnLGUI() {
        setTitle("Snakes and Ladders");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel imageLabel = new JLabel(new ImageIcon("image/logoSnL.png"));
        panel.add(imageLabel, BorderLayout.NORTH);

        JLabel label = new JLabel("Enter number of players (2-5): ");
        panel.add(label);

        numPlayersField = new JTextField();
        panel.add(numPlayersField);

        submitButton = new JButton("Submit");
        panel.add(submitButton);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    numPlayers = Integer.parseInt(numPlayersField.getText());
                    if (numPlayers < 2 || numPlayers > 5) {
                        JOptionPane.showMessageDialog(null, "Please enter a number between 2 and 5.");
                    } else {
                        dispose();
                        new PlayerNamesGUI(numPlayers);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });

        setVisible(true);
    }
}
