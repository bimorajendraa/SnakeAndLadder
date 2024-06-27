/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 2
 * Members  :
 * 1. 5026231210 - Bimo Rajendra Widyadhana
 * 2. 5026221206 - Marcellino SP. Pasaribu
 * 3. 5026231176 - Harya Raditya Handoyo
 * ------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.Scanner;

public class SnL {
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;

    public SnL(int s) {
        this.boardSize = s;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.gameStatus = 0;
    }

    public void setBoardSize(int s) {
        this.boardSize = s;
    }

    public void setGameStatus(int s) {
        this.gameStatus = s;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        Player playerInTurn;
        Scanner read = new Scanner(System.in);

        System.out.println("Enter number of players (2-5): ");
        int numPlayers = read.nextInt();
        read.nextLine();  // Consume newline

        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("Please enter Player " + i + ": ");
            String playerName = read.nextLine();
            Player player = new Player(playerName);
            addPlayer(player);
        }

        initiateGame();

        do {
            playerInTurn = getWhoseTurn();
            System.out.println("Now Playing " + playerInTurn.getName());
            System.out.println(playerInTurn.getName() + " please press enter to roll the dice");
            String enter = read.nextLine();
            int x = 0;
            if (enter.isEmpty()) {
                x = playerInTurn.rollDice();
            }
            System.out.println("Dice Number: " + x);
            movePlayerAround(playerInTurn, x);
            System.out.println("New Position: " + playerInTurn.getPosition());
            System.out.println("==============================================");

        } while (getGameStatus() != 2);

        System.out.println("The winner is: " + playerInTurn.getName());
    }

    public void addPlayer(Player s) {
        this.players.add(s);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addSnake(Snake s) {
        this.snakes.add(s);
    }

    public void addSnakes(int[][] s) {
        for (int r = 0; r < s.length; r++) {
            Snake snake = new Snake(s[r][0], s[r][1]);
            this.snakes.add(snake);
        }
    }

    public void addLadder(Ladder l) {
        this.ladders.add(l);
    }

    public void addLadders(int[][] l) {
        for (int r = 0; r < l.length; r++) {
            Ladder ladder = new Ladder(l[r][1], l[r][0]);
            this.ladders.add(ladder);
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public void initiateGame() {
        int[][] l = {
                {2, 23},
                {8, 34},
                {20, 77},
                {32, 68},
                {41, 79},
                {74, 88},
                {82, 100},
                {85, 95}
        };
        addLadders(l);

        int[][] s = {
                {5, 47},
                {9, 29},
                {15, 38},
                {25, 97},
                {33, 53},
                {37, 62},
                {54, 86},
                {70, 92}
        };
        addSnakes(s);
    }

    public void movePlayerAround(Player p, int x) {
        p.moveAround(x, this.boardSize);
        for (Ladder l : this.ladders) {
            if (p.getPosition() == l.getBottomPosition()) {
                System.out.println(p.getName() + " you got Ladder from: " + l.getBottomPosition() + " To: " + l.getTopPosition());
                p.setPosition(l.getTopPosition());
            }
        }
        for (Snake s : this.snakes) {
            if (p.getPosition() == s.getHeadPosition()) {
                p.setPosition(s.getTailPosition());
                System.out.println(p.getName() + " you get snake head from " + s.getHeadPosition() + " slide down to " + s.getTailPosition());
            }
        }
        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }

    public Player getWhoseTurn() {
        if (this.gameStatus == 0) {
            this.gameStatus = 1;
            this.nowPlaying = (int) (Math.random() * players.size());
            return this.players.get(this.nowPlaying);
        } else {
            this.nowPlaying = (this.nowPlaying + 1) % players.size();
            return this.players.get(this.nowPlaying);
        }
    }
}
