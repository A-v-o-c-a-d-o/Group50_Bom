package Code.Entity.Non_moveable.Items;

import java.io.File;
import Code.App.Game;
import Code.Entity.Moveable.Player;
import Code.Entity.Non_moveable.Non_moveable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Item extends Non_moveable {
    public Item(int x, int y) {
        super(x, y);
        this.prevent = false;
    }

    public abstract void doWhenCollided(Player player);

    public void update(Player other) {
        if (this.isCollidedWith(other)) {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/Resources/sound/beep.wav"));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            doWhenCollided(other);
            alive = false;
        }
    }

    public boolean isCollidedWith(Player other) {
        double x = Math.abs(this.getX() - other.getX());
        double y = Math.abs(this.getY() - other.getY());
        return x < (3* Game.CELLS_SIZE)/5 && y < (3*Game.CELLS_SIZE)/5;
    }

    @Override
    public boolean canBeBurn() {
        return true;
    }
}
