package Code.Entity.Non_moveable.Items;

import Code.App.Game;
import Code.Entity.Moveable.Player;
import Code.Entity.Non_moveable.Non_moveable;

public abstract class Item extends Non_moveable {
    public Item(int x, int y) {
        super(x, y);
        this.prevent = false;
    }

    public abstract void doWhenCollided(Player player);

    public void update(Player other) {
        if (this.isCollidedWith(other)) {
            doWhenCollided(other);
            alive = false;
        }
    }

    public boolean isCollidedWith(Player other) {
        double x = Math.abs(this.getX() - other.getX());
        double y = Math.abs(this.getY() - other.getY());
        return x < (3* Game.CELLS_SIZE)/5 && y < (3*Game.CELLS_SIZE)/5;
    }
}
