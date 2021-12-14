package Code.Entity.Non_moveable.Items;

import Code.Entity.Entity;
import Code.Entity.Moveable.Player;
import Code.Entity.Non_moveable.Non_moveable;

public abstract class Item extends Non_moveable {
    public Item(int x, int y) {
        super(x, y);
        this.prevent = false;
        this.destroy = false;
    }
    protected boolean collected = false;

    public abstract void doWhenCollided(Player player);

    public boolean collided(Entity other) {
        if (other instanceof Player) {
            if (!collected && this.isCollidedWith(other)) {
                doWhenCollided((Player)other);
                collected = true;
                destroy = true;
            }
        }
        return false;
    }

}
