package Code.Entity.Non_moveable;

import javafx.scene.image.Image;

public class Grass extends Non_moveable {
    public Grass(int x, int y) {
        super(x, y);
        prevent = false;
        image = new Image("./Resources/icons/grass.png");
    }   
}