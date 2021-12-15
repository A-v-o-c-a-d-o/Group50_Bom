package Code.Entity.ShortLife;

import javafx.scene.image.Image;

public class Bom extends ShortLife {
    public Bom(int x, int y) {
        super(x, y);
        this.timeToDie = System.currentTimeMillis() + 2000;
        prevent = false;
        image = new Image("./Resources/icons/bomb.png");
    }

    @Override
    public String toString() {
        return null;
    }
}