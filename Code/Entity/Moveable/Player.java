package Code.Entity.Moveable;

import javafx.scene.image.Image;

public class Player extends Moveable {
    private int igniteRange = 1;

    public Player(int x, int y) {
        super(x, y);
        dieSound = "die.wav";
        step = 3;
        health = 3;
        imgLeft = new Image("./Resources/icons/player_left.png");
        imgRight = new Image("./Resources/icons/player_right.png");
        imgUp = new Image("./Resources/icons/player_up.png");
        imgDown = new Image("./Resources/icons/player_down.png");
        imgDead = new Image("./Resources/icons/player_dead1.png");
        image = imgDown;
    }

    public int getIgniteRange() {
        return igniteRange;
    }

    public void setIgniteRange(int igniteRange) {
        this.igniteRange = igniteRange;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return null;
    }

    public void setHealth(int health) {
        this.health = health <= 3 ? health : 3;
    }
}