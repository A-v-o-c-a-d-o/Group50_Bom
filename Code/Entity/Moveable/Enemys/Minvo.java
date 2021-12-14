package Code.Entity.Moveable.Enemys;

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

    @Override
    public String toString() {
        return null;
    }
}
