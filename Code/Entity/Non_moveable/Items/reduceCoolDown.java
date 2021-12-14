package Code.Entity.Non_moveable.Items;

import Code.Entity.Moveable.Player;
import javafx.scene.image.Image;

public class reduceCoolDown extends Item {
    public reduceCoolDown(int x, int y) {
        super(x, y);
        image = new Image("./Resources/icons/powerup_bombs.png");
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
