package Code.Entity.Moveable.Enemys;

import javafx.scene.image.Image;

public class Doll extends Enemy {
    public Doll(int x, int y) {
        super(x, y);
        imgLeft = new Image("./Resources/icons/doll_left1.png");
        imgRight = new Image("./Resources/icons/doll_right1.png");
        imgUp = new Image("./Resources/icons/doll_left2.png");
        imgDown = new Image("./Resources/icons/doll_right2.png");
        imgDead = new Image("./Resources/icons/doll_dead.png");
        image = imgDown;
    }

    public Doll(int x, int y, int step) {
        super(x, y, step);
        imgLeft = new Image("./Resources/icons/doll_left1.png");
        imgRight = new Image("./Resources/icons/doll_right1.png");
        imgUp = new Image("./Resources/icons/doll_left2.png");
        imgDown = new Image("./Resources/icons/doll_right2.png");
        imgDead = new Image("./Resources/icons/doll_dead.png");
        image = imgDown;
    }

    @Override
    public String toString() {
        return null;
    }
}
