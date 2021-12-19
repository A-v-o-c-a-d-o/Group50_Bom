package Code.Entity.Moveable.Enemies;

import javafx.scene.image.Image;

public class Minvo extends Enemy {
    public Minvo(int x, int y) {
        super(x, y);
        dieSound = "rratIdle.wav";
        imgLeft = new Image("./Resources/icons/minvo_left1.png");
        imgRight = new Image("./Resources/icons/minvo_right1.png");
        imgUp = new Image("./Resources/icons/minvo_left2.png");
        imgDown = new Image("./Resources/icons/minvo_right2.png");
        imgDead = new Image("./Resources/icons/minvo_dead.png");
        image = imgDown;
    }

    public Minvo(int x, int y, int step) {
        super(x, y, step);
        dieSound = "rratIdle.wav";
        imgLeft = new Image("./Resources/icons/minvo_left.png");
        imgRight = new Image("./Resources/icons/minvo_right.png");
        imgUp = new Image("./Resources/icons/minvo.png");
        imgDown = new Image("./Resources/icons/minvo.png");
        imgDead = new Image("./Resources/icons/minvo.png");
        image = imgDown;
    }

    @Override
    public String toString() {
        return null;
    }
}
