package Code.Entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected boolean alive;
    protected Image image;
    protected int x, y;
    public boolean prevent;
    public boolean destroy;

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract String toString();

    public void update() {}
    
    public abstract boolean canBeBurn();

    public boolean isCollidedWith(Entity other) {
        return distanceTo(other) < 0.5;
    }

    public double distanceTo(Entity other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) +
                Math.pow(y - other.getY(), 2));
    }
}