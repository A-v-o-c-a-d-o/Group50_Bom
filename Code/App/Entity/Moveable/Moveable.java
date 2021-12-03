package Code.App.Entity.Moveable;

import Code.App.Entity.Entity;
import javafx.scene.image.Image;

public abstract class Moveable extends Entity {
    protected Image imgLeft;
    protected Image imgRight;
    protected Image imgUp;
    protected Image imgDown;
    protected Image imgDead;
    
    public Moveable() {
        image = imgDown;
    }

    public Moveable(int x, int y) {
        image = imgDown;
        this.x = x;
        this.y = y;
    }

    
}
