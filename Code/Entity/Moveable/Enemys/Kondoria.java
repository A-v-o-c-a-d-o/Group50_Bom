package Code.Entity.Moveable.Enemys;

import javafx.scene.image.Image;

public class Kondoria extends Enemy {
    public Kondoria(int x, int y) {
        super(x, y);
        dieSound = "skullHeadIdle.wav";
        imgLeft = new Image("./Resources/icons/kondoria_left1.png");
        imgRight = new Image("./Resources/icons/kondoria_right1.png");
        imgUp = new Image("./Resources/icons/kondoria_left2.png");
        imgDown = new Image("./Resources/icons/kondoria_right2.png");
        imgDead = new Image("./Resources/icons/kondoria_dead.png");
        image = imgDown;
    }

    public Kondoria(int x, int y, int step) {
        super(x, y, step);
        dieSound = "skullHeadIdle.wav";
        imgLeft = new Image("./Resources/icons/kondoria_left1.png");
        imgRight = new Image("./Resources/icons/kondoria_right1.png");
        imgUp = new Image("./Resources/icons/kondoria_left2.png");
        imgDown = new Image("./Resources/icons/kondoria_right2.png");
        imgDead = new Image("./Resources/icons/kondoria_dead.png");
        image = imgDown;
    }

    @Override
    public String toString() {
        return null;
    }
}
