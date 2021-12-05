package Code.Entity.Non_moveable;

import Code.Entity.Entity;

public abstract class Non_moveable extends Entity {
    public Non_moveable(int x, int y) {
        super(x, y);
        prevent = true;
    }
}
