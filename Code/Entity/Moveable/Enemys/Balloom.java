package Code.Entity.Moveable.Enemys;

import javafx.scene.image.Image;

public class Balloom extends Enemy {
    public Balloom(int x, int y) {
        super(x, y);
        dieSound = "balloonIdle.wav";
        imgLeft = new Image("./Resources/icons/balloom_left1.png");
        imgRight = new Image("./Resources/icons/balloom_right1.png");
        imgUp = new Image("./Resources/icons/balloom_left2.png");
        imgDown = new Image("./Resources/icons/balloom_right2.png");
        imgDead = new Image("./Resources/icons/balloom_dead.png");
        image = imgDown;
    }

    @Override
    public String toString() {
        return null;
    }
}
