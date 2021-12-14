package Code.Entity.Non_moveable.Items;

import Code.Entity.Moveable.Player;
import javafx.scene.image.Image;

public class bonusHealth extends Item {
    public bonusHealth(int x, int y) {
        super(x, y);
        image = new Image("./Resources/icons/powerup_detonator.png");
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
        player.setHealth(player.getHealth() + 1);
    }
}
