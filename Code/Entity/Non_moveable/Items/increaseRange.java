package Code.Entity.Non_moveable.Items;

import Code.Entity.Moveable.Player;
import javafx.scene.image.Image;

public class increaseRange extends Item {
    public increaseRange(int x, int y) {
        super(x, y);
        image = new Image("./Resources/icons/powerup_flames.png");
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void doWhenCollided(Player player) {
        player.setIgniteRange(player.getIgniteRange() + 1);
    }
}
