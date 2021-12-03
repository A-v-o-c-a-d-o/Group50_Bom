package Code.App.Entity;

import javafx.scene.image.Image;

public abstract class Entity {
    protected Image image;
    protected int x, y;

    public Entity() {}

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
}