package Code.Entity.Non_moveable;

import javafx.scene.image.Image;

public class Wall extends Non_moveable {
    public Wall(int x, int y) {
        super(x, y);
        prevent = true;
        image = new Image("./Resources/icons/wall.png");
    }
}
