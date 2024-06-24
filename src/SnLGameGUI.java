import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnLGameGUI extends JFrame {
    private SnL game;
    private JTextArea gameLog;
    private JButton rollButton;

    public SnLGameGUI(String[] playerNames) {
        game = new SnL(100);
        for (String name : playerNames) {
            game.addPlayer(new Player(name));
        }
        game.initiateGame();

        setTitle("Snakes and Ladders");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);
        panel.add(scrollPane, BorderLayout.CENTER);

        rollButton = new JButton("Roll Dice");
        panel.add(rollButton, BorderLayout.SOUTH);

        add(panel);

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getGameStatus() == 2) {
                    gameLog.append("Game is over. Winner: " + game.getWhoseTurn().getName() + "\n");
                    rollButton.setEnabled(false);
                    return;
                }
                Player playerInTurn = game.getWhoseTurn();
                gameLog.append("Now Playing: " + playerInTurn.getName() + "\n");
                int diceRoll = playerInTurn.rollDice();
                gameLog.append(playerInTurn.getName() + " rolled a " + diceRoll + "\n");
                game.movePlayerAround(playerInTurn, diceRoll);
                gameLog.append(playerInTurn.getName() + " moved to position " + playerInTurn.getPosition() + "\n");
                if (game.getGameStatus() == 2) {
                    gameLog.append("The winner is: " + playerInTurn.getName() + "\n");
                }
                gameLog.append("==============================================\n");
            }
        });

        setVisible(true);
    }
}
