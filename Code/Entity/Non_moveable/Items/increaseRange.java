package Code.Entity.Non_moveable.Items;

import Code.Entity.Moveable.Player;

public class increaseRange extends Item {
    public increaseRange(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean canBeBurn() {
        return false;
    }

    @Override
    public void doWhenCollided(Player player) {

    }
}
