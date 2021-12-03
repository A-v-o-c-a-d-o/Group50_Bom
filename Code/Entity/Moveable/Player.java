package Code.Entity.Moveable;

import javafx.scene.image.Image;

public class Player extends Moveable {
    public int health = 3;

    public Player() {
        super();
        imgLeft = new Image("./Resources/icons/player_left.png");
        imgRight = new Image("./Resources/icons/player_right.png");
        imgUp = new Image("./Resources/icons/player_up.png");
        imgDown = new Image("./Resources/icons/player_down.png");
        imgDead = new Image("./Resources/icons/player_dead.png");
    }

    public Player(int x, int y) {
        super(x, y);
        imgLeft = new Image("./Resources/icons/player_left.png");
        imgRight = new Image("./Resources/icons/player_right.png");
        imgUp = new Image("./Resources/icons/player_up.png");
        imgDown = new Image("./Resources/icons/player_down.png");
        imgDead = new Image("./Resources/icons/player_dead1.png");
    }
}