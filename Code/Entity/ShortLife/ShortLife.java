package Code.Entity.ShortLife;

import Code.Entity.Entity;
import Code.Entity.Moveable.Player;

public abstract class ShortLife extends Entity {
    protected long timeToDie;

    public ShortLife(int x, int y) {
        super(x, y);
        prevent = false;
    }

    public void update(Player player) {
        if (System.currentTimeMillis() - timeToDie > 0)
            alive = false;
    }

    
    public boolean canBeBurn() {
        return false;
    }
}
