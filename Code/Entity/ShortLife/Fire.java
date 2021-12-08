package Code.Entity.ShortLife;

import Code.App.Game;
import Code.Entity.Moveable.Moveable;
import javafx.scene.image.Image;

public class Fire extends ShortLife {
    public Fire(int x, int y) {
        super(x, y);
        this.timeToDie = System.currentTimeMillis() + 300;
        prevent = false;
        image = new Image("./Resources/icons/explosion_horizontal2.png");
    }

    public void burn(Moveable a) {
        if (x < a.getX() + Game.CELLS_SIZE/2 && a.getX() + Game.CELLS_SIZE/2 < x + Game.CELLS_SIZE
            && y < a.getY() + Game.CELLS_SIZE/2 && a.getY() + Game.CELLS_SIZE/2 < y + Game.CELLS_SIZE) {
                a.die();
        }
    }
}
