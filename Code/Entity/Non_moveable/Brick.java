package Code.Entity.Non_moveable;

import javafx.scene.image.Image;

public class Brick extends Non_moveable {
    public Brick(int x, int y) {
        super(x, y);
        prevent = true;
        image = new Image("./Resources/icons/brick.png");
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean canBeBurn() {
        return true;
    }
}
