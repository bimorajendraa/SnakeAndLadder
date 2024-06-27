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

public class Player {
    private String name;
    private int position;
    private int score;

    Player(String name){
        this.name = name;
        this.position = 0;
        this.score = 100;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void setScore(int score){
        this.score = score > 100 ? 100 : score; // Ensure the score does not exceed 100
    }

    public String getName(){
        return this.name;
    }

    public int getPosition(){
        return this.position;
    }

    public int getScore(){
        return this.score;
    }

    public int rollDice() {
        return (int)(Math.random() * 6 + 1);
    }

    public void moveAround(int y, int boardSize) {
        if(this.position + y > boardSize) {
            this.position = boardSize - (this.position + y) % boardSize;
        } else {
            this.position = this.position + y;
        }
    }
}