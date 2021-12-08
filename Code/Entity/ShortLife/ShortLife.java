package Code.Entity.ShortLife;

import Code.Entity.Entity;

public class ShortLife extends Entity {
    protected long timeToDie;

    public ShortLife(int x, int y) {
        super(x, y);
        prevent = false;
    }

    public void update() {
        if (System.currentTimeMillis() - timeToDie > 0)
            alive = false;
    }
}
