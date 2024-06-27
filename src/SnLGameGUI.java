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
                    rollButton.setEnabled(false);
                    return;
                }

                Player playerInTurn = game.getWhoseTurn();
                boolean continuePlaying;

                do {
                    continuePlaying = false;
                    gameLog.append("Now Playing: " + playerInTurn.getName() + "\n");
                    int diceRoll = playerInTurn.rollDice();
                    gameLog.append(playerInTurn.getName() + " rolled a " + diceRoll + "\n");
                    SoundPlayer.playSound("sound/diceRolling.wav");
                    game.movePlayerAround(playerInTurn, diceRoll);
                    gameLog.append(playerInTurn.getName() + " moved to position " + playerInTurn.getPosition() + "\n");
                    if (game.getGameStatus() == 2) {
                        gameLog.append("The winner is: " + playerInTurn.getName() + "\n");
                        SoundPlayer.playSound("sound/winBackSound.wav");
                    }
                    gameLog.append("==============================================\n");

                    if (diceRoll == 6 && game.getGameStatus() != 2) {
                        gameLog.append(playerInTurn.getName() + ": rolled a 6 and gets another turn!\n");
                        gameLog.append("==============================================\n");
                        continuePlaying = true;
                    }
                } while (continuePlaying);

                if (game.getGameStatus() != 2) {
                    game.getWhoseTurn();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4", "Player 5"};
        new SnLGameGUI(playerNames);
    }
}
