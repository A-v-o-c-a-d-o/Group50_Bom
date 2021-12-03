package Code.Entity;

import javafx.scene.image.Image;

public class Bom extends Entity {
    public Bom(int x, int y) {
        super(x, y);
        prevent = false;
        image = new Image("./Resources/icons/bomb.png");
    }
}