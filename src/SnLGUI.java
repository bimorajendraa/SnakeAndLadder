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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel for the image and add it to the top of the frame
        JLabel imageLabel = new JLabel(new ImageIcon("image/logoSnL.png"));
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.NORTH);

        // Create a panel for the input fields and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel label = new JLabel("Enter number of players (2-5): ");
        inputPanel.add(label);

        numPlayersField = new JTextField();
        inputPanel.add(numPlayersField);

        submitButton = new JButton("Submit");
        inputPanel.add(submitButton);

        // Add the input panel to the center of the frame
        add(inputPanel, BorderLayout.CENTER);

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
