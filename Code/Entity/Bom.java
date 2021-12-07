package Code.Entity;

import javafx.scene.image.Image;

public class Bom extends Entity {
    private long timeToExplode;

    public Bom(int x, int y) {
        super(x, y);
        this.timeToExplode = System.currentTimeMillis() + 2000;
        prevent = false;
        image = new Image("./Resources/icons/bomb.png");
    }

    public long getTimeExplode() {
        return timeToExplode;
    }
}