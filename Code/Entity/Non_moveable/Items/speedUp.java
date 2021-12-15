package Code.Entity.Non_moveable.Items;

import Code.Entity.Moveable.Player;
import javafx.scene.image.Image;

public class speedUp extends Item{
    public speedUp(int x, int y) {
        super(x, y);
        image = new Image("./Resources/icons/powerup_speed.png");
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void doWhenCollided(Player player) {
        player.setStep(player.getStep() + 3);
    }
}
