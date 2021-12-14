package Code.Entity.Non_moveable;

import Code.App.Game;
import Code.Entity.Entity;

public abstract class Non_moveable extends Entity {
    public boolean destroy;
    public Non_moveable(int x, int y) {
        super(x, y);
        prevent = true;
    }
    public boolean isCollidedWith(Entity other) {
        double x = Math.abs(this.getX() - other.getX());
        double y = Math.abs(this.getY() - other.getY());
        return x < (3* Game.CELLS_SIZE)/5 && y < (3*Game.CELLS_SIZE)/5;
    }
}