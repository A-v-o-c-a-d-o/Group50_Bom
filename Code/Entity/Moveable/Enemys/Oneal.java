package Code.Entity.Moveable.Enemys;

import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int x, int y) {
        super(x, y);
        dieSound = "scarabIdle.wav";
        imgLeft = new Image("./Resources/icons/oneal_left1.png");
        imgRight = new Image("./Resources/icons/oneal_right1.png");
        imgUp = new Image("./Resources/icons/oneal_left2.png");
        imgDown = new Image("./Resources/icons/oneal_right2.png");
        imgDead = new Image("./Resources/icons/oneal_dead.png");
        image = imgDown;
    }

    @Override
    public String toString() {
        return null;
    }
}
